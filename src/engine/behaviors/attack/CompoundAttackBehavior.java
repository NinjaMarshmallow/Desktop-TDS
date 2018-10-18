package engine.behaviors.attack;

import engine.entities.mobs.Mob;

public class CompoundAttackBehavior {
	
private AttackBehavior[] attacks;
	
	public CompoundAttackBehavior(AttackBehavior ... attackBehaviors) {
		this.attacks = attackBehaviors;
	}
	
	public void execute(Mob mob, Mob target) {
		for(AttackBehavior attack : attacks) {
			attack.execute(mob, target);
		}
	}

}
