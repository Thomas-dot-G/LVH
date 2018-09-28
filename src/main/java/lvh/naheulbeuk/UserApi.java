package lvh.naheulbeuk;

import java.util.Optional;

import lvh.naheulbeuk.model.LVHError;
import lvh.naheulbeuk.model.Response;
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
	
	@Autowired
	private UserServices userServices;
	
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Response> addUser(@RequestBody User user) {
		try {
			userServices.checkTokenAlreadyExists(user.getToken());
			Optional<User> alreadyExistUser = repository.findByGivenName(user.getGivenName());
			if (alreadyExistUser.isPresent()){
				return new Response(new LVHError(HttpStatus.CONFLICT, "GivenName already exist")).toEntity();
			}
			return new Response(repository.save(user)).toEntity();
		} catch (Exception e) {
			return new Response(new LVHError(HttpStatus.UNAUTHORIZED, "Token not accepted")).toEntity();
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getUserById(@PathVariable String id) {
		return new Response(repository.findById(id).orElse(null)).toEntity();
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Response> getUser(@RequestHeader(value="Authorization") String token) {
		return new Response(repository.findByToken(token).orElse(null)).toEntity();
	}

}