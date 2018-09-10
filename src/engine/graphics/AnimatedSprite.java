package engine.graphics;

import implementation.Screen;

import java.util.List;

import util.Color;
import engine.behaviors.Drawable;
import engine.management.Mediator;

public class AnimatedSprite extends Sprite implements Drawable {
	private Sprite currentSprite;
	private Sprite[] frames;
	private int time, currentFrame, animationSpeed;
	private int x, y;
	private boolean complete;
	
	public AnimatedSprite(List<Sprite> sprites) {
		super(sprites.get(0).getWidth(), sprites.get(0).getHeight(), Color.BLACK);
		frames = new Sprite[sprites.size()];
		currentSprite = frames[0];
		complete = false;
		time = currentFrame = 0;
		animationSpeed = 1;
	}
	
	public void play(int x, int y) {
		Mediator.getInstance().add(this);
		this.x = x;
		this.y = y;
	}

	public void update() {
		time++;
		if(complete) {
			Mediator.getInstance().remove(this);
		} else {
			if(time % ((int) (30/animationSpeed)) == 0) {
				currentSprite = frames[currentFrame++];
			}
			if(currentFrame >= frames.length - 1) complete = true;
		}
	}

	public void draw(Screen screen) {
		screen.renderSprite(x, y, currentSprite);
	}

	public int readPixel(int x, int y) {
		return currentSprite.getPixelAt(x, y);
	}
	
	
	
}
