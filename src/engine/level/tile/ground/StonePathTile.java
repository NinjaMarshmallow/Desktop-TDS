package engine.level.tile.ground;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class StonePathTile extends Tile {
	
	public StonePathTile(int x, int y) {
		super(x, y, Sprite.STONE_PATH_TILE, false, true);
	}

}
