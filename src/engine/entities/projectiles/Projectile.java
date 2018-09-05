package engine.entities.projectiles;

import util.Color;
import util.Stats;
import engine.entities.Entity;
import engine.graphics.Sprite;

public class Projectile extends Entity {
	
	protected double angle;
	protected double start, age;
	protected double speed, range;
	protected double distanceTraveled;
	protected Entity owner;
	
	public Projectile(Entity owner, double angle) {
		super(owner.getX(), owner.getY());
		this.owner = owner;
		this.angle = angle;
		this.speed = Stats.DEFAULT_SPEED;
		this.range = Stats.DEFAULT_SPEED;
		sprite = new Sprite(10, 10, Color.ORANGE);
		width = sprite.getWidth();
		height = sprite.getHeight();
		start = System.currentTimeMillis();
		
	}
	
	public void update() {
		age = System.currentTimeMillis() - start;
		if(!onScreen() || distanceTraveled > range) kill();
		move();
	}
	
	public void move() {
		distanceTraveled += speed;
		double xSpeed = Math.cos(angle) * speed;
		double ySpeed = Math.sin(angle) * speed;
		this.x += xSpeed;
		this.y += ySpeed;
	}
	
	public double getAge() {
		return age;
	}
}
