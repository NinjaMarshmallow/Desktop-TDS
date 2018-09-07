package engine.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import engine.level.World;
import util.Color;

public class Sprite {

	private int width, height;
	private int[] pixels;

	public static Sprite PLAYER = new Sprite("/mobs/player.png");
	public static Sprite WORLD = new Sprite("/maps/world.png");
	
	//Tiles
	public static Sprite VOID_TILE = new Sprite(World.TILE_SIZE, World.TILE_SIZE, Color.PINK);
	public static Sprite GRASS_TILE = new Sprite("/tiles/grass.png");
	public static Sprite DARK_GRASS_TILE = new Sprite("/tiles/darkgrass.png");
	public static Sprite SAND_TILE = new Sprite("/tiles/sand.png");

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

	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception q) {
			q.printStackTrace();
			System.out
					.println("File "
							+ path
							+ " not found! Using a blank orange 50px by 50px Sprite instead...");
			width = height = 50;
			pixels = new int[width * height];
			fill(Color.ORANGE);
		}
	}
}
