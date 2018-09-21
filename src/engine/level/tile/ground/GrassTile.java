package engine.level.tile.ground;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class GrassTile extends Tile {
	
	public GrassTile(int x, int y) {
		super(x, y, Sprite.GRASS_TILE, false, true);
	}

}
