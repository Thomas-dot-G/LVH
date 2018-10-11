package lvh.naheulbeuk.model;

import java.util.List;

import lvh.naheulbeuk.model.list.ActionType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Action {
	
	private ActionType actionType;
	
	private int quantity;
	
	private int d6Quantity;
	
	private int d20Quantity;
	
	private Object object;
	
	private Temporary temporary;
	
	private String caract;
	
	private List<Condition> conditions;

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract) {
		this.caract = caract;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public int getD6Quantity() {
		return d6Quantity;
	}

	public void setD6Quantity(int d6Quantity) {
		this.d6Quantity = d6Quantity;
	}

	public int getD20Quantity() {
		return d20Quantity;
	}

	public void setD20Quantity(int d20Quantity) {
		this.d20Quantity = d20Quantity;
	}

	public Temporary getTemporary() {
		return temporary;
	}

	public void setTemporary(Temporary temporary) {
		this.temporary = temporary;
	}
	
}
