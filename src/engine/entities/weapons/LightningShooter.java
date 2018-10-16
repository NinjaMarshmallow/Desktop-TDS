package engine.entities.weapons;

import engine.entities.Entity;
import engine.entities.projectiles.LightningBolt;
import engine.entities.projectiles.Projectile;

public class LightningShooter extends Shooter {
	
	public LightningShooter() {
		super();
	}
	
	public LightningShooter(Entity owner) {
		super(owner);
	}
	
	protected Projectile buildProjectile(double angle) {
		return new LightningBolt(owner, angle);
	}

}
