package engine.entities.mobs;

import util.Color;
import util.Stats;
import engine.entities.gauges.Healthbar;
import engine.graphics.Sprite;

public class Enemy extends Mob {
	
	public Enemy() {
		super();
	}
	
	public Enemy(double x, double y, int width, int height) {
		super(x, y, width, height);
		baseSpeed = Stats.ENEMY_SPEED;
		sprite = new Sprite(width, height, Color.RED);
	}
}
