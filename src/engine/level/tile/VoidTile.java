package engine.level.tile;

import engine.graphics.Sprite;
import util.Color;

public class VoidTile extends Tile {
	
	public VoidTile() {
		type = Color.NO_DRAW;
		sprite = Sprite.VOID_TILE;
	}
}
