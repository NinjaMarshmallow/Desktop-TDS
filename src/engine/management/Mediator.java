package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import util.Printer;
import engine.behaviors.Drawable;
import engine.behaviors.PlayerObserver;
import engine.entities.Entity;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Player;
import engine.entities.projectiles.Projectile;
import engine.graphics.AnimatedSprite;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Drawable> entities;
	private List<Projectile> projectiles;
	private List<Enemy> enemies;
	private List<Player> players;
	private List<AnimatedSprite> animations;
	
	private List<PlayerObserver> playerObservers;
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
		lists = new ArrayList<List<?>>();
		lists.add(entities);
		lists.add(projectiles);
		lists.add(enemies);
		lists.add(players);
		
		playerObservers = new ArrayList<PlayerObserver>();

	}

	public void update() {
		Printer.print(projectiles.size());
		updateList(entities);
		updateList(projectiles);
		updateList(enemies);
		updateList(players);
		updateList(animations);
		collideProjectilesWithEnemies();
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

	public void add(Drawable e) {
		if (e instanceof Projectile) {
			if (projectiles.size() < PROJECTILE_LIMIT)
				projectiles.add((Projectile) e);
		} else if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
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
	
	public void notifyPlayerObservers() {
		for(int i = 0;i < playerObservers.size(); i++) {
			PlayerObserver en = playerObservers.get(i);
			en.notify(new ArrayList<Player>(players));
		}
	}
}
