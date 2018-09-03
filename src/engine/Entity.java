package engine;

import implementation.Screen;
import util.Printer;
import engine.behaviors.Drawable;
import engine.behaviors.Positionable;

public class Entity implements Positionable, Drawable {
	protected double x, y;
	protected int width, height;
	protected Sprite sprite;

	public Entity() {
		x = y = width = height = 100;
		sprite = new Sprite(width, height, 0xFF0000);
	}

	public Entity(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		sprite = new Sprite(width, height, 0xFF0000);
	}
	
	public void update() {
		
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distanceTo(Positionable pos) {
		double xdiff = (this.x - pos.getX()) * (this.x - pos.getX());
		double ydiff = (this.y - pos.getY()) * (this.y - pos.getY());
		double result = Math.sqrt(xdiff + ydiff);
		Printer.print(result);
		return result;
	}

	public double angleTo(Positionable pos) {
		double xdiff = this.x - pos.getX();
		double ydiff = this.y - pos.getY();
		double result = Math.atan2(ydiff, xdiff);
		Printer.print(result);
		return result;
	}

	public void draw(Screen screen) {
		screen.renderEntity(this);
	}

	public int readPixel(int x, int y) {
		return sprite.getPixelAt(x, y);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean collide(Positionable pos) {
		return false;
	}
}
