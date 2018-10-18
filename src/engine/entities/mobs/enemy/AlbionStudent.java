package engine.entities.mobs.enemy;

import util.Stats;
import engine.behaviors.attack.ShootProjectile;
import engine.behaviors.move.FollowPlayer;
import engine.entities.mobs.Player;
import engine.entities.weapons.PurpleFlameShooter;
import engine.graphics.Sprite;

public class AlbionStudent extends Chaser {
	public AlbionStudent() {
		super();
		meleeRate = 20;
	}
	
	public AlbionStudent(double x, double y) {
		super(x, y, Sprite.ALBION_STUDENT, Stats.HOPE_STUDENT_RANGE);
		attackBehavior = new ShootProjectile(this, new PurpleFlameShooter());
	}
	
	public void setTargetPlayer(Player player) {
		moveBehavior = new FollowPlayer(player);
	}

}
