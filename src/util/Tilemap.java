package util;

import engine.level.tile.DarkGrassTile;
import engine.level.tile.GrassTile;
import engine.level.tile.SandTile;
import engine.level.tile.Tile;
import engine.level.tile.VoidTile;

public class Tilemap {
	
	public static Tile getTileFromColor(int color) {
		switch(color) {
		case Color.GRASS:
			return new GrassTile();
		case Color.DARK_GRASS:
			return new DarkGrassTile();
		case Color.SAND:
			return new SandTile();
		default:
			return new VoidTile();
		}
	}
	
}
