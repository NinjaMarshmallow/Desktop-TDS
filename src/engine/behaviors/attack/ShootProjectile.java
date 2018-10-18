package engine.behaviors.attack;

import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.mobs.Mob;

public class ShootProjectile implements AttackBehavior {
	
	private Weapon weapon;
	public ShootProjectile(Entity owner, Weapon weapon) {
		this.weapon = weapon;
		this.weapon.setOwner(owner);
	}
	
	public void execute(Mob mob, Mob target) {
		weapon.shoot(mob.angleTo(target));
	}
	
	public void reset() {
		weapon.reset();
	}

}
