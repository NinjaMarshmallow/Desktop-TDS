package engine.level.tile.ground;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class DarkGrassTile extends Tile {
	
	public DarkGrassTile(int x, int y) {
		super(x, y, Sprite.DARK_GRASS_TILE, false, true);
	}

}
