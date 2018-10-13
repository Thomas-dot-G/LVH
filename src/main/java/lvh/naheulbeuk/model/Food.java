package lvh.naheulbeuk.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Food {
	
	private String id;

	private String name;

	private Temporary temporary;
	
	private Date creationDate;
	
	private Integer dayLength;
	
	private Integer portions;
	
	private Integer buyingPrice;
	
	private Integer sellingPrice;
	
	public Food() {
		this.id = UUID.randomUUID().toString();
		this.creationDate = new Date();
		this.portions = 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Temporary getTemporary() {
		return temporary;
	}

	public void setTemporary(Temporary temporary) {
		this.temporary = temporary;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getDayLength() {
		return dayLength;
	}

	public void setDayLength(Integer dayLength) {
		this.dayLength = dayLength;
	}

	public Integer getPortions() {
		return portions;
	}

	public void setPortions(Integer portions) {
		this.portions = portions;
	}

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public boolean same(Food food) {
		if (this == food) return true;
		if (food == null) return false;
		if (food.getId() != null && !food.getId().equals(id)) return false;
		if (food.getName() != null && !food.getName().equals(name)) return false;
		if (food.getPortions() != null && !food.getPortions().equals(portions)) return false;
		if (food.getDayLength() != null && !food.getDayLength().equals(dayLength)) return false;
		if (food.getTemporary() != null && this.temporary != null) {
			return this.getTemporary().same(food.getTemporary());
		} else if (food.getTemporary() != null && this.getTemporary() == null){
			return false;
		} else {
			return true;
		}
	}

}
