package engine.level.tile;

import util.Color;
import engine.graphics.Sprite;

public class Tile implements ITile {
	public static Tile GRASS = new Tile(Color.GRASS, Sprite.GRASS_TILE, false, true);
	public static Tile DIRT = new Tile(Color.DIRT, Sprite.DIRT_TILE, false, true);
	public static Tile VOID = new Tile(Color.NO_DRAW, Sprite.VOID_TILE, false, true);
	
	private int type;
	private Sprite sprite;
	private boolean solid, traversable;
	
	public Tile() {
		type = Color.NO_DRAW_PINK;
		sprite = Sprite.VOID_TILE;
		solid = false;
		traversable = !solid;
	}
	
	public Tile(int type, Sprite sprite, boolean solid, boolean traversable) {
		this.type = type;
		this.sprite = sprite;
		this.solid = solid;
		this.traversable = traversable;
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
