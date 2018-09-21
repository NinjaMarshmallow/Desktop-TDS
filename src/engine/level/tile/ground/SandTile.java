package engine.level.tile.ground;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class SandTile extends Tile {
	
	public SandTile(int x, int y) {
		super(x, y, Sprite.SAND_TILE, false, true);
	}

}
