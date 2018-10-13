package lvh.naheulbeuk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class Page {
	
	private final String id;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Indexed
	private String storyId;
	
	@Indexed
	private String pageNumber;
	
	private String text;
	
	private String imageUri;
	
	@Indexed
	private boolean entryPoint;
	
	private Fight fight;
	
	private List<Test> tests = new ArrayList<Test>();
	
	private String storeId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean hasEncounterPb;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Action> actions = new ArrayList<Action>();
	
	private List<PageAccess> pageAccesses = new ArrayList<PageAccess>();
	
	public Page() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Page(String id, String pageNumber, String text) {
		this.id = id;
		this.pageNumber = pageNumber;
		this.text = text;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<PageAccess> getPageAccesses() {
		return pageAccesses;
	}

	public void setPageAccesses(List<PageAccess> pageAccesses) {
		this.pageAccesses = pageAccesses;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public boolean isEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(boolean entryPoint) {
		this.entryPoint = entryPoint;
	}

	public Fight getFight() {
		return fight;
	}

	public void setFight(Fight fight) {
		this.fight = fight;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public List<Test> getTests() {
		return tests;
	}
	
	public Test getTest(final String testName) {
		if (testName == null && this.tests != null && this.tests.size() == 1) return this.tests.get(0);
		for (Test test: this.tests) {
			if (testName.equals(test.getName())) {
				return test;
			}
		}
		return null;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public PageAccess getPageAccess(final String pageNumber) {
		if (pageNumber == null) return null;
		for (PageAccess pageaccess: this.getPageAccesses()) {
			if (pageNumber.equals(pageaccess.getTargetPageNumber())) return pageaccess;
		}
		return null;
	}

	public boolean isHasEncounterPb() {
		return hasEncounterPb;
	}

	public void setHasEncounterPb(boolean hasEncounterPb) {
		this.hasEncounterPb = hasEncounterPb;
	}

}
