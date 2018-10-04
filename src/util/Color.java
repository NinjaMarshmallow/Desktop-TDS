package util;

public class Color {
	// This color will never be rendered, so it is transparent

	public static final int NO_DRAW_PINK = 0xFFEE00EE; // Alpha Channel for
														// comparing colors
	public static final int NO_DRAW_BLACK = 0xFF000007; // Alpha Channel for
														// comparing colors
	public static final int NO_DRAW = NO_DRAW_PINK;
	

	// Normal Colors
	public static final int BLACK = 0x0;
	public static final int WHITE = 0xFFFFFF;
	public static final int GRAY = 0x888888;
	public static final int LIGHT_GRAY = 0xBBBBBB;

	public static final int RED = 0xFF0000;
	public static final int GREEN = 0x00FF00;
	public static final int BLUE = 0x0000FF;

	public static final int ORANGE = 0xFFA000;
	public static final int PURPLE = 0x9900FF;
	public static final int YELLOW = 0xFFFF00;

	public static final int ROYAL_BLUE = 0x009DFF;
	public static final int FOREST_GREEN = 0x077700;
	public static final int GOLD = 0xF2DA00;

	public static final int LIME_GREEN = 0xCCFF00;
	public static final int PINK = 0xFF7AE8;
	public static final int SKY_BLUE = 0xBCFCFF;
	public static final int BROWN = 0x996600;
	public static final int WOOD_BROWN = 0xDD6600;
	public static final int WOOD_BROWN2 = 0xDD7700;

	public static final int LIMEADE = 0x80BB00;
	public static final int TAN = 0xDDBB88;
	public static final int LAVENDER = 0xDBBDDD;
	public static final int BRICK_RED = 0x990000;
	public static final int MAROON = 0x800000;

	// Tilemapping Colors
	public static final int GRASS = GREEN + 0xFF000000;
	public static final int DARK_GRASS = LIMEADE + 0xFF000000;
	public static final int BEACH = LAVENDER + 0xFF000000;
	public static final int WATER = BLUE + 0xFF000000;
	public static final int SAND = TAN + 0xFF000000;
	public static final int WALL = BLACK + 0xFF000000;
	public static final int BUSH = FOREST_GREEN + 0xFF000000;
	public static final int BRICK = BRICK_RED + 0xFF000000;
	public static final int STONE_WALL = GRAY + 0xFF000000;
	public static final int FLOWER = YELLOW + 0xFF000000;
	public static final int DIRT = BROWN + 0xFF000000;
	public static final int STONE_PATH = LIGHT_GRAY + 0xFF000000;
	public static final int DOOR_TOP = WOOD_BROWN + 0xFF000000;
	public static final int DOOR_BOTTOM = WOOD_BROWN2 + 0xFF000000;
	
	// Enemymapping Colors
	public static final int ENEMY = GREEN + 0xFF000000;
	public static final int HOPE_STUDENT = ORANGE + 0xFF000000;
	
	//Menu Colors
	public static final int MENU_BACKGROUND = SKY_BLUE;
	
	public static final int[] enemyColors = { ENEMY, HOPE_STUDENT };
	
}

