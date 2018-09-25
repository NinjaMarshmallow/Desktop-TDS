package engine.behaviors;

import java.util.List;

import engine.level.tile.Tile;

public interface TileObserver {
	
	public void notifyOfTiles(List<Tile> tiles);

}
