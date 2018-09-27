package lvh.naheulbeuk;

import java.util.Optional;

import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.User;
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
@RequestMapping(value="user")
public class UserApi {
	
	@Autowired
	private UserRepository repository;
	
	
	@RequestMapping(value="/add", method = RequestMethod.PUT)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			checkToken(user.getToken());
			return new ResponseEntity<User>(repository.save(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		return new ResponseEntity<User>(repository.findById(id).orElse(null), HttpStatus.OK);
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@RequestHeader(value="Authorization") String token) {
		return new ResponseEntity<User>(repository.findByToken(token).orElse(null), HttpStatus.OK);
	}
	
	private void checkToken(final String token) throws Exception {
		Optional<User> user = repository.findByToken(token);
		if (user.isPresent()){
			throw new Exception("User already exists");
		}
	}

}