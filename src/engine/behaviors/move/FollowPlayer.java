package engine.behaviors.move;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.TileObserver;
import engine.entities.mobs.Chaser;
import engine.entities.mobs.Mob;
import engine.entities.mobs.NullPlayer;
import engine.entities.mobs.Player;
import engine.level.tile.Tile;

public class FollowPlayer implements MoveBehavior, TileObserver{
	private Player player;
	private MoveBehavior idle;
	private List<Tile> tiles;
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
			List<Tile> collidingTiles = findCollidingTiles(mob);
			System.out.println(collidingTiles);
			if(!collidingTiles.isEmpty()) {
				adjustForObstacles(collidingTiles, mob);
			} else {
				moveDirectlyTowardsPlayer(mob);
			}
		}
	}

	public void notifyOfTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	private List<Tile> findCollidingTiles(Mob mob) {
		List<Tile> result = new ArrayList<Tile>();
		if(tiles != null) {
			for(int i = 0; i < tiles.size(); i++) {
				Tile tile = tiles.get(i);
				if(mob.collides(tile)) {
					result.add(tile);
				}
			}
		}
		return result;
	}
	
	private void adjustForObstacles(List<Tile> collidingTiles, Mob mob) {
		for(int i = 0; i < collidingTiles.size(); i++) {
			double direction = Math.signum(mob.getXSpeed());
			mob.setXSpeed(direction * mob.getBaseSpeed());
		}
	}
	
	private void moveDirectlyTowardsPlayer(Mob mob) {
		if (mob.distanceTo(player) > player.getSize()/4 + mob.getSize()/4) {
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
}
