package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class Condition {
	
	private ConditionApply conditionApply; //AND OR for list of conditions
	
	private ConditionType conditionType;
	
	private String caract;
	
	private ConditionApply caractCondition; // If conditionType = CARACT
	
	private Integer points;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isUnAccessible;
	
	private Boolean testPassed; // true, test passed, false, test failed, null no test required
	
	private String testName;
	
	private Object object;
	
	private Boolean mustNotHaveObject;

	public ConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract) {
		this.caract = caract;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Boolean getMustNotHaveObject() {
		return mustNotHaveObject;
	}

	public void setMustNotHaveObject(Boolean mustNotHaveObject) {
		this.mustNotHaveObject = mustNotHaveObject;
	}

	public ConditionApply getConditionApply() {
		return conditionApply;
	}

	public void setConditionApply(ConditionApply conditionApply) {
		this.conditionApply = conditionApply;
	}

	public ConditionApply getCaractCondition() {
		return caractCondition;
	}

	public void setCaractCondition(ConditionApply caractCondition) {
		this.caractCondition = caractCondition;
	}

	public boolean isUnAccessible() {
		return isUnAccessible;
	}

	public void setUnAccessible(boolean isUnAccessible) {
		this.isUnAccessible = isUnAccessible;
	}

	public Boolean getTestPassed() {
		return testPassed;
	}

	public void setTestPassed(Boolean testPassed) {
		this.testPassed = testPassed;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
		
}
