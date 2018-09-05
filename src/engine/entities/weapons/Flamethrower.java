package engine.entities.weapons;

import util.Environment;
import util.Printer;
import engine.behaviors.Weapon;
import engine.entities.Entity;
import engine.entities.projectiles.Fireball;
import engine.management.Mediator;

public class Flamethrower implements Weapon {
	
	private Mediator mediator;
	private Entity owner;
	private double fireRate, shootCounter; 
	private int ammo, maxAmmo, clips, reloadTimer;
	private boolean unlimited = true;
	public Flamethrower(Entity owner) {
		this.owner = owner;
		mediator = Mediator.getInstance();
		fireRate = 10; //10 times per second
		shootCounter = 0;
		ammo = maxAmmo = 5;
		clips = 3; // 3 Reloads
		reloadTimer = 0;
	}
	
	public void shoot(double angle) {
		if(reloadTimer <= 0) {
			if(hasAmmo()) {
				if(shootCounter % fireRate == 0) {
					mediator.add(new Fireball(owner, angle));
					if(!unlimited) ammo--;
				}
				shootCounter += 1;
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
			reloadTimer = 3 * Environment.getInstance().getFPS();
			ammo = maxAmmo;
			clips--;
		} else {
			Printer.print("Out of Ammo...");
		}
	}
}