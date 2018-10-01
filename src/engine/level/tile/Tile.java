package engine.level.tile;

import implementation.Screen;

import java.awt.Rectangle;

import engine.behaviors.Collideable;
import engine.behaviors.Drawable;
import engine.behaviors.Positionable;
import engine.graphics.Sprite;

public class Tile implements ITile, Collideable, Drawable {
//	public static Tile GRASS = new Tile(Color.GRASS, Sprite.GRASS_TILE, false, true);
//	public static Tile DIRT = new Tile(Color.DIRT, Sprite.DIRT_TILE, false, true);
//	public static Tile DARK_GRASS = new Tile(Color.DARK_GRASS, Sprite.DARK_GRASS_TILE, false, true);
//	public static Tile SAND = new Tile(Color.SAND, Sprite.SAND_TILE, false, true);
//	public static Tile WATER = new Tile(Color.WATER, Sprite.WATER_TILE, false, false);
//	public static Tile BRICK_WALL = new Tile(Color.BRICK, Sprite.BRICK_TILE, true, false);
//	public static Tile STONE_WALL = new Tile(Color.STONE_WALL, Sprite.STONE_WALL_TILE, true, false);
//	public static Tile FLOWER = new Tile(Color.FLOWER, Sprite.FLOWER_TILE, false, true);
//	public static Tile VOID = new Tile(Color.PINK, Sprite.VOID_TILE, false, true);
	protected int x, y, width, height;
	protected Sprite sprite;
	protected boolean solid, traversable;
	protected int time = 0;
	public Tile() {
		sprite = Sprite.VOID_TILE;
		solid = false;
		traversable = !solid;
		initialize();
	}
	
	public Tile(int type, Sprite sprite, boolean solid, boolean traversable) {
		this.sprite = sprite;
		this.solid = solid;
		this.traversable = traversable;
		initialize();
	}
	
	public Tile(int x, int y, Sprite sprite, boolean solid, boolean traversable) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = solid;
		this.traversable = traversable;
		initialize();
	}
	
	private void initialize() {
		width = sprite.getWidth();
		height = sprite.getHeight();
		
	}
	
	public boolean isSolid() {
		return solid;
	}

	public boolean isTraversable() {
		return traversable;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public void setX(double x) {
		this.x = (int)x;
	}

	public void setY(double y) {
		this.y = (int)y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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

	public boolean collides(Collideable col) {
		int px = (int) (col.getX() - col.getWidth()/2);
		int py = (int) (col.getY() - col.getHeight()/2);
		Rectangle thisRect = new Rectangle((int) x - width/2, (int) y - height/2, width, height);
		Rectangle otherRect = new Rectangle(px, py, col.getWidth(), col.getHeight());
		return thisRect.intersects(otherRect);
	}

	public void update() {
		time++;
		if(time > 7000) {
			time = 0;
		}
	}

	public void draw(Screen screen) {
		screen.renderTile(this);
	}

	public int readPixel(int x, int y) {
		return sprite.getPixelAt(x, y);
	}

	public boolean isAlive() {
		return true;
	}
}
