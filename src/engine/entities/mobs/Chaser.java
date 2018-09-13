package engine.entities.mobs;

import engine.behaviors.FollowPlayer;
import engine.graphics.Sprite;

public class Chaser extends Enemy {

	public Chaser() {
		super();
	}
	
	public Chaser(double x, double y, Sprite sprite) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
		this.sprite = sprite;
		moveBehavior = new FollowPlayer();
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}
}
