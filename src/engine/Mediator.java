package engine;

import implementation.Screen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import util.Printer;

public class Mediator {
	private static final int PROJECTILE_LIMIT = 200;
	private List<List<?>> lists;
	private List<Entity> entities;
	private List<Projectile> projectiles;
	private List<Mob> enemies;
	private static Mediator instance;
	
	
	public static Mediator getInstance() {
		if (instance == null) instance = new Mediator();
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
		
	}
	
	public void add(Entity e) {
		Printer.print("Entity Added" + e);
		if(e instanceof Projectile) {
			if(projectiles.size() < PROJECTILE_LIMIT) projectiles.add((Projectile) e);
		} else if (e instanceof Mob) {
			enemies.add((Mob) e); 
		} else {
			entities.add(e);
		}
	}
	
	public void update() {
		Printer.print(projectiles.size());
		List<Entity> dead = new ArrayList<Entity>();
		for(Entity e : entities) {
			e.update();
		}
		//Use this for safe deletion for enemies and projectiles otherwise Concurrent Modification Exception
//		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
//		    String string = iterator.next();
//		    if (string.isEmpty()) {
//		        // Remove the current element from the iterator and the list.
//		        iterator.remove();
//		    }
//		}
		for(Projectile p : projectiles) {
			p.update();
			if(!p.onScreen()) dead.add(p);
		}
		for(Mob m : enemies) {
			m.update();
			if(!m.isAlive()) dead.add(m);
		}
		
		for(Entity e : dead) {
			removeEntity(e);
		}
		dead.clear();
	}
	
	public void draw(Screen screen) {
		for(List<?> list : lists) {
			for(Object e : list) {
				((Entity) e).draw(screen);
			}
		}
	}
	
	public void removeEntity(Entity e) {
		Printer.print("Entity Removed" + e);
		if(e instanceof Projectile) {
			projectiles.remove(e);
		} else if (e instanceof Mob) {
			enemies.remove(e); 
		} else {
			entities.remove(e);
		}
	}
}
