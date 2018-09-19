package util;

import engine.level.tile.Tile;

public class Tilemap {
	
	public static Tile getTileFromColor(int color) {
		switch(color) {
		case Color.GRASS:
			return Tile.GRASS;
		case Color.DIRT:
			return Tile.DIRT;
		default:
			return Tile.VOID;
		}
	}
	
}
