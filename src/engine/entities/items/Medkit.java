package engine.entities.items;

import java.awt.Rectangle;

import engine.behaviors.Collideable;
import engine.graphics.Sprite;

public class Medkit extends Item implements Collideable {
	
	public Medkit(int x, int y) {
		super(x, y, Sprite.MEDKIT_INVENTORY, Sprite.MEDKIT_OVERWORLD);
		type = ItemType.IMMEDIATE_USE;
	}

	public boolean collides(Collideable col) {
		int px = (int) (col.getX() - col.getWidth()/2);
		int py = (int) (col.getY() - col.getHeight()/2);
		Rectangle thisRect = new Rectangle((int) x, (int) y, width, height);
		Rectangle otherRect = new Rectangle(px, py, col.getWidth(), col.getHeight());
		return thisRect.intersects(otherRect);
	}
	
	public void use() {
		if(owner != null && owner.isAlive()) {
			owner.heal(100);
		}
		super.use();
	}

}
