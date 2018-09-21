package engine.level.tile.ground;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class DirtTile extends Tile {
	
	public DirtTile(int x, int y) {
		super(x, y, Sprite.DIRT_TILE, false, true);
	}

}
