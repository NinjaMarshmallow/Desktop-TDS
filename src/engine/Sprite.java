package engine;

public class Sprite {

	private int width, height;
	private int[] pixels;

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
}
