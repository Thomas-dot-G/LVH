package lvh.naheulbeuk;

import java.util.Optional;

import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Story;
import lvh.naheulbeuk.model.User;
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
	
	@RequestMapping(value="/add", method = RequestMethod.PUT)
	public ResponseEntity<Character> addPerso(@RequestHeader(value="Authorization") String token, @RequestBody Character character) {
		if (token == null) {
			return new ResponseEntity<Character>(HttpStatus.UNAUTHORIZED);
		} else {
			try {
				character.setUserId(getIdFromToken(token));
			} catch (Exception e) {
				return new ResponseEntity<Character>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<Character>(repository.save(character), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Character> getPerso(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		Character perso = repository.findById(id).orElse(null);
		if (perso != null) {
			try {
				checkToken(token, perso.getUserId());
			} catch (Exception e) {
				return new ResponseEntity<Character>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<Character>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Character>(perso, HttpStatus.OK);
	}
	
	private String getIdFromToken(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			return user.get().getId();
		} else {
			throw new Exception("Bad Token");
		}
	}
	
	private void checkToken(final String token, final String userId) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			if (!user.get().getId().equals(userId)) {
				throw new Exception("Bad Token");
			}
		} else {
			throw new Exception("Bad Token");
		}
	}

}