package lvh.naheulbeuk.model;

import lvh.naheulbeuk.model.list.EquipementType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Equipement {
	
	private String name;

	private EquipementType type;
	
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
	
	private Integer rechargeTime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EquipementType getType() {
		return type;
	}

	public void setType(EquipementType type) {
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

	public Integer getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Integer rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public boolean same(Equipement eq) {
		if (this == eq) return true;
		if (eq == null) return false;
		if (eq.additionalDamage != null && !eq.additionalDamage.equals(additionalDamage)) return false;
		if (eq.attack != null && !eq.attack.equals(attack)) return false;
		if (eq.attackDie != null && !eq.attackDie.equals(attackDie)) return false;
		if (eq.charisma != null && !eq.charisma.equals(charisma)) return false;
		if (eq.courage != null && !eq.courage.equals(courage)) return false;
		if (eq.dexterity != null && !eq.dexterity.equals(dexterity)) return false;
		if (eq.intelligence != null && !eq.intelligence.equals(intelligence)) return false;
		if (eq.name != null && !eq.name.equals(name)) return false;
		if (eq.parry != null && !eq.parry.equals(parry)) return false;
		if (eq.protection != null && !eq.protection.equals(protection)) return false;
		if (eq.strength != null && !eq.strength.equals(strength)) return false;
		if (eq.rechargeTime != null && !eq.rechargeTime.equals(rechargeTime)) return false;
		if (eq.type != null && !eq.type.equals(type)) return false;
		return true;
	}
	
	
	
}
