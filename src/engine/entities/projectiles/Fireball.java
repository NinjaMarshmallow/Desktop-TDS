package engine.entities.projectiles;

import util.Stats;
import engine.entities.Entity;
import engine.graphics.Sprite;

public class Fireball extends Projectile {

	public Fireball(Entity owner, double angle) {
		super(owner, angle, Sprite.FIREBALL);
		speed = Stats.FIREBALL_SPEED;
		range = Stats.FIREBALL_RANGE;
		power = Stats.FIREBALL_POWER;
	}
}
