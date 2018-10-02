package engine.behaviors.move;

import engine.entities.mobs.Chaser;
import engine.entities.mobs.Mob;
import engine.entities.mobs.NullPlayer;
import engine.entities.mobs.Player;

public class FollowPlayer implements MoveBehavior {
	private Player player;
	private MoveBehavior idle;
	public FollowPlayer() {
		this.player = new NullPlayer();
		idle = new MoveRandomly();
	}

	public FollowPlayer(Player player) {
		setPlayer(player);
		idle = new MoveRandomly();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void execute(Mob mob) {
		double distance = mob.distanceTo(player);
		Chaser chaser = (Chaser) mob;
		if (distance > chaser.getRange()) {
			idle.execute(mob);
		} else {
			if (distance > player.getSize()/4 + mob.getSize()/4) {
				double angle = mob.angleTo(player);
				double xSpeed = Math.cos(angle) * mob.getBaseSpeed();
				double ySpeed = Math.sin(angle) * mob.getBaseSpeed();
				mob.setXSpeed(xSpeed);
				mob.setYSpeed(ySpeed);
				
			} else {
				mob.setXSpeed(0);
				mob.setYSpeed(0);
			}
		}
//		if (mob.getX() < player.getX()) {
//			mob.setXSpeed(mob.getBaseSpeed());
//		} else if (mob.getX() > player.getX()) {
//			mob.setXSpeed(-mob.getBaseSpeed());
//		} else {
//			mob.setXSpeed(0);
//		}
//		if (mob.getY() < player.getY()) {
//			mob.setYSpeed(mob.getBaseSpeed());
//		} else if (mob.getY() > player.getY()) {
//			mob.setYSpeed(-mob.getBaseSpeed());
//		} else {
//			mob.setYSpeed(0);
//		}
	}
}
