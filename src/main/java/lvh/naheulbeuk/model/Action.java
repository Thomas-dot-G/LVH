package lvh.naheulbeuk.model;

import lvh.naheulbeuk.model.list.ActionType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Action {
	
	private ActionType actionType;
	
	private int quantity;
	
	private Object object;
	
	private String caract;
	

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
	
}
