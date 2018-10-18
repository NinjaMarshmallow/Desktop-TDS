package engine.behaviors.attack;

import util.Environment;
import util.Stats;
import engine.entities.mobs.Mob;

public class DamageOnCollide implements AttackBehavior {
	private int time;
	public DamageOnCollide() {
		this.time = 0;
	}

	public void execute(Mob mob, Mob target) {
		if(time % Stats.ENEMY_MELEE_RATE == 0) {
			if(mob.collides(target)) {
				mob.bodyAttack(target);
			}
		}
		time++;
	}
	
	public void reset() {
		time = 0;
	}
}
