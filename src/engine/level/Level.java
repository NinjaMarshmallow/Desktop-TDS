package engine.level;

import implementation.Screen;

import java.util.ArrayList;

import util.Color;
import util.Keyboard;
import util.Mouse;
import engine.behaviors.move.FollowPlayer;
import engine.entities.Spawner;
import engine.entities.items.Key;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.EnemyFactory;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.level.tile.DoorTile;
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

	public Level(Keyboard keyboard, Screen screen, Sprite tilemap, Sprite enemymap) {
		this.keyboard = keyboard;
		this.screen = screen;
		time = 0;
		initializeTiles(tilemap);
		bindDoors();
		initializePlayer();
		placeEnemies(enemymap);
		initializeItems();
	}
	
	private void initializeTiles(Sprite tilemap) {
		this.tilemap = tilemap;
		tileWidth = tilemap.getWidth();
		tileHeight = tilemap.getHeight();
		width = tileWidth * TILE_SIZE;
		height = tileHeight * TILE_SIZE;
		tiles = new Tile[tilemap.getWidth() * tilemap.getHeight()];
		loadTiles();
	}
	
	private void initializePlayer() {
		player = new Player(keyboard);
		player.setX(4 * TILE_SIZE);
		player.setY(4 * TILE_SIZE);
	}
	
	private void placeEnemies(Sprite enemymap) {
		//spawner = new Spawner(100, 100, new EnemySpawnBehavior());
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				int color = enemymap.getPixelAt(x, y);
				if (!isEnemyPlacementColor(color)) continue;
				Enemy enemy = EnemyFactory.createEnemy(x * TILE_SIZE, y * TILE_SIZE, color);
				enemy.setMoveBehavior(new FollowPlayer(player));
				enemy.setX(enemy.getX() - enemy.getWidth()/2);
				enemy.setY(enemy.getY() - enemy.getHeight()/2);
			}
		}
	}
	
	private boolean isEnemyPlacementColor(int color) {
		for(int i = 0; i < Color.enemyColors.length; i++) {
			if(Color.enemyColors[i] == color) {
				return true;
			}
		}
		return false;
	}
	
	private void initializeItems() {
		Mediator.getInstance().add(new Key(27 * TILE_SIZE, 6 * TILE_SIZE));
	}

	private void loadTiles() {
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				Tile tile = buildTile(x, y);
				placeTile(x, y, tile);
			}
		}
	}
	
	private Tile buildTile(int x, int y) {
		int color = tilemap.getPixelAt(x, y);
		return TileFactory.createTile(x * TILE_SIZE, y * TILE_SIZE, color);
	}
	
	private void placeTile(int x, int y, Tile tile) {
		tiles[x + y * tileWidth] = tile;
		Mediator.getInstance().add(tile);
	}
	
	private void bindDoors() {
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				Tile tile = getTile(x * TILE_SIZE, y * TILE_SIZE);
				if(tile instanceof DoorTile) {
					bindDoor((DoorTile) tile);
				}
			}
		}
	}
	
	private void bindDoor(DoorTile door) {
		Tile right = getTile((int)door.getX() + TILE_SIZE, (int)door.getY());
		Tile down = getTile((int) door.getX(), (int) door.getY() + TILE_SIZE);
		if (right instanceof DoorTile) door.bind((DoorTile) right);
		if (down instanceof DoorTile) door.bind((DoorTile) down);
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
		mediator.drawAnimations(screen);
		mediator.drawProjectiles(screen);
	}
}