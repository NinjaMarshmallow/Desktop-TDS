package engine.entities.mobs;

import java.util.ArrayList;
import java.util.List;

import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Stats;
import engine.behaviors.Collideable;
import engine.behaviors.ItemObserver;
import engine.behaviors.Weapon;
import engine.behaviors.move.KeyboardControlled;
import engine.entities.items.Item;
import engine.entities.items.Key;
import engine.entities.weapons.WatermelonLauncher;
import engine.graphics.Sprite;
import engine.level.tile.DoorTile;
import engine.level.tile.Tile;
import engine.management.Mediator;

public class Player extends Mob implements ItemObserver {
	
	private Weapon weapon;
	private List<Item> inventory;
	private String name;
	
	public Player() {
		super(Environment.getInstance().getWidth()/2, Environment.getInstance().getHeight()/2, Sprite.PLAYER);
		initialize("Player 1");
	}
	
	public Player(double x, double y) {
		super(x, y, Sprite.PLAYER);
		initialize("Player 1");
	}
	
	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		initialize("Player 1");
	}
	
	public Player(String name, Sprite sprite) {
		super(-sprite.getWidth(), -sprite.getHeight(), sprite);
		initialize(name);
	}
	
	private void initialize(String name) {
		this.name = name;
		baseSpeed = Stats.PLAYER_SPEED;
		this.setMoveBehavior(new KeyboardControlled(Keyboard.getInstance()));
		this.weapon = new WatermelonLauncher(this);
		this.x -= this.getWidth()/2;
		this.y -= this.getHeight()/2;
		inventory = new ArrayList<Item>();
		Mediator.getInstance().addItemObserver(this);
		width = Sprite.TILE_SIZE;
		height = (int) (Sprite.TILE_SIZE * 1.617);
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void update() {
		handleShooting();
		super.update();
	}
	
	protected boolean collision(double xa, double ya) {
		boolean result = false;
		this.x += xa;
		this.y += ya;
		for(int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			if(!tile.isTraversable() && collides(tile)) {
				if(tile instanceof DoorTile) {
					for(int j = 0; j < inventory.size(); j++) {
						Item item = inventory.get(j);
						if(item instanceof Key) {
							DoorTile doorTile = (DoorTile) tile;
							doorTile.open();
							break;
						}
					}
				}
				result = true;
			}
		}
		this.x -= xa;
		this.y -= ya;
		return result;
	}
	
	private void handleShooting() {
		if(Mouse.getB() == 1) {
			double mx = Mouse.getXWithOffset();
			double my = Mouse.getYWithOffset();
			double angle = angleTo(mx, my);
			weapon.shoot(angle);
		}
	}

	public void notifyOfItems(List<Item> items) {
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(item instanceof Collideable) {
				if(collides((Collideable)item)) {
					item.setOwner(this);
					this.inventory.add(item);
					Mediator.getInstance().remove(item);
				}
			}
		}
	}
	
	public void reset() {
		this.health = maxHealth;
		this.inventory.clear();
		this.alive = true;
		this.initialize(this.name);
		Mediator.getInstance().add(this);
	}
}
