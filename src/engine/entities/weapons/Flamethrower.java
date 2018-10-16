package engine.entities.weapons;

import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Fireball;
import engine.entities.projectiles.Projectile;

public class Flamethrower extends Shooter implements Weapon  {
	
	public Flamethrower() {
		super();
		initialize();
	}
	
	public Flamethrower(Entity owner) {
		super(owner);
		initialize();
		
	}
	
	private void initialize() {
		fireRate = Stats.FLAMETHROWER_FIRE_RATE;
		ammo = maxAmmo = Stats.FLAMETHROWER_MAX_AMMO;
		clips = Stats.FLAMETHROWER_INITIAL_CLIPS;
		reloadTime = Stats.FLAMETHROWER_RELOAD_TIME;
	}
	
	protected Projectile buildProjectile(double angle) {
		return new Fireball(owner, angle);
	}
}