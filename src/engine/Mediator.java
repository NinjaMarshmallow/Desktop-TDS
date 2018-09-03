package engine;

import implementation.Screen;

import java.util.ArrayList;
import java.util.List;

import util.Printer;

public class Mediator {
	
	private List<Entity> entities;
	private static Mediator instance;
	
	
	public static Mediator getInstance() {
		if (instance == null) {
			return new Mediator();
		} else {
			return instance;
		}
	}
	
	private Mediator() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
	}
	
	public void draw(Screen screen) {
		for(Entity e : entities) {
			e.draw(screen);
		}
	}
	
	public void removeEntity(Entity e) {
		if(entities.contains(e)) {
			entities.remove(e);
		} else {
			Printer.print(e + "is not in the Mediator list");
		}
	}
}
