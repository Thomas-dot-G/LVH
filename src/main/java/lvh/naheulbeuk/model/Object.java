package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
		@JsonSubTypes({ 
		  @Type(value = Equipement.class),
		})
public class Object {
	
	private String id;
	private LocalisationObject localisation;
	private Integer weight;


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

}
