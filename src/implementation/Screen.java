package implementation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.Entity;

public class Screen {

	private int width, height;
	private BufferedImage image;
	private int[] pixels;
	private Canvas canvas;
	private JFrame window;

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
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		canvas.requestFocus();
	}
	
	public void addInput(Keyboard keyboard, Mouse mouse) {
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
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
		for (int y = 0; y < e.getHeight(); y++) {
			int yLocal = (int) e.getY() + y;
			if(yLocal < 0 || yLocal >= height) continue;
			for (int x = 0; x < e.getWidth(); x++) {
				int xLocal = (int) e.getX() + x;
				if(xLocal < 0 || xLocal >= width) continue;
				pixels[xLocal + yLocal * width] = e.readPixel(x, y);
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			Printer.print("Created Buffer Strategy");
			canvas.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
		g.dispose();
		bs.show();
	}
}