package lvh.naheulbeuk;

import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.Story;
import lvh.naheulbeuk.repository.PageRepository;
import lvh.naheulbeuk.repository.StoryRepository;
import lvh.naheulbeuk.repository.UserRepository;
import lvh.naheulbeuk.model.User;

import java.util.Optional;

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
@RequestMapping(value="page")
public class PageApi {
	
	@Autowired
	private PageRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@RequestMapping(value="/add", method = RequestMethod.PUT)
	public ResponseEntity<Page> addPage(@RequestHeader(value="Authorization") String token, @RequestBody Page page) {
		try {
			checkToken(token);
			checkStory(token, page.getStoryId());
			checkEntryPoint(page);
			try {
				checkPageNumber(page);
			} catch (Exception e1) {
				return new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Page>(repository.save(page), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Page>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> getPage(@PathVariable String id) {
		return new ResponseEntity<Page>(repository.findById(id).orElse(null), HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/story/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> getFirstPage(@PathVariable String id) {
		return new ResponseEntity<Page>(repository.findByStoryIdAndEntryPointTrue(id).orElse(null), HttpStatus.OK);
	}
	
	private void checkToken(final String token) throws Exception {
		Optional<User> user = userRepository.findByToken(token);
		if (!user.isPresent()){
			throw new Exception("Bad Token");
		}
	}
	
	private void checkStory(final String token, final String id) throws Exception {
		Optional<Story> story = storyRepository.findById(id);
		Optional<User> user = userRepository.findByToken(token);
		if (!story.isPresent()){
			throw new Exception("Non existing Story");
		} else if (!user.isPresent()){
			throw new Exception("Bad Token");
		} else if (!user.get().getId().equals(story.get().getUserId())) {
			throw new Exception("Not Authorized");
		}
	}
	
	private void checkEntryPoint(Page page) {
		if (page.isEntryPoint()) {
			// Disable previous entryPointPage
			repository.findByStoryIdAndEntryPointTrue(page.getStoryId()).ifPresent(pageEntryPoint -> {
				pageEntryPoint.setEntryPoint(false);
				repository.save(pageEntryPoint);
			});
		}
	}
	
	private void checkPageNumber(Page page) throws Exception {
		if(repository.findByStoryIdAndPageNumber(page.getStoryId(), page.getPageNumber()).isPresent()) {
			throw new Exception("Page Number already exists");
		}
	}

}