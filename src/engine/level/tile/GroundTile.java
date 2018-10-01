package engine.level.tile;

import engine.graphics.Sprite;

public class GroundTile extends Tile {
	
	public GroundTile(int x, int y, Sprite sprite) {
		super(x, y, sprite, false, true);
	}

}
