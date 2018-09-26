package engine.entities.mobs;

import java.util.Random;

import engine.behaviors.move.FollowPlayer;
import engine.behaviors.move.MoveInCircles;
import engine.graphics.Sprite;

public class HopeStudent extends Chaser {

	public HopeStudent() {
		super();
	}
	
	public HopeStudent(double x, double y) {
		super(x, y, Sprite.HOPE_STUDENT);
		if(!players.isEmpty()) {
			setTargetPlayer(players.get(new Random().nextInt(players.size())));
		}
	}
	
	public void update() {
		super.update();
//		if(!players.isEmpty() && this.moveBehavior instanceof MoveInCircles) {
//			setTargetPlayer(players.get(new Random().nextInt(players.size())));
//		}
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}
}
