package engine.entities.mobs;

import implementation.Screen;
import util.Keyboard;
import engine.graphics.Sprite;

public class NullPlayer extends Player {
	public NullPlayer() {
		super(100, 100, Sprite.NULL_SPRITE, new Keyboard());
	}
	
	public NullPlayer(double x, double y, Sprite sprite, Keyboard keyboard) {
		super(x, y, sprite, keyboard);
	}

	public void update() {
		
	}
	
	public void draw(Screen screen) {
		
	}

}
