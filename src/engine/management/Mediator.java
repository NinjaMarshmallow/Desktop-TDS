package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Printer;
import engine.entities.Entity;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Mob;
import engine.entities.mobs.Player;
import engine.entities.projectiles.Projectile;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Entity> entities;
	private List<Projectile> projectiles;
	private List<Enemy> enemies;
	private List<Player> players;
	private static Mediator instance;

	public static Mediator getInstance() {
		if (instance == null)
			instance = new Mediator();
		return instance;
	}

	private Mediator() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		players = new ArrayList<Player>();
		
		lists = new ArrayList<List<?>>();
		lists.add(entities);
		lists.add(projectiles);
		lists.add(enemies);
		lists.add(players);

	}

	public void update() {
		Printer.print(projectiles.size());
		updateList(entities);
		updateList(projectiles);
		updateList(enemies);
		updateList(players);
		collideProjectilesWithEnemies();
		//collideEnemiesWithPlayer();

	}

	private void updateList(List<?> list) {
		List<Entity> dead = new ArrayList<Entity>();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Entity element = (Entity) iterator.next();
			element.update();
			if (!element.isAlive()) dead.add(element);
		}
		synchronized(list) {
			list.removeAll(dead);
		}
		
	}
	
	private void collideProjectilesWithEnemies() {
		for(Projectile p : projectiles) {
			for(Enemy e : enemies) {
				double dist = e.distanceTo(p);
				double maximumCollisionDistance = e.getSize() + p.getSize(); 
				if(dist > maximumCollisionDistance) continue;
				if(e.collides(p)) p.hit(e);
			}
		}
	}
	
	public void add(Entity e) {
		if (e instanceof Projectile) {
			if (projectiles.size() < PROJECTILE_LIMIT) projectiles.add((Projectile) e);
		} else if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}
	
	public void removeEntity(Entity e) {
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
			for(Object e : list) {
				((Entity) e).draw(screen);
			}
		} catch (Exception e) {
		e.printStackTrace();
		Printer.print("Concurrent Modification Error...",
				Printer.FLAGS.ERROR);
		}
	}
}
