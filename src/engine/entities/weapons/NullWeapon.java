package engine.entities.weapons;

import util.Printer;
import engine.behaviors.Weapon;
import engine.entities.Entity;

public class NullWeapon implements Weapon {
	private Entity owner;
	public void shoot(double angle) {
		Printer.print("Pew Pew. This weapon is a Null Weapon");
	}

	public boolean hasAmmo() {
		return true;
	}

	@Override
	public void setOwner(Entity en) {
		owner = en;
	}

}
