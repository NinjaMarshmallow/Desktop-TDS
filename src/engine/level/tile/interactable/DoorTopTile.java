package engine.level.tile.interactable;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class DoorTopTile extends Tile {
	
	public DoorTopTile(int x, int y) {
		super(x, y, Sprite.DOOR_TOP_TILE, true, false);
	}
	
	public void open() {
		this.solid = false;
		this.traversable = true;
	}

}
