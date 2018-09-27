package lvh.naheulbeuk.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class PageAccess {
		
	private boolean condition;
	
	private String conditionType;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Action> actions;

	private String targetPageNumer;
	
	private boolean input;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String correctInput;


	public boolean isCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
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

	public String getTargetPageNumer() {
		return targetPageNumer;
	}

	public void setTargetPageNumer(String targetPageNumer) {
		this.targetPageNumer = targetPageNumer;
	}	
}
