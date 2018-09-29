package lvh.naheulbeuk.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {
	
	private User user;
	
	private Page page;
	
	private Story story;
	
	private Character perso;
	
	private LVHError error;
	
	public Response() {
	}
	
	public Response(User user) {
		this.user = user;
	}
	
	public Response(Character perso) {
		this.perso = perso;
	}
	
	public Response(Character perso, Page page) {
		this.perso = perso;
		this.page = page;
	}
	
	public Response(Story story) {
		this.story = story;
	}
	
	public Response(Page page) {
		this.page = page;
	}
	
	public Response(LVHError error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Character getPerso() {
		return perso;
	}

	public void setPerso(Character perso) {
		this.perso = perso;
	}

	public LVHError getError() {
		return error;
	}

	public void setError(LVHError error) {
		this.error = error;
	}
	
	public ResponseEntity<Response> toEntity() {
		if (error != null) {
			return new ResponseEntity<Response>(this, error.getCode());

		} else {
			return new ResponseEntity<Response>(this, HttpStatus.OK);
		}
	}
	
	public static ResponseEntity<Response> invalideToken() {
		return new Response(new LVHError(HttpStatus.UNAUTHORIZED, "Invalid Token")).toEntity();
	}

}
