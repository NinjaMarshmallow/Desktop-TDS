package engine.entities.projectiles;

import java.util.Random;

import util.Stats;
import engine.entities.Entity;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;

public class Hatchet extends Projectile {
	private Sprite original;
	private int rotationRandomness = new Random().nextInt(10) - 5;
	public Hatchet(Entity owner, double angle) {
		super(owner, angle, Sprite.HATCHET);
		speed = Stats.HATCHET_SPEED;
		range = Stats.HATCHET_RANGE;
		power = Stats.HATCHET_POWER;
		hitAnimation = AnimationFactory.createAnimation(AnimatedSprite.HATCHET_BREAK);
		original = sprite;
		
	}
	
	public void update() {
		super.update();
		if(time % (60/4 + rotationRandomness) == 0) {
			sprite = sprite.rotate90CW();
		}
	}

}
