package engine.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import util.Color;
import engine.level.Level;

public class Sprite {

	private int width, height;
	private int[] pixels;
	// Null Sprite
	public static Sprite NULL_SPRITE = new Sprite(100, 100, Color.NO_DRAW);
	// Entity Sprites
	public static Sprite EMMA = new Sprite("/mobs/emma.png");
	public static Sprite TYLER = new Sprite("/mobs/tyler.png");
	public static Sprite PURPLE_BOX = new Sprite("/mobs/box.png");
	
	public static Sprite PLAYER = TYLER;

	public static Sprite HOPE_STUDENT = new Sprite("/mobs/hope_student.png");

	public static Sprite LEVEL0 = new Sprite("/maps/level0.png");
	public static Sprite LEVEL1 = new Sprite("/maps/level1.png");
	public static Sprite MAP = new Sprite("/maps/map.png");

	// Projectile Sprites
	public static Sprite FIREBALL = new Sprite("/projectiles/fireball.png");
	public static Sprite WATERMELON = new Sprite("/projectiles/watermelon.png");
	public static Sprite LIGHTNING = new Sprite("/projectiles/bolt.png");

	// Animation Sprites

	// Fire Explosion
	public static Sprite EXPLOSION_1 = new Sprite("/anim/fire/fire01.png");
	public static Sprite EXPLOSION_2 = new Sprite("/anim/fire/fire02.png");
	public static Sprite EXPLOSION_3 = new Sprite("/anim/fire/fire03.png");

	// Watermelon Explosion
	public static Sprite MELON_EXPLOSION_1 = new Sprite("/anim/melon/melon_explosion01.png");
	public static Sprite MELON_EXPLOSION_2 = new Sprite("/anim/melon/melon_explosion02.png");
	public static Sprite MELON_EXPLOSION_3 = new Sprite("/anim/melon/melon_explosion03.png");
	
	//Electric Explosion
	public static Sprite ELECTRIC_EXPLOSION_1 = new Sprite("/anim/electric/electric01.png");
	public static Sprite ELECTRIC_EXPLOSION_2 = new Sprite("/anim/electric/electric02.png");
	public static Sprite ELECTRIC_EXPLOSION_3 = new Sprite("/anim/electric/electric03.png");
	

	// Tiles
	public static Sprite VOID_TILE = new Sprite(Level.TILE_SIZE,
			Level.TILE_SIZE, Color.PINK);
	public static Sprite GRASS_TILE = new Sprite("/tiles/grass.png");
	public static Sprite DARK_GRASS_TILE = new Sprite("/tiles/darkgrass.png");
	public static Sprite SAND_TILE = new Sprite("/tiles/sand.png");
	public static Sprite WATER_TILE = new Sprite("/tiles/water.png");
	public static Sprite BRICK_TILE = new Sprite("/tiles/brick.png");
	public static Sprite STONE_WALL_TILE = new Sprite("/tiles/stone.png");
	public static Sprite DIRT_TILE = new Sprite("/tiles/dirt.png");
	public static Sprite FLOWER_TILE = new Sprite("/tiles/flower.png", GRASS_TILE);
	public static Sprite ALT_GRASS_TILE = new Sprite("/tiles/grass2.png");
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		fill(color);
	}

	public Sprite(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public Sprite(String path) {
		load(path);
	}
	
	public Sprite(String path, Sprite background) {
		load(path);
		if(width != background.getWidth() || height != background.getHeight()) {
			throw new UnsupportedOperationException("Width and Height must match in order to create an overlay Sprite");
		}
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int color = pixels[x + y * width];
				if(color == Color.NO_DRAW_BLACK || color == Color.NO_DRAW_PINK) {
					pixels[x + y * width] = background.getPixelAt(x, y);
				}
			}
		}
	}

	public void fill(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public void fill(int color, int fillWidth) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < fillWidth; x++) {
				pixels[x + y * width] = color;
			}
		}
	}

	public int getPixelAt(int x, int y) {
		return pixels[x + y * width];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSize() {
		return width > height ? width : height;
	}

	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception q) {
			q.printStackTrace();
			System.out.println("File " + path + " not found! Using a blank orange 50px by 50px Sprite instead...");
			width = height = 50;
			pixels = new int[width * height];
			fill(Color.ORANGE);
		}
	}
	
	private void overlay(Sprite sprite) {
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++)
			pixels[x + y * width] = sprite.getPixelAt(x, y); 
		}
	}
}
