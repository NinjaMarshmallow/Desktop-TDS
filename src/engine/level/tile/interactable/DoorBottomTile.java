package engine.level.tile.interactable;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class DoorBottomTile extends Tile {
	
	public DoorBottomTile(int x, int y) {
		super(x, y, Sprite.DOOR_BOTTOM_TILE, true, false);
	}
	
	public void open() {
		this.solid = false;
		this.traversable = true;
	}

}
