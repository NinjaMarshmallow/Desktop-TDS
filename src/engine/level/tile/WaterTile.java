package engine.level.tile;

import engine.graphics.Sprite;

public class WaterTile extends Tile {
	
	public WaterTile(int x, int y) {
		super(x, y, Sprite.WATER_TILE, false, false);
	}

}
