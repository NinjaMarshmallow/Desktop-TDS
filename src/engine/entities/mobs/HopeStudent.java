package engine.entities.mobs;

import engine.behaviors.FollowPlayer;
import engine.graphics.Sprite;

public class HopeStudent extends Chaser {

	public HopeStudent() {
		super();
	}
	
	public HopeStudent(double x, double y) {
		super(x, y, Sprite.HOPE_STUDENT);
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}
}
