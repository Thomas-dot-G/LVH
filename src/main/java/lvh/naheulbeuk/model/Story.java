package lvh.naheulbeuk.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Story {
	
	private final String id;
	
	@Indexed
	private String userId;
	
	private String description;
	
	private String rangeLevel;
	
	private boolean adminApproved;
	
	private final Date creationDate;
	
	private Date lastUpdateDate;
	
	private Date approvedDate;
	
	private int likes;
	
	private int dislikes;
	
	private String imageUri;
	
	public Story() {
		this.id = UUID.randomUUID().toString();
		this.creationDate = new Date();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRangeLevel() {
		return rangeLevel;
	}

	public void setRangeLevel(String rangeLevel) {
		this.rangeLevel = rangeLevel;
	}

	public boolean isAdminApproved() {
		return adminApproved;
	}

	public void setAdminApproved(boolean adminApproved) {
		this.adminApproved = adminApproved;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

}
