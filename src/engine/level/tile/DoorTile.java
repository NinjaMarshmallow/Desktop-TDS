package engine.level.tile;

import java.util.ArrayList;
import java.util.List;

import util.Environment;
import engine.graphics.Sprite;

public class DoorTile extends WallTile {
	private Sprite openSprite, closedSprite;
	private List<DoorTile> components;
	private boolean isOpen = false;
	public DoorTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		this.closedSprite = sprite;
		this.openSprite = Sprite.STONE_PATH_TILE;
		components = new ArrayList<DoorTile>();
	}
	
	public void update() {
		super.update();
		if (isOpen && time % (5 * Environment.getInstance().getFPS()) == 0) {
			close();
		}
	}
	
	public void open() {
		isOpen = true;
		solid = false;
		traversable = true;
		sprite = openSprite;
		time = 0;
		openComponents();
	}
	
	public void close() {
		isOpen = false;
		solid = true;
		traversable = false;
		sprite = closedSprite;
		closeComponents();
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void bind(DoorTile doortile) {
		this.components.add(doortile);
		if(!doortile.hasComponent(this))doortile.bind(this);
	}
	
	public boolean hasComponent(DoorTile doortile) {
		return components.contains(doortile);
	}
	
	public void openComponents() {
		for(int i = 0; i < components.size(); i++) {
			DoorTile door = components.get(i);
			if(!door.isOpen())door.open();
		}
	}
	
	public void closeComponents() {
		for(int i = 0; i < components.size(); i++) {
			DoorTile door = components.get(i);
			if(door.isOpen()) door.close();
		}
	}
	
}
