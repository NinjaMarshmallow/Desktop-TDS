package engine.level.tile;

import engine.graphics.Sprite;


public interface ITile {
	
	public int getType();
	public Sprite getSprite();
	public boolean isSolid();
	public boolean isTraversable();
	
}
