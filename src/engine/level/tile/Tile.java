package engine.level.tile;

import util.Color;
import engine.graphics.Sprite;

public class Tile implements ITile {
	
	protected int type;
	protected Sprite sprite;
	protected boolean solid = false, traversable = !solid;
	public Tile() {
		type = Color.NO_DRAW;
		sprite = Sprite.VOID_TILE;
	}
	
	public int getType() {
		return type;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isTraversable() {
		return traversable;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
