package engine.level.tile;

import util.Color;
import engine.level.tile.decoration.FlowerTile;
import engine.level.tile.ground.DarkGrassTile;
import engine.level.tile.ground.DirtTile;
import engine.level.tile.ground.GrassTile;
import engine.level.tile.ground.SandTile;
import engine.level.tile.ground.StonePathTile;
import engine.level.tile.interactable.DoorBottomTile;
import engine.level.tile.interactable.DoorTopTile;
import engine.level.tile.walls.BrickWallTile;
import engine.level.tile.walls.StoneWallTile;

public class TileFactory {
	
	public static Tile createTile(int x, int y, int color) {
		switch(color) {
		case Color.GRASS:
			return new GrassTile(x, y);
		case Color.DIRT:
			return new DirtTile(x, y);
		case Color.DARK_GRASS:
			return new DarkGrassTile(x, y);
		case Color.SAND:
			return new SandTile(x, y);
		case Color.WATER:
			return new WaterTile(x, y);
		case Color.BRICK:
			return new BrickWallTile(x, y);
		case Color.STONE_WALL:
			return new StoneWallTile(x, y);
		case Color.FLOWER:
			return new FlowerTile(x, y);
		case Color.STONE_PATH:
			return new StonePathTile(x, y);
		case Color.DOOR_TOP:
			return new DoorTopTile(x, y);
		case Color.DOOR_BOTTOM:
			return new DoorBottomTile(x, y);
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
