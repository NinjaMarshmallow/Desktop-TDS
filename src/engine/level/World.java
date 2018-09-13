package engine.level;

import implementation.Screen;
import util.Environment;
import util.Keyboard;
import util.Tilemap;
import engine.behaviors.FollowPlayer;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.HopeStudent;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.level.tile.Tile;
import engine.management.Mediator;

public class World {
	public static final int TILE_SIZE = 20;
	private double x, y;
	private int width, height, tileWidth, tileHeight;
	private Keyboard keyboard;
	private Player player;
	private Enemy enemy;
	private Mediator mediator;
	private Sprite tileSprite;
	private Tile[] tiles;
	
	private enum Direction { HORIZONTAL, VERTICAL };
	
	public World(Keyboard keyboard) {
		this.keyboard = keyboard;
		mediator = Mediator.getInstance();
		player = new Player(keyboard);
		enemy = new HopeStudent(500, 400);
		enemy.setMoveBehavior(new FollowPlayer(player));
		mediator.add(enemy);
		mediator.add(player);
		x = y = 0;
		tileSprite = Sprite.WORLD;
		tileWidth = tileSprite.getWidth();
		tileHeight = tileSprite.getHeight();
		width = tileWidth * TILE_SIZE;
		height = tileHeight * TILE_SIZE;
		
		tiles = new Tile[tileSprite.getWidth() * tileSprite.getHeight()];
		load();
	}
	
	private void load() {
		for(int y = 0; y < tileHeight; y++) {
			for(int x = 0; x < tileWidth; x++) {
				int color = tileSprite.getPixelAt(x, y);
				tiles[x + y * tileWidth] = Tilemap.getTileFromColor(color);
			}
		}
	}
	
	public void update() {
//		if(playerIsEdgeOfScreenX()) {
//			shift(Direction.HORIZONTAL, -player.getXSpeed());
//		} else if(playerIsEdgeOfScreenY()) {
//			shift(Direction.VERTICAL, -player.getYSpeed());
//		}
		mediator.update();
	}
	
	private boolean playerIsEdgeOfScreenX() {
		return player.getX() > (Environment.getInstance().getWidth() * 0.8) ||
			   player.getX() < (Environment.getInstance().getWidth() * 0.2);
	}
	
	private boolean playerIsEdgeOfScreenY() {
		return player.getY() > (Environment.getInstance().getHeight() * 0.8) ||
			   player.getY() < (Environment.getInstance().getHeight() * 0.2);
	}
	
	private void shift(Direction dir, double vel) {
		if(dir == Direction.HORIZONTAL) {
			x += vel;
		} else if (dir == Direction.VERTICAL) {
			y += vel;
		}
	}
	
	public void draw(Screen screen) {
		for(int y = (int)this.y; y < tileHeight; y++) {
			for(int x = (int)this.x; x < tileWidth; x++) {
				screen.renderSprite(x * TILE_SIZE, y * TILE_SIZE, tiles[x + y * tileWidth].getSprite());
			}
		}
		mediator.drawEntities(screen);
		mediator.drawEnemies(screen);
		mediator.drawPlayers(screen);
		mediator.drawProjectiles(screen);
	}
}
