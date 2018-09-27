package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Equipement extends Object {
	
	private String name;

	private String type;
	
	private Integer courage;
	
	private Integer intelligence;
	
	private Integer charisma;
	
	private Integer dexterity;
	
	private Integer strength;
	
	private Integer attack;
	
	private Integer parry;
	
	private Integer protection;

	private Integer sellingPrice;

	private Integer buyingPrice;
	
	private Integer attackDie;
	
	private Integer additionalDamage;
		
	private Integer projectiles;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCourage() {
		return courage;
	}

	public void setCourage(Integer courage) {
		this.courage = courage;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getCharisma() {
		return charisma;
	}

	public void setCharisma(Integer charisma) {
		this.charisma = charisma;
	}

	public Integer getDexterity() {
		return dexterity;
	}

	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getParry() {
		return parry;
	}

	public void setParry(Integer parry) {
		this.parry = parry;
	}

	public Integer getProtection() {
		return protection;
	}

	public void setProtection(Integer protection) {
		this.protection = protection;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Integer getAttackDie() {
		return attackDie;
	}

	public void setAttackDie(Integer attackDie) {
		this.attackDie = attackDie;
	}

	public Integer getAdditionalDamage() {
		return additionalDamage;
	}

	public void setAdditionalDamage(Integer additionalDamage) {
		this.additionalDamage = additionalDamage;
	}

	public Integer getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(Integer projectiles) {
		this.projectiles = projectiles;
	}

	public boolean same(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Equipement other = (Equipement) obj;
		if (other.additionalDamage != null && !other.additionalDamage.equals(additionalDamage)) return false;
		if (other.attack != null && !other.attack.equals(attack)) return false;
		if (other.attackDie != null && !other.attackDie.equals(attackDie)) return false;
		if (other.charisma != null && !other.charisma.equals(charisma)) return false;
		if (other.courage != null && !other.courage.equals(courage)) return false;
		if (other.dexterity != null && !other.dexterity.equals(dexterity)) return false;
		if (other.intelligence != null && !other.intelligence.equals(intelligence)) return false;
		if (other.name != null && !other.name.equals(name)) return false;
		if (other.parry != null && !other.parry.equals(parry)) return false;
		if (other.protection != null && !other.protection.equals(protection)) return false;
		if (other.strength != null && !other.strength.equals(strength)) return false;
		if (other.type != null && !other.type.equals(type)) return false;
		return true;
	}
	
	
	
}
