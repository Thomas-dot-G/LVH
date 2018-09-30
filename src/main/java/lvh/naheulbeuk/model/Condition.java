package lvh.naheulbeuk.model;

import lvh.naheulbeuk.model.list.ConditionApply;
import lvh.naheulbeuk.model.list.ConditionType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class Condition {
	
	private ConditionApply conditionApply; //AND OR for list of conditions
	
	private ConditionType conditionType;
	
	private CaractCondition caractCondition;

	@JsonProperty(access = Access.READ_ONLY)
	private boolean unAccessible;
			
	private FightCondition fightCondition;
	
	private TestCondition testCondition;
	
	private Object object;
	
	private Boolean inverseCondition;

	public ConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Boolean getInverseCondition() {
		return inverseCondition;
	}

	public void setInverseCondition(Boolean inverseCondition) {
		this.inverseCondition = inverseCondition;
	}

	public ConditionApply getConditionApply() {
		return conditionApply;
	}

	public void setConditionApply(ConditionApply conditionApply) {
		this.conditionApply = conditionApply;
	}

	public boolean isUnAccessible() {
		return unAccessible;
	}

	public void setUnAccessible(boolean isUnAccessible) {
		this.unAccessible = isUnAccessible;
	}

	public CaractCondition getCaractCondition() {
		return caractCondition;
	}

	public void setCaractCondition(CaractCondition caractCondition) {
		this.caractCondition = caractCondition;
	}

	public FightCondition getFightCondition() {
		return fightCondition;
	}

	public void setFightCondition(FightCondition fightCondition) {
		this.fightCondition = fightCondition;
	}

	public TestCondition getTestCondition() {
		return testCondition;
	}

	public void setTestCondition(TestCondition testCondition) {
		this.testCondition = testCondition;
	}
}
