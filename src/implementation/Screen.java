package implementation;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import util.Color;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.entities.Entity;
import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class Screen {

	private int width, height;
	private BufferedImage image;
	private int[] pixels;
	private int[] pixelBuffer;
	private Canvas canvas;
	private JFrame window;
	private double xScroll, yScroll;
	public static enum Direction { HORIZONTAL, VERTICAL };

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		window = new JFrame();
		canvas = new Canvas();
		window.add(canvas);
		window.setResizable(false);
		window.setPreferredSize(new Dimension(this.width, this.height));
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		pixelBuffer = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		pixels = new int[pixelBuffer.length];
		canvas.createBufferStrategy(3);
		canvas.requestFocus();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void addInput(Keyboard keyboard, Mouse mouse) {
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}

	public void clear() {
		int black = 0x0;
		this.fill(black);
	}

	public void fill(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public void renderEntity(Entity e) {
		renderSprite((int)e.getX(), (int)e.getY(), e.getSprite());
	}
	
	public void renderTile(Tile t) {
		renderSprite((int)t.getX(), (int)t.getY(), t.getSprite());
	}
	
	
	public void renderSprite(int x, int y, Sprite sprite) {
		for (int startY = 0; startY < sprite.getHeight(); startY++) {
			int yLocal = (int) (y + startY - yScroll);
			if(yLocal < 0 || yLocal >= height) continue;
			for (int startX = 0; startX < sprite.getWidth(); startX++) {
				int xLocal = (int) (x + startX - xScroll);
				if(xLocal < 0 || xLocal >= width) continue;
				renderPixel(xLocal, yLocal, sprite.getPixelAt(startX, startY));
			}
		}
	}
	
	public void renderHitbox(Rectangle rect) {
		for(int y = rect.y; y < rect.y + rect.height; y++) {
			if(rect.y < 0 || rect.y + rect.height > Environment.getInstance().getHeight()) continue;
			if(rect.x < 0 || rect.x + rect.width > Environment.getInstance().getWidth()) continue;
			pixels[rect.x + y * width] = 0x0;
			pixels[rect.x + rect.width + y * width] = 0x0;
		}
		
		for(int x = rect.x; x < rect.x + rect.width; x++) {
			if(rect.x < 0 || rect.x + rect.width > Environment.getInstance().getWidth()) continue;
			if(rect.y < 0 || rect.y + rect.height > Environment.getInstance().getHeight()) continue;
			pixels[x + rect.y * width] = 0x0;
			pixels[x + (rect.y + rect.height) * width] = 0x0;
		}
	}
	
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		for(int i = 0; i < pixelBuffer.length; i++) {
			pixelBuffer[i] = pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void renderPixel(int x, int y, int color) {
		if(color != Color.NO_DRAW_PINK && color != Color.NO_DRAW_BLACK) {
			int xPosition = x;
			int yPosition = y;
			int arrayLocation = xPosition + yPosition * width;
			if(arrayLocation >= 0 && arrayLocation < pixels.length) pixels[arrayLocation] = color;
		}
	}
	
	public void setScroll(int xScroll, int yScroll) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
	}
	
	public double getXScroll() {
		return xScroll;
	}
	
	public double getYScroll() {
		return yScroll;
	}
	
	public void shift(Direction dir, double vel) {
		if(dir == Direction.HORIZONTAL) {
			xScroll += vel;
		} else if (dir == Direction.VERTICAL) {
			yScroll += vel;
		}
	}
}