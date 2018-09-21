package engine.level.tile;

import engine.graphics.Sprite;


public interface ITile {
	
	public Sprite getSprite();
	public boolean isSolid();
	public boolean isTraversable();
	
}
