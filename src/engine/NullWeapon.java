package engine;

import util.Printer;
import engine.behaviors.Weapon;

public class NullWeapon implements Weapon {

	public void shoot(double angle) {
		Printer.print("Pew Pew. This weapon is a Null Weapon");
	}

	public boolean hasAmmo() {
		return true;
	}

}
