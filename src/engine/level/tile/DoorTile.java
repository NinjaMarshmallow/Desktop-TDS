package engine.level.tile;

import util.Environment;
import engine.graphics.Sprite;

public class DoorTile extends WallTile {
	private Sprite openSprite, closedSprite;
	public DoorTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		this.closedSprite = sprite;
		this.openSprite = Sprite.STONE_PATH_TILE;
	}
	
	public void update() {
		super.update();
		if (sprite == this.openSprite && time % (5 * Environment.getInstance().getFPS()) == 0) {
			this.close();
		}
	}
	
	public void open() {
		this.solid = false;
		this.traversable = true;
		this.sprite = openSprite;
		time = 0;
	}
	
	public void close() {
		this.solid = true;
		this.traversable = false;
		this.sprite = closedSprite;
	}
	
}
