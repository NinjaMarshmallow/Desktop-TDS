package engine.level.tile;

import util.Color;
import engine.graphics.Sprite;

public class TileFactory {
	
	public static Tile createTile(int x, int y, int color) {
		switch(color) {
		case Color.GRASS:
			return new Tile(x, y, Sprite.GRASS_TILE, false, true);
		case Color.DIRT:
			return new Tile(x, y, Sprite.DIRT_TILE, false, true);
		case Color.DARK_GRASS:
			return new Tile(x, y, Sprite.DARK_GRASS_TILE, false, true);
		case Color.SAND:
			return new Tile(x, y, Sprite.SAND_TILE, false, true);
		case Color.WATER:
			return new Tile(x, y, Sprite.WATER_TILE, false, false);
		case Color.BRICK:
			return new WallTile(x, y, Sprite.BRICK_TILE);
		case Color.STONE_WALL:
			return new WallTile(x, y, Sprite.STONE_WALL_TILE);
		case Color.FLOWER:
			return new GroundTile(x, y, Sprite.FLOWER_TILE);
		case Color.STONE_PATH:
			return new GroundTile(x, y, Sprite.STONE_PATH_TILE);
		case Color.DOOR_TOP:
			return new DoorTile(x, y, Sprite.DOOR_TOP_TILE);
		case Color.DOOR_BOTTOM:
			return new DoorTile(x, y, Sprite.DOOR_BOTTOM_TILE);
		default:
			return new VoidTile(x, y);
		}
		
//		switch(color) {
//		case Color.GRASS:
//			return Tile.GRASS;
//		case Color.DIRT:
//			return Tile.DIRT;
//		case Color.DARK_GRASS:
//			return Tile.DARK_GRASS;
//		case Color.SAND:
//			return Tile.SAND;
//		case Color.WATER:
//			return Tile.WATER;
//		case Color.BRICK:
//			return Tile.BRICK_WALL;
//		case Color.STONE_WALL:
//			return Tile.STONE_WALL;
//		case Color.FLOWER:
//			return Tile.FLOWER;
//		default:
//			return Tile.VOID;
//		}
	}

}
