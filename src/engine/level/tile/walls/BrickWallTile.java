package engine.level.tile.walls;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class BrickWallTile extends Tile {
	
	public BrickWallTile(int x, int y) {
		super(x, y, Sprite.BRICK_TILE, true, false);
	}
}
