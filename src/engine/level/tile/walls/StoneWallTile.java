package engine.level.tile.walls;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class StoneWallTile extends Tile {
	
	public StoneWallTile(int x, int y) {
		super(x, y, Sprite.STONE_WALL_TILE, true, false);
	}

}
