package engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Color;

public class Sprite {

	private int width, height;
	private int[] pixels;
	
	public static Sprite PLAYER = new Sprite("/mobs/player.png");
	

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
		}catch(Exception q) {
			q.printStackTrace();
			System.out.println("File " + path + " not found! Using a blank orange 50px by 50px Sprite instead...");
			width = height = 50;
			pixels = new int[width * height];
			fill(Color.ORANGE);
		}
	}
}
