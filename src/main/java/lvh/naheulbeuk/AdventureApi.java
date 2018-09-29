package lvh.naheulbeuk;

import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Choice;
import lvh.naheulbeuk.model.LVHError;
import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.Response;
import lvh.naheulbeuk.repository.CharacterRepository;
import lvh.naheulbeuk.repository.PageRepository;
import lvh.naheulbeuk.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="adventure")
public class AdventureApi {
	
	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	@Autowired
	private AdventureServices services;
	
	@RequestMapping(value="/changePage", method = RequestMethod.POST)
	public ResponseEntity<Response> changePage(@RequestHeader(value="Authorization") String token, @RequestBody Choice choice) {
		try {
			Character perso = characterRepository.findById(choice.getPersoId()).orElse(null);
			if (perso != null) {
				services.checkToken(token, perso.getUserId());
				Page currentPage = null;
				if (perso.getPageId() != null) {
					currentPage = pageRepository.findById(perso.getPageId()).orElse(null);
				}
				Page newPage = services.checkPageAccess(currentPage, choice);
				if(newPage == null){
					return new Response(new LVHError(HttpStatus.FORBIDDEN, "Access unknown or forbidden")).toEntity();
				} else {
					services.checkActions(currentPage, newPage, choice, perso);
					services.setPageAccess(perso, newPage);
					return new Response(perso, newPage).toEntity();
				}
			} else {
				return new Response(new LVHError(HttpStatus.NOT_FOUND, "Perso not found")).toEntity();
			}
		} catch (Exception e) {
			return new Response(new LVHError(HttpStatus.UNAUTHORIZED, "Access not allowed")).toEntity();
		}
	}
	
	@RequestMapping(value="/{persoId}", method = RequestMethod.GET)
	public ResponseEntity<Response> reloadPage(@RequestHeader(value="Authorization") String token, @PathVariable String persoId) {
		try {
			Character perso = characterRepository.findById(persoId).orElse(null);
			if (perso != null) {
				services.checkToken(token, perso.getUserId());
				final Response response = new Response(perso);
				Page currentPage = null;
				if (perso.getPageId() != null) {
					currentPage = pageRepository.findById(perso.getPageId()).orElse(null);
					services.setPageAccess(perso, currentPage);
					response.setPage(currentPage);
				}
				return response.toEntity();
			} else {
				return new Response(new LVHError(HttpStatus.NOT_FOUND, "Perso not found")).toEntity();
			}
		} catch (Exception e) {
			return Response.invalideToken();
		}
	}

}