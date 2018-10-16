package engine.entities.weapons;

import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Hatchet;
import engine.entities.projectiles.Projectile;

public class HatchetTosser extends Shooter implements Weapon  {
	
	public HatchetTosser() {
		super();
		initialize();
	}
	
	public HatchetTosser(Entity owner) {
		super(owner);
		initialize();
	}
	
	private void initialize() {
		maxAmmo = Stats.HATCHET_TOSSER_MAX_AMMO;
		fireRate = Stats.HATCHET_TOSSER_FIRE_RATE;
		reloadTime = Stats.HATCHET_TOSSER_RELOAD_TIME;
		clips = Stats.HATCHET_TOSSER_INITIAL_CLIPS;
	}
	
	protected Projectile buildProjectile(double angle) {
		return new Hatchet(owner, angle);
	}
}