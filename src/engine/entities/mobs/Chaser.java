package engine.entities.mobs;

import util.Stats;
import engine.behaviors.move.FollowPlayer;
import engine.behaviors.move.MoveRandomly;
import engine.graphics.Sprite;

public class Chaser extends Enemy {
	private double range;
	public Chaser() {
		super();
	}
	
	public Chaser(double x, double y, Sprite sprite) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
		this.sprite = sprite;
		this.range = Stats.CHASER_RANGE;
		moveBehavior = new MoveRandomly();
	}
	
	public Chaser(double x, double y, Sprite sprite, double range) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
		this.sprite = sprite;
		this.range = range;
		moveBehavior = new MoveRandomly();
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}
	
	public double getRange() {
		return range;
	}
}
