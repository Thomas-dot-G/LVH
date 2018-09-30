package lvh.naheulbeuk.model;

import lvh.naheulbeuk.model.list.ConditionApply;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CaractCondition {
	
	private String caract;
	
	private ConditionApply caractLogic; 
	
	private Integer points;
	
	private String caractValue;

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract) {
		this.caract = caract;
	}

	public ConditionApply getCaractLogic() {
		return caractLogic;
	}

	public void setCaractLogic(ConditionApply caractLogic) {
		this.caractLogic = caractLogic;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getCaractValue() {
		return caractValue;
	}

	public void setCaractValue(String caractValue) {
		this.caractValue = caractValue;
	}
	
}
