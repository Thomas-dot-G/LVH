package lvh.naheulbeuk;

import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.Story;
import lvh.naheulbeuk.repository.PageRepository;
import lvh.naheulbeuk.repository.StoryRepository;
import lvh.naheulbeuk.repository.UserRepository;
import lvh.naheulbeuk.model.User;
import lvh.naheulbeuk.model.output.LVHError;
import lvh.naheulbeuk.model.output.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Response> addPage(@RequestHeader(value="Authorization") String token, @RequestBody Page page) {
		try {
			userServices.checkToken(token);
			checkStory(token, page.getStoryId());
			checkEntryPoint(page);
			try {
				checkPageNumber(page);
			} catch (Exception e1) {
				return new Response(new LVHError(HttpStatus.BAD_REQUEST, "This page number already exists for that story")).toEntity();
			}
			return new Response(repository.save(page)).toEntity();
		} catch (Exception e) {
			return Response.invalideToken();
		}
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deletePage(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		try {
			userServices.checkToken(token);
			Page page = repository.findById(id).orElse(null);
			if (page != null) {
				if(checkStory(token, page.getStoryId()).isAdminApproved()) {
					return new Response(new LVHError(HttpStatus.FORBIDDEN, "This story is approved and cannot be changed")).toEntity();
				} else {
				repository.delete(page);
				}
			}
			return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return Response.invalideToken();
		}
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getPage(@PathVariable String id) {
		return new Response(repository.findById(id).orElse(null)).toEntity();
	}
	
	@RequestMapping(value="/story/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getFirstPage(@PathVariable String id) {
		return new Response(repository.findByStoryIdAndEntryPointTrue(id).orElse(null)).toEntity();
	}
	
	@RequestMapping(value="/story/{id}/pageNumber", method = RequestMethod.GET)
	public ResponseEntity<List<Page>> getPagesName(@PathVariable String id) {
		return new ResponseEntity<List<Page>>(repository.findByStoryId(id).stream().map(page -> {
			final Page pageNum = new Page(); 
			pageNum.setPageNumber(page.getPageNumber()); 
			return pageNum;
		}).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	private Story checkStory(final String token, final String id) throws Exception {
		Optional<Story> story = storyRepository.findById(id);
		Optional<User> user = userRepository.findByToken(token);
		if (!story.isPresent()){
			throw new Exception("Non existing Story");
		} else if (!user.isPresent()){
			throw new Exception("Bad Token");
		} else if (!user.get().getId().equals(story.get().getUserId())) {
			throw new Exception("Not Authorized");
		}
		return story.orElse(null);
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