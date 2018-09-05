package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Printer;
import engine.entities.Entity;
import engine.entities.mobs.Mob;
import engine.entities.projectiles.Projectile;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Entity> entities;
	private List<Projectile> projectiles;
	private List<Mob> enemies;
	private static Mediator instance;
	private boolean locked;

	public static Mediator getInstance() {
		if (instance == null)
			instance = new Mediator();
		return instance;
	}

	private Mediator() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Mob>();
		lists = new ArrayList<List<?>>();
		lists.add(entities);
		lists.add(projectiles);
		lists.add(enemies);
		locked = false;

	}

	public void add(Entity e) {
		Printer.print("Entity Added" + e);
		if (e instanceof Projectile) {
			if (projectiles.size() < PROJECTILE_LIMIT)
				projectiles.add((Projectile) e);
		} else if (e instanceof Mob) {
			enemies.add((Mob) e);
		} else {
			entities.add(e);
		}
	}

	public void update() {
		Printer.print(projectiles.size());
		// Use this for safe deletion for enemies and projectiles otherwise
		// Concurrent Modification Exception
		// for (Iterator<String> iterator = list.iterator();
		// iterator.hasNext();) {
		// String string = iterator.next();
		// if (string.isEmpty()) {
		// // Remove the current element from the iterator and the list.
		// iterator.remove();
		// }
		// }
		updateList(entities);
		updateList(projectiles);
		updateList(enemies);

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
	
	public void removeEntity(Entity e) {
		Printer.print("Entity Removed" + e);
		if (e instanceof Projectile) {
			projectiles.remove(e);
		} else if (e instanceof Mob) {
			enemies.remove(e);
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
