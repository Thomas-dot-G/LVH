package lvh.naheulbeuk.model;

import java.util.List;

public class Fight {
	
	private List<Character> contactEnemy;
	
	private Integer roundMax;
	
	private Integer pvMin;
	
	private Integer enemyPvMin;
	
	private List<Character> distanceEnemy;
	
	private boolean mandatory;

	public List<Character> getContactEnemy() {
		return contactEnemy;
	}

	public void setContactEnemy(List<Character> contactEnemy) {
		this.contactEnemy = contactEnemy;
	}

	public Integer getRoundMax() {
		return roundMax;
	}

	public void setRoundMax(Integer roundMax) {
		this.roundMax = roundMax;
	}

	public Integer getPvMin() {
		return pvMin;
	}

	public void setPvMin(Integer pvMin) {
		this.pvMin = pvMin;
	}

	public Integer getEnemyPvMin() {
		return enemyPvMin;
	}

	public void setEnemyPvMin(Integer enemyPvMin) {
		this.enemyPvMin = enemyPvMin;
	}

	public List<Character> getDistanceEnemy() {
		return distanceEnemy;
	}

	public void setDistanceEnemy(List<Character> distanceEnemy) {
		this.distanceEnemy = distanceEnemy;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
}
