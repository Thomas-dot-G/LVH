package lvh.naheulbeuk.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Temporary {
	
	private String id;

	private String name;

	private Equipement bonus;
	
	private Date creationDate;
	
	private Integer length; // in seconds
	
	public Temporary() {
		this.creationDate = new Date();
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean same(Temporary temp) {
		if (this == temp) return true;
		if (temp == null) return false;
		if (temp.getId() != null && !temp.getId().equals(id)) return false;
		if (temp.getName() != null && !temp.getName().equals(name)) return false;
		if (temp.getBonus() != null && this.getBonus() != null) {
			return this.getBonus().same(temp.getBonus());
		} else if (temp.getBonus() != null && this.getBonus() == null){
			return false;
		} else {
			return true;
		}
	}
	
}
