package engine.level;

import implementation.Screen;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Tilemap;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.level.tile.Tile;
import engine.management.Mediator;

public class World {
	public static final int TILE_SIZE = Sprite.BRICK_TILE.getSize();
	private double x, y;
	private int width, height, tileWidth, tileHeight;
	private Keyboard keyboard;
	private Player player;
	private Enemy enemy;
	private Mediator mediator;
	private Sprite tileSprite;
	private Tile[] tiles;
	private Screen screen;
	private int time;
	
	public World(Keyboard keyboard, Screen screen) {
		this.keyboard = keyboard;
		this.screen = screen;
		mediator = Mediator.getInstance();
		player = new Player(keyboard);
		//enemy = new HopeStudent(500, 400);
		//enemy.setMoveBehavior(new FollowPlayer(player));
		//mediator.add(enemy);
		
		mediator.add(player);
		tileSprite = Sprite.WORLD;
		tileWidth = tileSprite.getWidth();
		tileHeight = tileSprite.getHeight();
		width = tileWidth * TILE_SIZE;
		height = tileHeight * TILE_SIZE;
		time = 0;
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
		mediator.update();
		scrollScreen();
	}
	
	private void scrollScreen() {
		screen.shift(Screen.Direction.HORIZONTAL, player.getXSpeed());
		screen.shift(Screen.Direction.VERTICAL, player.getYSpeed());
		Mouse.shift(Mouse.VERTICAL, player.getYSpeed());
		Mouse.shift(Mouse.HORIZONTAL, player.getXSpeed());
//		if(playerIsLeftEdge() && playerMovingLeft() || playerIsRightEdge() && playerMovingRight()) {
//			
//		}
//		if(playerIsTopEdge() && playerMovingUp() || playerIsBottomEdge() && playerMovingDown()) {
//			
//		}
	}
	private boolean playerIsTopEdge() {
		return player.getY() - screen.getYScroll() + player.getHeight()/2 < (Environment.getInstance().getHeight() * 0.2);
	}
	
	private boolean playerIsBottomEdge() {
		return player.getY()  - screen.getYScroll() + player.getHeight()/2 > (Environment.getInstance().getHeight() * 0.8);
	}
	
	private boolean playerIsLeftEdge() {
		return player.getX() - screen.getXScroll() + player.getWidth()/2 < (Environment.getInstance().getWidth() * 0.2);
	}
	
	private boolean playerIsRightEdge() {
		return player.getX() - screen.getXScroll() + player.getWidth()/2 > (Environment.getInstance().getWidth() * 0.8);
	}
	
	private boolean playerMovingUp() {
		return player.getYSpeed() < 0;
	}
	
	private boolean playerMovingDown() {
		return player.getYSpeed() > 0;
	}
	
	private boolean playerMovingLeft() {
		return player.getXSpeed() < 0;
	}
	
	private boolean playerMovingRight() {
		return player.getXSpeed() > 0;
	}
	
	public void draw() {
//		int x0 = (xScroll -  TILE_SIZE) / TILE_SIZE;
//		int y0 = (yScroll - TILE_SIZE) / TILE_SIZE;
//		int x1 = (xScroll + Environment.getInstance().getWidth() + 16) / 16;
//		int y1 = (yScroll + Environment.getInstance().getHeight() + 16) / 16;
//		for(int y = y0; y < y1; y++) {
//			for(int x = x0; x < x0; x++) {
//				screen.renderSprite(x, y, tiles[x + y * tileWidth].getSprite());
//			}
//		}
		for(int y = 0; y < tileHeight; y++) {
			int yp = y * TILE_SIZE;
			for(int x = 0; x < tileWidth; x++) {
				int xp = x * TILE_SIZE;
				screen.renderSprite(xp, yp, tiles[x + y * tileWidth].getSprite());
			}
		}
		mediator.drawEntities(screen);
		mediator.drawEnemies(screen);
		mediator.drawPlayers(screen);
		mediator.drawProjectiles(screen);
	}
}
