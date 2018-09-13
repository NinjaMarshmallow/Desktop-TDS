package engine.entities.projectiles;

import util.Stats;
import engine.entities.Entity;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;

public class Watermelon extends Projectile{
	
	public Watermelon(Entity owner, double angle) {
		super(owner, angle, Sprite.WATERMELON);
		speed = Stats.WATERMELON_SPEED;
		range = Stats.WATERMELON_RANGE;
		power = Stats.WATERMELON_POWER;
		hitAnimation = AnimationFactory.createAnimation(AnimatedSprite.WATERMELON);
	}

}
