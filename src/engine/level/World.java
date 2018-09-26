package engine.level;

import implementation.Screen;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import engine.behaviors.EnemySpawnBehavior;
import engine.behaviors.move.FollowPlayer;
import engine.entities.Spawner;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.HopeStudent;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.level.tile.Tile;
import engine.level.tile.TileFactory;
import engine.level.tile.VoidTile;
import engine.management.Mediator;

public class World {
	public static final int TILE_SIZE = Sprite.BRICK_TILE.getSize();
	private double x, y;
	private int width, height, tileWidth, tileHeight;
	private Keyboard keyboard;
	private Player player;
	private Enemy enemy;
	private Spawner spawner;
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
		enemy = new HopeStudent(500, 400);
		enemy.setMoveBehavior(new FollowPlayer(player));
		spawner = new Spawner(100, 100, new EnemySpawnBehavior());
		tileSprite = Sprite.WORLD;
		tileWidth = tileSprite.getWidth();
		tileHeight = tileSprite.getHeight();
		width = tileWidth * TILE_SIZE;
		height = tileHeight * TILE_SIZE;
		time = 0;
		tiles = new Tile[tileSprite.getWidth() * tileSprite.getHeight()];
		loadTiles();
	}

	private void loadTiles() {
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				int color = tileSprite.getPixelAt(x, y);
				Tile tile = TileFactory.createTile(x * TILE_SIZE, y * TILE_SIZE, color);
				tiles[x + y * tileWidth] = tile;
				Mediator.getInstance().add(tile);
			}
		}
	}
	
	private Tile getTile(int x, int y) {
		for(int i = 0; i < tiles.length; i++) {
			Tile tile = tiles[i];
			if(tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		return new VoidTile(x, y);
	}

	public void update() {
		mediator.update();
		scrollScreen();
	}

	private void scrollScreen() {
		int xScroll = (int)player.getX() - screen.getWidth()/2 + player.getWidth()/2;
		int yScroll = (int)player.getY() - screen.getHeight()/2 + player.getHeight()/2;
		screen.setScroll(xScroll, yScroll);
		Mouse.setOffset(xScroll, yScroll);
		// if(playerIsLeftEdge() && playerMovingLeft() || playerIsRightEdge() &&
		// playerMovingRight()) {
		//
		// }
		// if(playerIsTopEdge() && playerMovingUp() || playerIsBottomEdge() &&
		// playerMovingDown()) {
		//
		// }
	}

	private boolean playerIsTopEdge() {
		return player.getY() - screen.getYScroll() + player.getHeight() / 2 < (Environment
				.getInstance().getHeight() * 0.2);
	}

	private boolean playerIsBottomEdge() {
		return player.getY() - screen.getYScroll() + player.getHeight() / 2 > (Environment
				.getInstance().getHeight() * 0.8);
	}

	private boolean playerIsLeftEdge() {
		return player.getX() - screen.getXScroll() + player.getWidth() / 2 < (Environment
				.getInstance().getWidth() * 0.2);
	}

	private boolean playerIsRightEdge() {
		return player.getX() - screen.getXScroll() + player.getWidth() / 2 > (Environment
				.getInstance().getWidth() * 0.8);
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
		// int x0 = (xScroll - TILE_SIZE) / TILE_SIZE;
		// int y0 = (yScroll - TILE_SIZE) / TILE_SIZE;
		// int x1 = (xScroll + Environment.getInstance().getWidth() + 16) / 16;
		// int y1 = (yScroll + Environment.getInstance().getHeight() + 16) / 16;
		// for(int y = y0; y < y1; y++) {
		// for(int x = x0; x < x0; x++) {
		// screen.renderSprite(x, y, tiles[x + y * tileWidth].getSprite());
		// }
		// }
		for(int i = 0; i < tiles.length; i++) {
			screen.renderTile(tiles[i]);
		}
		mediator.drawEntities(screen);
		mediator.drawEnemies(screen);
		mediator.drawPlayers(screen);
		mediator.drawProjectiles(screen);
	}
}
