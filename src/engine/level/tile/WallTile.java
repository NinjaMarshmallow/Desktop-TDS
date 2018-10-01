package engine.level.tile;

import engine.graphics.Sprite;

public class WallTile extends Tile {
	
	public WallTile(int x, int y, Sprite sprite) {
		super(x, y, sprite, true, false);
	}
}
