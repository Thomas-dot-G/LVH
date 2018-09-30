package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FightCondition {
	
	private boolean combatPassed; // true, combat passed, false, combat failed, null no combat required
	
	private boolean hasKilled;
	
	private boolean combatAvoided;

	public boolean isCombatPassed() {
		return combatPassed;
	}

	public void setCombatPassed(boolean combatPassed) {
		this.combatPassed = combatPassed;
	}

	public boolean isHasKilled() {
		return hasKilled;
	}

	public void setHasKilled(boolean hasKilled) {
		this.hasKilled = hasKilled;
	}

	public boolean isCombatAvoided() {
		return combatAvoided;
	}

	public void setCombatAvoided(boolean combatAvoided) {
		this.combatAvoided = combatAvoided;
	}
		
}
