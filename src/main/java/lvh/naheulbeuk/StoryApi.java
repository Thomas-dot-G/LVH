package lvh.naheulbeuk;

import java.util.List;

import lvh.naheulbeuk.model.LVHError;
import lvh.naheulbeuk.model.Response;
import lvh.naheulbeuk.model.Story;
import lvh.naheulbeuk.repository.PageRepository;
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
	
	@Autowired
	private PageRepository pageRepository;
	
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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Story>> getAllStories() {
		return new ResponseEntity<List<Story>>(repository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Story>> getMyStories(@RequestHeader(value="Authorization") String token) {
		try {
			return new ResponseEntity<List<Story>>(repository.findByUserId(userServices.getUserId(token)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteStory(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		try {
			userServices.checkToken(token);
			Story story = repository.findById(id).orElse(null);
			if (story != null) {
				if (!userServices.getUserId(token).equals(story.getUserId())) {
					return new Response(new LVHError(HttpStatus.FORBIDDEN, "You are not allowed to do that")).toEntity();
				}
				if (story.isAdminApproved()) {
					return new Response(new LVHError(HttpStatus.FORBIDDEN, "This story is approved and cannot be deleted")).toEntity();

				}
				pageRepository.deleteByStoryId(story.getId());
				repository.delete(story);
			}
			return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return Response.invalideToken();
		}
		
	}

}