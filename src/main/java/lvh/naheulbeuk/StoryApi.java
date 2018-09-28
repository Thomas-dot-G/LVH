package lvh.naheulbeuk;

import java.util.List;

import lvh.naheulbeuk.model.Response;
import lvh.naheulbeuk.model.Story;
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
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Response> addStory(@RequestHeader(value="Authorization") String token, @RequestBody Story story) {
		try {
			story.setUserId(userServices.getUserId(token));
			return new Response(repository.save(story)).toEntity();
		} catch (Exception e) {
			return Response.invalideToken();
		}
		
	}
	
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Story>> getStories(@PathVariable String userId) {
		return new ResponseEntity<List<Story>>(repository.findByUserId(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Story>> getMyStories(@RequestHeader(value="Authorization") String token) {
		try {
			return new ResponseEntity<List<Story>>(repository.findByUserId(userServices.getUserId(token)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}