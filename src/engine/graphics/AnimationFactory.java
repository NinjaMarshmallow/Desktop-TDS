package engine.graphics;

import java.util.Arrays;

import engine.entities.projectiles.Fireball;
import engine.entities.projectiles.Projectile;

public class AnimationFactory {
	
	public AnimationFactory() {
		
	}
	
	public static AnimatedSprite createAnimation(int type) {
		switch(type) {
		case AnimatedSprite.EXPLOSION:
			return new AnimatedSprite(Arrays.asList(Sprite.EXPLOSION_1, Sprite.EXPLOSION_2, Sprite.EXPLOSION_3));
		case AnimatedSprite.WATERMELON:
			return new AnimatedSprite(Arrays.asList(Sprite.MELON_EXPLOSION_1, Sprite.MELON_EXPLOSION_2, Sprite.MELON_EXPLOSION_3), 0.7);
		case AnimatedSprite.ELECTRIC:
			return new AnimatedSprite(Arrays.asList(Sprite.ELECTRIC_EXPLOSION_1, Sprite.ELECTRIC_EXPLOSION_2, Sprite.ELECTRIC_EXPLOSION_3), 1.2);
		default:
			return new AnimatedSprite();
		}
	}
	
	public static AnimatedSprite createAnimation(Projectile p) {
		if(p instanceof Fireball) return new AnimatedSprite(Arrays.asList(Sprite.EXPLOSION_1, Sprite.EXPLOSION_2, Sprite.EXPLOSION_3));
		return new AnimatedSprite();
	}

}
