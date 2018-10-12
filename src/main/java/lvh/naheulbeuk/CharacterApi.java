package lvh.naheulbeuk;

import java.util.List;

import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Food;
import lvh.naheulbeuk.model.output.LVHError;
import lvh.naheulbeuk.model.output.Response;
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
@RequestMapping(value="perso")
public class CharacterApi {
	
	@Autowired
	private CharacterRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Response> addPerso(@RequestHeader(value="Authorization") String token, @RequestBody Character character) {
		if (token == null) {
			return Response.invalideToken();
		} else {
			try {
				character.setUserId(userServices.getUserId(token));
			} catch (Exception e) {
				return Response.invalideToken();
			}
			Character perso = repository.save(character);
			final Response response = new Response(perso);
			if (perso.getPageId() != null) {
				response.setPage(pageRepository.findById(perso.getPageId()).orElse(null));
			}
			return response.toEntity();
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getPerso(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		Character perso = repository.findById(id).orElse(null);
		if (perso != null) {
			try {
				userServices.checkTokenMatchUserId(token, perso.getUserId());
			} catch (Exception e) {
				return Response.invalideToken();
			}
		} else {
			return new Response(new LVHError(HttpStatus.NOT_FOUND, "Perso not found")).toEntity();
		}
		return new Response(perso).toEntity();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deletePerso(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		Character perso = repository.findById(id).orElse(null);
		if (perso != null) {
			try {
				userServices.checkTokenMatchUserId(token, perso.getUserId());
				repository.delete(perso);
			} catch (Exception e) {
				return Response.invalideToken();
			}
		}
		return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Character>> getMyPerso(@RequestHeader(value="Authorization") String token) {
		try {
			final String userId = userServices.getUserId(token);
			return new ResponseEntity<List<Character>>(repository.findByUserId(userId), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/{persoId}/eat", method = RequestMethod.POST)
	public ResponseEntity<Response> eat(@RequestHeader(value="Authorization") String token, @PathVariable String persoId, @RequestBody Food food) {
		if (token == null) {
			return Response.invalideToken();
		} else {
			Character perso = repository.findById(persoId).orElse(null);
			try {
				userServices.checkTokenMatchUserId(token, perso.getUserId());
			} catch (Exception e) {
				return Response.invalideToken();
			}
			try {
				perso.eat(food.getId());
			} catch (Exception e) {
				return new Response(new LVHError(HttpStatus.BAD_REQUEST, "No id for food found")).toEntity();
			}
			Response response = new Response(repository.save(perso));
			if (perso.getPageId() != null) {
				response.setPage(pageRepository.findById(perso.getPageId()).orElse(null));
			}
			return response.toEntity();
		}
	}

}