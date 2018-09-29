package engine.level;

import implementation.Screen;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import engine.behaviors.EnemySpawnBehavior;
import engine.behaviors.move.FollowPlayer;
import engine.entities.Spawner;
import engine.entities.items.Key;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.HopeStudent;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.level.tile.Tile;
import engine.level.tile.TileFactory;
import engine.level.tile.VoidTile;
import engine.management.Mediator;

public class Level {
	public static final int TILE_SIZE = Sprite.BRICK_TILE.getSize();
	private double x, y;
	private int width, height, tileWidth, tileHeight;
	private Keyboard keyboard;
	private Player player;
	private Enemy enemy;
	private Spawner spawner;
	private Sprite tilemap;
	private Tile[] tiles;
	private Screen screen;
	private int time;

	public Level(Keyboard keyboard, Screen screen, Sprite tilemap) {
		this.keyboard = keyboard;
		this.screen = screen;
		this.tilemap = tilemap;
		tileWidth = tilemap.getWidth();
		tileHeight = tilemap.getHeight();
		width = tileWidth * TILE_SIZE;
		height = tileHeight * TILE_SIZE;
		player = new Player(keyboard);
		player.setX(4 * TILE_SIZE);
		player.setY(4 * TILE_SIZE);
		enemy = new HopeStudent(8 * TILE_SIZE, 14 * TILE_SIZE);
		enemy.setMoveBehavior(new FollowPlayer(player));
		spawner = new Spawner(100, 100, new EnemySpawnBehavior());
		time = 0;
		tiles = new Tile[tilemap.getWidth() * tilemap.getHeight()];
		loadTiles();
		Mediator.getInstance().add(new Key(27 * TILE_SIZE, 6 * TILE_SIZE));
	}

	private void loadTiles() {
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				int color = tilemap.getPixelAt(x, y);
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
		Mediator.getInstance().update();
		scrollScreen();
		for(int i = 0; i< tiles.length; i++) {
			tiles[i].update();
		}
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
		for(int i = 0; i < tiles.length; i++) {
			screen.renderTile(tiles[i]);
		}
		Mediator mediator = Mediator.getInstance();
		mediator.drawItems(screen);
		mediator.drawEntities(screen);
		mediator.drawEnemies(screen);
		mediator.drawPlayers(screen);
		mediator.drawProjectiles(screen);
	}
}
