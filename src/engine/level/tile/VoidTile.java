package engine.level.tile;

import engine.graphics.Sprite;
import util.Color;

public class VoidTile extends Tile {
	
	public VoidTile(int x, int y) {
		super(x, y, Sprite.VOID_TILE, false, true);
	}
}
