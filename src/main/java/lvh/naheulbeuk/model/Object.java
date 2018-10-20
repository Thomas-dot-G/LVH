package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lvh.naheulbeuk.model.list.LocalisationObject;

@JsonInclude(Include.NON_NULL)
public class Object {
	
	private String id;
	private LocalisationObject localisation;
	private Boolean questObject;
	private Integer weight;
	private Equipement equipement;
	private String imageUri;


	public String getId() {
		return id;
	}

	public LocalisationObject getLocalisation() {
		return localisation;
	}

	public void setLocalisation(LocalisationObject localisation) {
		this.localisation = localisation;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean isQuestObject() {
		return questObject;
	}

	public void setQuestObject(Boolean questObject) {
		this.questObject = questObject;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Boolean getQuestObject() {
		return questObject;
	}

	public boolean same(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj.isQuestObject() != null && obj.isQuestObject() != questObject) return false;
		if (obj.getImageUri() != null && !obj.getImageUri().equals(imageUri)) return false;
		if (obj.getId() != null && !obj.getId().equals(id)) return false;
		if (obj.getWeight() != null && !obj.getWeight().equals(weight)) return false;
		if (obj.getLocalisation() != null && !obj.getLocalisation().equals(localisation)) return false;
		if (obj.getEquipement() != null && this.getEquipement() != null) {
			return this.getEquipement().same(obj.getEquipement());
		} else if (obj.getEquipement() != null && this.getEquipement() == null){
			return false;
		} else {
			return true;
		}
	}

}
