package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FightCondition {
	
	private Boolean combatPassed; // true, combat passed, false, combat failed, null no combat required
	
	private Boolean hasKilled;
	
	private Boolean combatAvoided;

	public Boolean isCombatPassed() {
		return combatPassed;
	}

	public void setCombatPassed(Boolean combatPassed) {
		this.combatPassed = combatPassed;
	}

	public Boolean isHasKilled() {
		return hasKilled;
	}

	public void setHasKilled(Boolean hasKilled) {
		this.hasKilled = hasKilled;
	}

	public Boolean isCombatAvoided() {
		return combatAvoided;
	}

	public void setCombatAvoided(Boolean combatAvoided) {
		this.combatAvoided = combatAvoided;
	}
		
}
