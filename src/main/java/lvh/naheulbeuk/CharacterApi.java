package lvh.naheulbeuk;

import java.util.List;

import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.LVHError;
import lvh.naheulbeuk.model.Response;
import lvh.naheulbeuk.repository.CharacterRepository;
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
			return new Response(repository.save(character)).toEntity();
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

}