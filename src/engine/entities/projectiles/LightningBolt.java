package engine.entities.projectiles;

import util.Stats;
import engine.entities.Entity;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;

public class LightningBolt extends Projectile {
	
	public LightningBolt(Entity owner, double angle) {
		super(owner, angle, Sprite.LIGHTNING);
		speed = Stats.LIGHTNING_SPEED;
		range = Stats.LIGHTNING_RANGE;
		power = Stats.LIGHTNING_POWER;
		hitAnimation = AnimationFactory.createAnimation(AnimatedSprite.ELECTRIC);
	}

}
