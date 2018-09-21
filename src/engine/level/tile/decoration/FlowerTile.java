package engine.level.tile.decoration;

import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class FlowerTile extends Tile {
	
	public FlowerTile(int x, int y) {
		super(x, y, Sprite.FLOWER_TILE, false, true);
	}
}
