package engine.entities.weapons;

import engine.entities.Entity;
import engine.entities.projectiles.Projectile;
import engine.entities.projectiles.Watermelon;

public class WatermelonLauncher extends Shooter {
	
	public WatermelonLauncher() {
		super();
	}
	
	public WatermelonLauncher(Entity owner) {
		super(owner);
	}
	
	protected Projectile buildProjectile(double angle) {
		return new Watermelon(owner, angle);
	}

}
