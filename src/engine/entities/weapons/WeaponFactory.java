package engine.entities.weapons;

import engine.behaviors.Weapon;

public class WeaponFactory {
	
	public static Weapon createWeapon(String weaponID) {
		Weapon weapon = new WatermelonLauncher();
		switch(weaponID) {
		case "WM":
			weapon = new WatermelonLauncher();
			break;
		case "FB":
			weapon = new Flamethrower();
			break;
		case "TB":
			weapon = new LightningShooter();
			break;
		case "HA":
			weapon = new HatchetTosser();
		}
		return weapon;
	}
}
