package engine.entities.items;

import implementation.Screen;
import engine.behaviors.Drawable;
import engine.behaviors.Positionable;
import engine.entities.Entity;
import engine.graphics.Sprite;

public class Item implements Drawable, Positionable {
	
	protected Sprite inventorySprite;
	protected Sprite overworldSprite;
	protected Sprite currentSprite;
	protected double x, y;
	protected int width, height;
	protected Entity owner;
	
	public Item(int x, int y) {
		initialize(x, y, Sprite.NULL_ITEM_INVENTORY, Sprite.NULL_ITEM_OVERWORLD);
	}
	
	public Item(int x, int y, Sprite inventory, Sprite overworld) {
		initialize(x, y, inventory, overworld);
	}
	
	private void initialize(int x, int y, Sprite inventory, Sprite overworld) {
		this.x = x;
		this.y = y;
		this.width = overworld.getWidth();
		this.height = overworld.getHeight();
		this.inventorySprite = inventory;
		this.overworldSprite = this.currentSprite = overworld;
		this.owner = null;
	}
	
	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public void update() {
		if(this.owner != null) {
			currentSprite = inventorySprite;
		} else {
			currentSprite = overworldSprite;
		}
		this.width = currentSprite.getWidth();
		this.height = currentSprite.getHeight();
	}

	public void draw(Screen screen) {
		screen.renderSprite((int) (x - width/2), (int) (y - height/2), currentSprite);
	}

	public int readPixel(int x, int y) {
		return currentSprite.getPixelAt(x, y);
	}

	public boolean isAlive() {
		return true;
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
}