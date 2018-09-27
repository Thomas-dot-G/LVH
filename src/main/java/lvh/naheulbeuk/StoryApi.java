package lvh.naheulbeuk;

import java.util.List;
import java.util.Optional;

import lvh.naheulbeuk.model.Story;
import lvh.naheulbeuk.model.User;
import lvh.naheulbeuk.repository.StoryRepository;
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
@RequestMapping(value="story")
public class StoryApi {
	
	@Autowired
	private StoryRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/add", method = RequestMethod.PUT)
	public ResponseEntity<Story> addStory(@RequestHeader(value="Authorization") String token, @RequestBody Story story) {
		try {
			story.setUserId(this.getUserId(token));
			return new ResponseEntity<Story>(repository.save(story), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Story>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="/get/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Story>> getStories(@PathVariable String userId) {
		return new ResponseEntity<List<Story>>(repository.findByUserId(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ResponseEntity<Story> getStory(@PathVariable String id) {
		return new ResponseEntity<Story>(repository.findById(id).orElse(null), HttpStatus.OK);
	}
	
	private String getUserId(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			return user.get().getId();
		} else {
			throw new Exception("Bad Token");
		}
	}

}