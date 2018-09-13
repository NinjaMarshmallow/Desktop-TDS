package engine.entities.mobs;

import util.Color;
import util.Keyboard;
import engine.graphics.Sprite;

public class NullPlayer extends Player {
	public NullPlayer() {
		super(100, 100, new Sprite(100, 100, Color.BLACK), new Keyboard());
	}
	
	public NullPlayer(double x, double y, Sprite sprite, Keyboard keyboard) {
		super(x, y, sprite, keyboard);
	}

	public void update() {
		
	}

}
