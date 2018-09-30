package lvh.naheulbeuk.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
public class Test {
	
	private String name;
	
	private String caract;
	
	private Integer plainModificator; // If conditionType = TEST
	
	private Integer caractBasedModificator; // If conditionType = TEST
	
	private String basedModificatorCaract; // If conditionType = TEST
	
	private Integer fromDefinedPoint;
	
	private Boolean ignoreLowerPoints; // true if basedModificatorCaract point are lower than fromDefinedPoint ignore caractBasedModificator ? (false for the other way around, null for ignore nothing)
	
	//Example: épreuve de FORCE: pour chaque point de PR au dessus de 3, vous aurez un malus de 2
	// caract: Strength, plainModificator: 0, caractBasedModificator: -2, caractBaseModificator: protection, fromDefinedPoint: 3
		
	@JsonProperty(access = Access.READ_ONLY)
	private Integer doLessThan;
	
	private List<Action> actions;

	
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

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract) {
		this.caract = caract;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDoLessThan(Integer doLessThan) {
		this.doLessThan = doLessThan;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
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
