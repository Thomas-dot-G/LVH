package lvh.naheulbeuk.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class PageAccess {

	private List<Condition> conditions;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Action> actions;

	private String targetPageNumber;
	
	private boolean input;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String correctInput;

	@JsonProperty(access = Access.READ_ONLY)
	private boolean unAccessible;
	
	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public boolean isInput() {
		return input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public String getCorrectInput() {
		return correctInput;
	}

	public void setCorrectInput(String correctInput) {
		this.correctInput = correctInput;
	}

	public String getTargetPageNumber() {
		return targetPageNumber;
	}

	public void setTargetPageNumber(String targetPageNumber) {
		this.targetPageNumber = targetPageNumber;
	}

	public boolean isUnAccessible() {
		return unAccessible;
	}

	public void setUnAccessible(boolean unAccessible) {
		this.unAccessible = unAccessible;
	}
}
