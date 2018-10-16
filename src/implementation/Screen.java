package implementation;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import util.Color;
import util.Keyboard;
import util.Mouse;
import util.text.TextObject;
import engine.entities.Entity;
import engine.graphics.Sprite;
import engine.level.tile.Tile;

public class Screen {
	private boolean debug;
	private int width, height;
	private BufferedImage image;
	private int[] pixels;
	private int[] pixelBuffer;
	private Canvas canvas;
	private JFrame window;
	private double xScroll, yScroll;
	private static String title;
	private List<TextObject> textObjects;
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
		textObjects = new ArrayList<TextObject>();
		debug = false;
		addInput();
		fill(0x0);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void addInput() {
		Mouse mouse = new Mouse();
		canvas.addKeyListener(Keyboard.getInstance());
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}

	public void clear() {
		int black = 0x0;
		this.fill(black);
		clearText();
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
		renderSprite((int)t.getX() - t.getWidth()/2, (int)t.getY() - t.getHeight()/2, t.getSprite());
	}
	
	
	public void renderSprite(int x, int y, Sprite sprite) {
		if(sprite == null) return;
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
		if(debug) {
			renderRectangleFrame(rect.x, rect.y, rect.width, rect.height, 0x0);
		}
	}
	
	public void renderRectangle(int xp, int yp, int width, int height, int fgColor, int bgColor) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xLocal = (int) xp + x;
				int yLocal = (int) yp + y;
				if(yLocal < 0 || yLocal > this.height) continue;
				if(xLocal < 0 || xLocal > this.width) continue;
				renderPixel(xLocal, yLocal, bgColor);
			}
		}
		renderRectangleFrame(xp, yp, width, height, fgColor);
	}
	
	public void renderRectangleFrame(int xp, int yp, int width, int height, int color) {
		for(int y = 0; y < height; y++) {
			int xLocal = (int) (xp - xScroll);
			int yLocal = (int) (yp + y - yScroll);
			if(yLocal < 0 || yLocal + height > this.height) continue;
			if(xLocal < 0 || xLocal + width > this.width) continue;
			renderPixel(xLocal, yLocal, color);
			renderPixel(xLocal + width, yLocal, color);
		}
		
		for(int x = 0; x < width; x++) {
			int xLocal = (int) (xp + x - xScroll);
			int yLocal = (int) (yp - yScroll);
			if(yLocal < 0 || yLocal + height > this.height) continue;
			if(xLocal < 0 || xLocal + width > this.width) continue;
			renderPixel(xLocal, yLocal, color);
			renderPixel(xLocal, yLocal + height, color);
		}
	}
	
	public void renderText(int x, int y, String text, Font font, int color) {
		textObjects.add(new TextObject(x, y, text, font, color));
	}
	
	public void renderText(TextObject to) {
		textObjects.add(to);
	}
	
	public void clearText() {
		textObjects.clear();
	}
	
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		for(int i = 0; i < pixelBuffer.length; i++) {
			pixelBuffer[i] = pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
		for(int i = 0; i < textObjects.size(); i++) {
			TextObject textObject = textObjects.get(i);
			g.setColor(new java.awt.Color(textObject.getColor()));
			g.setFont(textObject.getFont());
			FontMetrics fm = g.getFontMetrics();
			int stringWidth = fm.stringWidth(textObject.getText());
			g.drawString(textObject.getText(), textObject.getX() - stringWidth/2, textObject.getY() + fm.getAscent()/3);
		}
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
	
	public void printFPS(String fpsText) {
		window.setTitle(fpsText + " --- " + title);
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
		window.setTitle(title);
	}
}