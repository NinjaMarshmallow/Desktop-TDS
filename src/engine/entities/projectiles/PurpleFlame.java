package engine.entities.projectiles;

import util.Stats;
import engine.entities.Entity;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;

public class PurpleFlame extends Projectile {

	public PurpleFlame(Entity owner, double angle) {
		super(owner, angle, Sprite.PURPLE_FLAME);
		speed = Stats.FIREBALL_SPEED;
		range = Stats.FIREBALL_RANGE;
		power = Stats.DEFAULT_POWER;
		hitAnimation = AnimationFactory.createAnimation(AnimatedSprite.EXPLOSION);
	}
}
