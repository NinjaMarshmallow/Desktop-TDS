package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import util.Printer;
import engine.behaviors.Drawable;
import engine.behaviors.PlayerObserver;
import engine.behaviors.TileObserver;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Player;
import engine.entities.projectiles.Projectile;
import engine.graphics.AnimatedSprite;
import engine.level.tile.Tile;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Drawable> entities;
	private List<Projectile> projectiles;
	private List<Enemy> enemies;
	private List<Player> players;
	private List<Tile> tiles;
	private List<AnimatedSprite> animations;

	private List<PlayerObserver> playerObservers;
	private List<TileObserver> tileObservers;
	private static Mediator instance;

	public static Mediator getInstance() {
		if (instance == null)
			instance = new Mediator();
		return instance;
	}

	private Mediator() {
		entities = new ArrayList<Drawable>();
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		players = new ArrayList<Player>();
		animations = new ArrayList<AnimatedSprite>();
		tiles = new ArrayList<Tile>();
		lists = new ArrayList<List<?>>();
		lists.add(entities);
		lists.add(projectiles);
		lists.add(enemies);
		lists.add(players);
		lists.add(tiles);

		playerObservers = new ArrayList<PlayerObserver>();
		tileObservers = new ArrayList<TileObserver>();

	}

	public void update() {
		Printer.print(projectiles.size());
		for (int i = 0; i < lists.size(); i++) {
			updateList(lists.get(i));
		}
		collideProjectilesWithEnemies();
		//collidePlayerWithSolids();
		collideProjectilesWithSolids();
		// collideEnemiesWithPlayer();

	}

	private void updateList(List<?> list) {
		try {
			List<Drawable> dead = new ArrayList<Drawable>();
			for (int i = 0; i < list.size(); i++) {
				Drawable drawable = ((Drawable) list.get(i));
				drawable.update();
				if (!drawable.isAlive())
					dead.add(drawable);
			}
			synchronized (list) {
				list.removeAll(dead);
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
			Printer.print("Error caught and ignored");
		}
		notifyPlayerObservers();
		notifyTileObservers();
	}

	private void collideProjectilesWithEnemies() {
		for (int i = 0; i < projectiles.size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {
				Projectile p = projectiles.get(i);
				Enemy e = enemies.get(j);
				double dist = e.distanceTo(p);
				double maximumCollisionDistance = e.getSize() + p.getSize();
				if (dist > maximumCollisionDistance)
					continue;
				if (e.collides(p))
					p.hit(e);
			}
		}
	}

//	private void collidePlayerWithSolids() {
//		for (int p = 0; p < players.size(); p++) {
//			for (int t = 0; t < tiles.size(); t++) {
//				Tile tile = tiles.get(t);
//				if (tile.isTraversable())
//					continue;
//				if (!tile.isSolid())
//					continue;
//				Player player = players.get(p);
//				if (player.collides(tile)) {
//					if (player.collideX(tile)) {
//						boolean movingLeft = player.getXSpeed() < 0;
//						boolean movingRight = player.getXSpeed() > 0;
//
//						if (movingLeft) {
//							player.setX(player.getX() - player.getXSpeed());
//						} else if (movingRight) {
//							player.setX(player.getX() - player.getXSpeed());
//						}
//					}
//					if (player.collideY(tile)) {
//						boolean movingUp = player.getYSpeed() < 0;
//						boolean movingDown = player.getYSpeed() > 0;
//						if (movingUp) {
//							player.setY(player.getY() - player.getYSpeed());
//						} else if (movingDown) {
//							player.setY(player.getY() - player.getYSpeed());
//						}
//
//					}
//				}
//			}
//		}
//	}

	private void collideProjectilesWithSolids() {
		for (int i = 0; i < projectiles.size(); i++) {
			for (int t = 0; t < tiles.size(); t++) {
				Tile tile = tiles.get(t);
				if (tile.isTraversable())
					continue;
				if (!tile.isSolid())
					continue;
				Projectile projectile = projectiles.get(i);
				if (projectile.collides(tile)) {
					projectile.kill();
				}
			}
		}
	}

	public void add(Drawable e) {
		if (e instanceof Projectile) {
			if (projectiles.size() < PROJECTILE_LIMIT)
				projectiles.add((Projectile) e);
		} else if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Tile) {
			tiles.add((Tile) e);
		} else {
			entities.add(e);
		}
	}

	public void remove(Drawable e) {
		if (e instanceof Projectile) {
			projectiles.remove(e);
		} else if (e instanceof Enemy) {
			enemies.remove(e);
		} else if (e instanceof Player) {
			players.remove(e);
		} else if (e instanceof Tile) {
			tiles.remove(e);
		} else {
			entities.remove(e);
		}
	}

	public void drawEntities(Screen screen) {
		drawList(screen, entities);
	}

	public void drawProjectiles(Screen screen) {
		drawList(screen, projectiles);
	}

	public void drawEnemies(Screen screen) {
		drawList(screen, enemies);
	}

	public void drawPlayers(Screen screen) {
		drawList(screen, players);
	}

	private void drawList(Screen screen, List<?> list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				Drawable drawable = (Drawable) list.get(i);
				drawable.draw(screen);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Printer.print("Concurrent Modification Error...",
					Printer.FLAGS.ERROR);
		}
	}

	public void addPlayersObserver(PlayerObserver playerObserver) {
		playerObservers.add(playerObserver);
	}
	
	public void addTileObserver(TileObserver tileObserver) {
		tileObservers.add(tileObserver);
	}

	public void notifyPlayerObservers() {
		for (int i = 0; i < playerObservers.size(); i++) {
			PlayerObserver en = playerObservers.get(i);
			en.notifyOfPlayers(new ArrayList<Player>(players));
		}
	}
	public void notifyTileObservers() {
		for (int i = 0; i < tileObservers.size(); i++) {
			TileObserver en = tileObservers.get(i);
			en.notifyOfTiles(new ArrayList<Tile>(tiles));
		}
	}
}
