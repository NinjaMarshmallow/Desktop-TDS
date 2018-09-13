package engine.entities.weapons;

import util.Environment;
import util.Printer;
import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Projectile;
import engine.management.Mediator;

public class Shooter implements Weapon {

	protected Mediator mediator;
	protected Entity owner;
	protected double fireRate;
	protected double shootTimer, reloadTimer;
	protected int ammo, maxAmmo, clips, reloadTime;

	protected boolean unlimited = true;

	public Shooter(Entity owner) {
		this.owner = owner;
		mediator = Mediator.getInstance();
		fireRate = Stats.DEFAULT_FIRE_RATE;
		ammo = maxAmmo = Stats.DEFAULT_MAX_AMMO;
		clips = Stats.DEFAULT_INITIAL_CLIPS;
		reloadTime = Stats.DEFAULT_RELOAD_TIME;
		shootTimer = 0;
		reloadTimer = 0;
	}

	protected Projectile buildProjectile(double angle) {
		return new Projectile(owner, angle);
	}

	public void shoot(double angle) {
		// Firerate is defined as the number of shots off in 1 second
		if (reloadTimer <= 0) {
			if (hasAmmo()) {
				if (shootTimer % (int) (Environment.getInstance().getFPS()/fireRate) == 0) {
					mediator.add(buildProjectile(angle));
					if (!unlimited)
						ammo--;
				}
				shootTimer += 1;
			} else {
				reload();
			}
		} else {
			reloadTimer--;
		}
	}

	public boolean hasAmmo() {
		return ammo > 0;
	}

	private void reload() {
		if (clips > 0) {
			reloadTimer = reloadTime;
			ammo = maxAmmo;
			clips--;
		} else {
			Printer.print("Out of Ammo...");
		}
	}
}