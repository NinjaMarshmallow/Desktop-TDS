package engine.behaviors.attack;

import engine.entities.mobs.Mob;

public interface AttackBehavior {
	
	public void execute(Mob mob, Mob target);
	public void reset();

}
