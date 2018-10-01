package engine.entities.mobs;

import implementation.Screen;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.Health;
import engine.behaviors.TileObserver;
import engine.behaviors.move.BounceSideways;
import engine.behaviors.move.MoveBehavior;
import engine.behaviors.move.Moveable;
import engine.entities.Entity;
import engine.entities.gauges.Healthbar;
import engine.graphics.Sprite;
import engine.level.tile.Tile;
import engine.management.Mediator;

public class Mob extends Entity implements Moveable, Health, TileObserver {
	protected double baseSpeed, xSpeed, ySpeed, health, maxHealth;
	protected Healthbar healthbar;
	protected MoveBehavior moveBehavior;
	protected List<Tile> tiles;
	
	public Mob() {
		super();
		initialize();
	}
	
	public Mob(double x, double y, int width, int height) {
		super(x, y, width, height);
		initialize();
	}
	
	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		initialize();
	}
	
	private void initialize() {
		baseSpeed = 5;
		setXSpeed(getBaseSpeed());
		health = maxHealth = 100;
		healthbar = new Healthbar(this);
		moveBehavior = new BounceSideways();
		Mediator.getInstance().addTileObserver(this);
		Mediator.getInstance().add(this);
		tiles = new ArrayList<Tile>();
	}
	
	public void update() {
		super.update();
		healthbar.update();
		if(health <= 0) kill();
		move();
	}
	
	public void move() {
		moveBehavior.execute(this);
		if(!collision(getXSpeed(), 0)) {
			this.x += getXSpeed();
			
		}
		if(!collision(0, getYSpeed())) {
			this.y += getYSpeed();
		}
	}
	
	public double getBaseSpeed() {
		return baseSpeed;
	}
	
	public double getXSpeed() {
		return this.xSpeed;
	}
	
	public double getYSpeed() {
		return this.ySpeed;
	}

	public void setXSpeed(double speed) {
		this.xSpeed = speed;
	}
	
	public void setYSpeed(double speed) {
		this.ySpeed = speed;
	}

	public void setMoveBehavior(MoveBehavior moveBehavior) {
		this.moveBehavior = moveBehavior;
	}

	public double getHealth() {
		return health;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public boolean isAlive() {
		return alive;
	}

	public void heal(double hp) {
		health += hp;
		if(health > maxHealth) {
			health = maxHealth;
		}
	}

	public void damage(double hp) {
		health -= hp;
		if(health < 0) {
			health = 0;
		}
	}
	
	public void draw(Screen screen) {
		super.draw(screen);
		screen.renderEntity(healthbar);
	}

	public void notifyOfTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	protected boolean collision(double xa, double ya) {
		boolean result = false;
		this.x += xa;
		this.y += ya;
		for(int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			if(!tile.isTraversable() && collides(tile)) {
					result = true;
			}
		}
		this.x -= xa;
		this.y -= ya;
		return result;
	}

	public void setBaseSpeed(double speed) {
		this.baseSpeed = speed;
	}
}