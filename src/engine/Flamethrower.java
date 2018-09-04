package engine;

import engine.behaviors.Weapon;

public class Flamethrower implements Weapon {
	
	private Mediator mediator;
	private Entity owner;
	public Flamethrower(Entity owner) {
		this.owner = owner;
		mediator = Mediator.getInstance();
	}
	
	public void shoot(double angle) {
		mediator.add(new Fireball(owner, angle));
	}

	public boolean hasAmmo() {
		return true;
	}

}
