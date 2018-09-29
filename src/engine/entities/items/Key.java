package engine.entities.items;

import java.awt.Rectangle;

import engine.behaviors.Collideable;
import engine.graphics.Sprite;

public class Key extends Item implements Collideable {

	public Key(int x, int y) {
		super(x, y, Sprite.KEY_INVENTORY, Sprite.KEY_OVERWORLD);
	}

	public boolean collides(Collideable col) {
		int px = (int) (col.getX() - col.getWidth()/2);
		int py = (int) (col.getY() - col.getHeight()/2);
		Rectangle thisRect = new Rectangle((int) x, (int) y, width, height);
		Rectangle otherRect = new Rectangle(px, py, col.getWidth(), col.getHeight());
		return thisRect.intersects(otherRect);
	}

}
