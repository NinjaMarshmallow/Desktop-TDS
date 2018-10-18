package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import util.Printer;
import engine.behaviors.Drawable;
import engine.behaviors.ItemObserver;
import engine.behaviors.PlayerObserver;
import engine.behaviors.TileObserver;
import engine.entities.Spawner;
import engine.entities.items.Item;
import engine.entities.mobs.Player;
import engine.entities.mobs.enemy.Enemy;
import engine.entities.projectiles.Projectile;
import engine.graphics.AnimatedSprite;
import engine.level.tile.Tile;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Drawable> drawables;
	private List<Projectile> projectiles;
	private List<Enemy> enemies;
	private List<Player> players;
	private List<Tile> tiles;
	private List<AnimatedSprite> animations;
	private List<Spawner> spawners;
	private List<Item> items;

	private List<PlayerObserver> playerObservers;
	private List<TileObserver> tileObservers;
	private List<ItemObserver> itemObservers;
	
	
	private static Mediator instance;

	public static Mediator getInstance() {
		if (instance == null)
			instance = new Mediator();
		return instance;
	}

	private Mediator() {
		drawables = new ArrayList<Drawable>();
		projectiles = new ArrayList<Projectile>();
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		animations = new ArrayList<AnimatedSprite>();
		spawners = new ArrayList<Spawner>();
		items = new ArrayList<Item>();
		tiles = new ArrayList<Tile>();
		lists = new ArrayList<List<?>>();
		lists.add(drawables);
		lists.add(projectiles);
		lists.add(animations);
		lists.add(enemies);
		lists.add(players);
		lists.add(tiles);
		lists.add(spawners);
		lists.add(items);

		playerObservers = new ArrayList<PlayerObserver>();
		tileObservers = new ArrayList<TileObserver>();
		itemObservers = new ArrayList<ItemObserver>();
	}

	public void update() {
		for (int i = 0; i < lists.size(); i++) {
			updateList(lists.get(i));
		}
		System.out.println(enemies.size());
		collideProjectilesWithEnemies();
		collideProjectilesWithSolids();
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
		notifyItemObservers();
	}

	private void collideProjectilesWithEnemies() {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if(p.getOwner() instanceof Player) {
				for (int j = 0; j < enemies.size(); j++) {
					Enemy e = enemies.get(j);
					double dist = e.distanceTo(p);
					double maximumCollisionDistance = e.getSize() + p.getSize();
					if (dist > maximumCollisionDistance)
						continue;
					if (e.collides(p))
						p.hit(e);
				}
			} else if (p.getOwner() instanceof Enemy){
				for(int j = 0; j < players.size(); j++) {
					Player player = players.get(j);
					double dist = player.distanceTo(p);
					double maximumCollisionDistance = player.getSize() + p.getSize();
					if (dist > maximumCollisionDistance)
						continue;
					if (player.collides(p))
						p.hit(player);
				}
			}
		}
	}

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
	
	public void spawnPlayers(int x, int y) {
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			player.heal(100);
			player.setX(x);
			player.setY(y);
		}
	}

	public void add(Drawable draw) {
		if (draw instanceof Projectile) {
			if (projectiles.size() < PROJECTILE_LIMIT)
				projectiles.add((Projectile) draw);
		} else if (draw instanceof Enemy) {
			enemies.add((Enemy) draw);
		} else if (draw instanceof Player) {
			players.add((Player) draw);
		} else if (draw instanceof Tile) {
			tiles.add((Tile) draw);
		} else if (draw instanceof AnimatedSprite) {
			animations.add((AnimatedSprite) draw);
		} else if (draw instanceof Spawner) {
			spawners.add((Spawner) draw);
		}  else if (draw instanceof Item) {
			items.add((Item) draw);
		} else {
			drawables.add(draw);
		}
	}

	public void remove(Drawable draw) {
		if (draw instanceof Projectile) {
			projectiles.remove(draw);
		} else if (draw instanceof Enemy) {
			enemies.remove(draw);
		} else if (draw instanceof Player) {
			players.remove(draw);
		} else if (draw instanceof Tile) {
			tiles.remove(draw);
		} else if (draw instanceof AnimatedSprite) {
			animations.remove(draw);
		} else if (draw instanceof Spawner) {
			spawners.remove(draw);
		} else if (draw instanceof Item) {
			items.remove((Item) draw);
		} else {
			drawables.remove(draw);
		}
	}
	
	public void clear() {
		projectiles.clear();
		enemies.clear();
		players.clear();
		tiles.clear();
		animations.clear();
		spawners.clear();
		items.clear();
	}
	
	public void clearLevel() {
		projectiles.clear();
		enemies.clear();
		tiles.clear();
		animations.clear();
		spawners.clear();
		items.clear();
		playerObservers.clear();
	}
	
	public void clearEnemies() {
		enemies.clear();
	}
	
	public void drawItems(Screen screen) {
		drawList(screen, items);
	}

	public void drawEntities(Screen screen) {
		drawList(screen, drawables);
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
	
	public void drawAnimations(Screen screen) {
		drawList(screen, animations);
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
	
	public void addItemObserver(ItemObserver itemObserver) {
		itemObservers.add(itemObserver);
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
	
	public void notifyItemObservers() {
		for (int i = 0; i < itemObservers.size(); i++) {
			ItemObserver en = itemObservers.get(i);
			en.notifyOfItems(new ArrayList<Item>(items));
		}
	}
}
