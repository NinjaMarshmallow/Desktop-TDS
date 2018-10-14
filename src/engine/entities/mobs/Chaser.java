package engine.entities.mobs;

import java.util.Random;

import util.Environment;
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
		super(x, y, sprite);
		initialize(Stats.CHASER_RANGE);
	}
	
	public Chaser(double x, double y, Sprite sprite, double range) {
		super(x, y, sprite);
		initialize(range);
	}
	
	private void initialize(double range) {
		this.range = range;
		moveBehavior = new MoveRandomly();
	}
	
	public void update() {
		super.update();
		if(time % Environment.getInstance().getFPS() == 0 && !players.isEmpty() && !hasTarget()) {
			setTargetPlayer(players.get(new Random().nextInt(players.size())));
		}
	}
	
	public boolean hasTarget() {
		return target != null;
	}
	
	public Player getTarget() {
		return target;
	}
	
	public void setTargetPlayer(Player player) {
		target = player;
		moveBehavior = new FollowPlayer(player);
	}
	
	public double getRange() {
		return range;
	}
}
