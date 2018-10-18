package engine.entities.weapons;

import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Projectile;
import engine.entities.projectiles.PurpleFlame;

public class PurpleFlameShooter extends Shooter implements Weapon  {
	
	public PurpleFlameShooter() {
		super();
		initialize();
	}
	
	public PurpleFlameShooter(Entity owner) {
		super(owner);
		initialize();
		
	}
	
	private void initialize() {
		fireRate = Stats.PURPLE_FLAME_FIRE_RATE;
		ammo = maxAmmo = Stats.FLAMETHROWER_MAX_AMMO;
		clips = Stats.FLAMETHROWER_INITIAL_CLIPS;
		reloadTime = Stats.FLAMETHROWER_RELOAD_TIME;
	}
	
	protected Projectile buildProjectile(double angle) {
		return new PurpleFlame(owner, angle);
	}
}