package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public class Condition {
	
	private ConditionType conditionType;
	
	private String caract;
	
	private Integer plainModificator;
	
	private Integer caractBasedModificator;
	
	private String basedModificatorCaract;
	
	private Integer fromDefinedPoint;
	
	private Boolean ignoreLowerPoints; // if basedModificatorCaract point are lower than fromDefinedPoint ignore caractBasedModificator ? (false for the other way around, null for ignore nothing)
	
	private Integer doLessThan;
	
	//Example: épreuve de FORCE: pour chaque point de PR au dessus de 3, vous aurez un malus de 2
	// caract: Strength, plainModificator: 0, caractBasedModificator: -2, caractBaseModificator: protection, fromDefinedPoint: 3
	
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

	public Integer getPlainModificator() {
		return plainModificator;
	}

	public void setPlainModificator(Integer plainModificator) {
		this.plainModificator = plainModificator;
	}

	public Integer getCaractBasedModificator() {
		return caractBasedModificator;
	}

	public void setCaractBasedModificator(Integer caractBasedModificator) {
		this.caractBasedModificator = caractBasedModificator;
	}

	public String getBasedModificatorCaract() {
		return basedModificatorCaract;
	}

	public void setBasedModificatorCaract(String basedModificatorCaract) {
		this.basedModificatorCaract = basedModificatorCaract;
	}

	public Integer getFromDefinedPoint() {
		return fromDefinedPoint;
	}

	public void setFromDefinedPoint(Integer fromDefinedPoint) {
		this.fromDefinedPoint = fromDefinedPoint;
	}

	public Boolean getIgnoreLowerPoints() {
		return ignoreLowerPoints;
	}

	public void setIgnoreLowerPoints(Boolean ignoreLowerPoints) {
		this.ignoreLowerPoints = ignoreLowerPoints;
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

	public void setDoLessThan(int caract, int basedModificatorCaract) {
		int baseModificator = (basedModificatorCaract - (fromDefinedPoint != null ? fromDefinedPoint : 0));
		if (ignoreLowerPoints != null && ignoreLowerPoints == true && baseModificator < 0) baseModificator = 0;
		if (ignoreLowerPoints != null && ignoreLowerPoints == false && baseModificator > 0) baseModificator = 0;

		this.doLessThan = caract + plainModificator + (baseModificator * caractBasedModificator);
	}
	
	public Integer getDoLessThan() {
		return doLessThan;
	}
		
}
