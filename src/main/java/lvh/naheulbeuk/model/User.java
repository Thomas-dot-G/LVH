package lvh.naheulbeuk.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class User {
	
	private final String id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Indexed
	private String token;
	
	private String givenName;
	
	private String avatarUri;
	
	private final Date creationDate;


	public User() {
		this.id = UUID.randomUUID().toString();
		this.creationDate = new Date();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getAvatarUri() {
		return avatarUri;
	}

	public void setAvatarUri(String avatarUri) {
		this.avatarUri = avatarUri;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getId() {
		return id;
	}

}
