package lvh.naheulbeuk;

import java.util.Optional;

import lvh.naheulbeuk.model.User;
import lvh.naheulbeuk.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void checkTokenMatchUserId(final String token, final String userId) throws Exception {
		if (token == null) {
			throw new Exception("No Token");
		}
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			if (!user.get().getId().equals(userId)) {
				throw new Exception("Bad Token");
			}
		} else {
			throw new Exception("Bad Token");
		}
	}
	
	public void checkTokenAlreadyExists(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			throw new Exception("User already exists");
		}
	}
	
	public void checkToken(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (!user.isPresent()){
			throw new Exception("Bad Token");
		}
	}
	
	public String getUserId(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			return user.get().getId();
		} else {
			throw new Exception("Bad Token");
		}
	}

}