package lvh.naheulbeuk;

import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Choice;
import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.repository.CharacterRepository;
import lvh.naheulbeuk.repository.PageRepository;
import lvh.naheulbeuk.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Character> changePage(@RequestHeader(value="Authorization") String token, @RequestBody Choice choice) {
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
					return new ResponseEntity<Character>(HttpStatus.FORBIDDEN);
				} else {
					services.checkActions(currentPage, newPage, choice, perso);
					return new ResponseEntity<Character>(perso, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<Character>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Character>(HttpStatus.UNAUTHORIZED);
		}
	}

}