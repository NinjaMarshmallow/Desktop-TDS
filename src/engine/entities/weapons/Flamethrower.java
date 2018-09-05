package engine.entities.weapons;

import util.Printer;
import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Fireball;
import engine.management.Mediator;

public class Flamethrower implements Weapon {
	
	private Mediator mediator;
	private Entity owner;
	private double fireRate;
	private double shootTimer, reloadTimer;
	private int ammo, maxAmmo, clips, reloadTime;
	
	private boolean unlimited = true;
	public Flamethrower(Entity owner) {
		this.owner = owner;
		mediator = Mediator.getInstance();
		fireRate = Stats.FLAMETHROWER_FIRE_RATE;
		ammo = maxAmmo = Stats.FLAMETHROWER_MAX_AMMO;
		clips = Stats.FLAMETHROWER_INITIAL_CLIPS;
		reloadTime = Stats.FLAMETHROWER_RELOAD_TIME;
		shootTimer = 0;
		reloadTimer = 0;
	}
	
	public void shoot(double angle) {
		if(reloadTimer <= 0) {
			if(hasAmmo()) {
				if(shootTimer % fireRate == 0) {
					mediator.add(new Fireball(owner, angle));
					if(!unlimited) ammo--;
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
	
	public void reload() {
		if(clips > 0) {
			reloadTimer = reloadTime;
			ammo = maxAmmo;
			clips--;
		} else {
			Printer.print("Out of Ammo...");
		}
	}
}