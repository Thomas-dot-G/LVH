package lvh.naheulbeuk.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Temporary {

	private String name;

	private Equipement bonus;
	
	private Date creationDate;
	
	private Integer length; // in seconds

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipement getBonus() {
		return bonus;
	}

	public void setBonus(Equipement bonus) {
		this.bonus = bonus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
}
