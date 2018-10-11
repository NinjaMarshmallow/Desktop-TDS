package engine.entities.mobs;

import java.util.Random;

import util.Stats;
import engine.behaviors.move.FollowPlayer;
import engine.behaviors.move.MoveInCircles;
import engine.graphics.Sprite;

public class HopeStudent extends Chaser {

	public HopeStudent() {
		super();
	}
	
	public HopeStudent(double x, double y) {
		super(x, y, Sprite.HOPE_STUDENT, Stats.HOPE_STUDENT_RANGE);
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}
}
