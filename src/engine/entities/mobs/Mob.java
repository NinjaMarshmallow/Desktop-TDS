package engine.entities.mobs;

import implementation.Screen;
import util.Environment;
import engine.behaviors.Health;
import engine.behaviors.MoveBehavior;
import engine.behaviors.Moveable;
import engine.entities.Entity;
import engine.entities.gauges.Healthbar;
import engine.graphics.Sprite;

public class Mob extends Entity implements Moveable, Health {
	protected double speed, xSpeed, ySpeed, health, maxHealth;
	protected Healthbar healthbar;
	private MoveBehavior moveBehavior;
	public Mob() {
		super();
		speed = 5;
		health = maxHealth = 100;
		healthbar = new Healthbar(this);
	}
	
	public Mob(double x, double y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
		health = maxHealth = 100;
		healthbar = new Healthbar(this);
	}
	
	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		speed = 5;
		health = maxHealth = 100;
		healthbar = new Healthbar(this);
	}
	
	public void update() {
		super.update();
		healthbar.update();
		if(health <= 0) kill();
		move();
	}
	
	public void move() {
		if(x < 0 || x > Environment.getInstance().getWidth() - width) xSpeed *= -1;
		this.x += getXSpeed();
	}
	
	public double getXSpeed() {
		return this.xSpeed;
	}
	
	public double getYSpeed() {
		return this.xSpeed;
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
}