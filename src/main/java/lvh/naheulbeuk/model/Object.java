package lvh.naheulbeuk.model;

import lvh.naheulbeuk.model.list.LocalisationObject;


public class Object {
	
	private String id;
	private LocalisationObject localisation;
	private Integer weight;
	private Equipement equipement;


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

	public boolean same(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj.id != null && !obj.id.equals(id)) return false;
		if (obj.weight != null && !obj.weight.equals(weight)) return false;
		if (obj.localisation != null && !obj.localisation.equals(localisation)) return false;
		if (obj.getEquipement() != null && this.getEquipement() != null) {
			return this.getEquipement().same(obj.getEquipement());
		} else {
			return true;
		}
	}

}
