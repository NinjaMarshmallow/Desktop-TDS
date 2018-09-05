package engine.entities.mobs;

import util.Color;
import engine.entities.gauges.Healthbar;
import engine.graphics.Sprite;

public class Enemy extends Mob {
	
	public Enemy() {
		super();
	}
	
	public Enemy(double x, double y, int width, int height) {
		super(x, y, width, height);
		xSpeed = speed = 5;
		health = maxHealth = 100;
		healthbar = new Healthbar(this);
		sprite = new Sprite(width, height, Color.RED);
	}
}
