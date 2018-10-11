package engine.entities.mobs;

import util.Stats;
import engine.behaviors.move.FollowPlayer;
import engine.behaviors.move.MoveRandomly;
import engine.graphics.Sprite;

public class Chaser extends Enemy {
	protected double range;
	protected Player target;
	public Chaser() {
		super();
	}
	
	public Chaser(double x, double y, Sprite sprite) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
		initialize(sprite, Stats.CHASER_RANGE);
	}
	
	public Chaser(double x, double y, Sprite sprite, double range) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
		initialize(sprite, range);
	}
	
	private void initialize(Sprite sprite, double range) {
		this.sprite = sprite;
		this.range = range;
		moveBehavior = new MoveRandomly();

	}
	
	public void setTargetPlayer(Player player) {
		target = player;
		moveBehavior = new FollowPlayer(player);
	}
	
	public double getRange() {
		return range;
	}
}
