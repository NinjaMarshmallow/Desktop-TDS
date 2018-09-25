package engine.entities;

import implementation.Screen;

import java.awt.Rectangle;
import java.util.List;

import util.Environment;
import engine.behaviors.Drawable;
import engine.behaviors.Positionable;
import engine.graphics.Sprite;

public class Entity implements Positionable, Drawable {
	protected double x, y;
	protected int width, height;
	protected Rectangle hitbox;
	protected Sprite sprite;
	protected int time;
	protected boolean alive = false;

	public Entity() {
		width = height = 100;
		sprite = new Sprite(width, height, 0xFF0000);
		initialize(100, 100);
	}
	
	public Entity(double x, double y) {
		initialize(x, y);
		width = height = 100;
		sprite = new Sprite(width, height, 0xFF0000);
	}

	public Entity(double x, double y, int width, int height) {
		initialize(x, y);
		this.width = width;
		this.height = height;
		sprite = new Sprite(width, height, 0xFF0000);
	}
	
	public Entity(double x, double y, Sprite sprite) {
		initialize(x, y);
		this.sprite = sprite;
		width = sprite.getWidth();
		height = sprite.getHeight();
		
	}
	
	private void initialize(double x, double y) {
		this.x = x;
		this.y = y;
		alive = true;
		hitbox = new Rectangle((int) x, (int) y, width, height);
	}
	
	public void update() {
		time++;
		setHitbox();
	}
	
	private void setHitbox() {
		hitbox.setBounds((int)(x - width/2), (int)(y - height/2), width, height);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
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
	
	public void kill() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public double distanceTo(Positionable pos) {
		double xdiff = (this.x - pos.getX()) * (this.x - pos.getX());
		double ydiff = (this.y - pos.getY()) * (this.y - pos.getY());
		double result = Math.sqrt(xdiff + ydiff);
		return result;
	}
	
	public double distanceTo(double x, double y) {
		double xdiff = (this.x - x) * (this.x - x);
		double ydiff = (this.y - y) * (this.y - y);
		double result = Math.sqrt(xdiff + ydiff);
		return result;
	}

	public double angleTo(Positionable pos) {
		double xdiff = pos.getX() - this.x;
		double ydiff = pos.getY() - this.y;
		double result = Math.atan2(ydiff, xdiff);
		return result;
	}
	
	public double angleTo(double x, double y) {
		double xdiff = x - this.x;
		double ydiff = y - this.y;
		double result = Math.atan2(ydiff, xdiff);
		return result;
	}

	public void draw(Screen screen) {
		screen.renderEntity(this);
		//screen.renderHitbox(hitbox);
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
	
	public int getSize() {
		return height > width ? height : width;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public boolean collides(Positionable pos) {
		int px = (int) (pos.getX() - pos.getWidth()/2);
		int py = (int) (pos.getY() - pos.getHeight()/2);
		Rectangle thisRect = new Rectangle((int) x - width/2, (int) y - height/2, width, height);
		Rectangle otherRect = new Rectangle(px, py, pos.getWidth(), pos.getHeight());
		return thisRect.intersects(otherRect);
	}
	
	public boolean onScreen() {
		Environment env = Environment.getInstance();
		if (x < 0 || x > env.getWidth() ||
			y < 0 || y > env.getHeight()) {
			return false;
		}
		return true;
	}

	public boolean collideX(Positionable pos) {
		int selfx1 = (int) x - height/2;
		int selfx2 = (int) x + height/2;
		int px1 = (int) (pos.getX() - pos.getHeight()/2);
		int px2 = px1 + pos.getHeight();
		if(selfx1 < px1 && px1 < selfx2 || selfx1 < px2 && px2 < selfx2 ) {
			return true;
		}
		return false;
	}
	
	public boolean collideY(Positionable pos) {
		int selfy1 = (int) y - height/2;
		int selfy2 = (int) y + height/2;
		int py1 = (int) (pos.getY() - pos.getHeight()/2);
		int py2 = py1 + pos.getHeight();
		if(selfy1 < py1 && py1 < selfy2 || selfy1 < py2 && py2 < selfy2 ) {
			return true;
		}
		return false;
	}
}
