package engine;

import util.Color;
import util.Printer;

public class Projectile extends Entity {
	
	private double angle, speed;
	private Entity owner;
	private double age;
	private final double start; 
	public Projectile(Entity owner, double angle) {
		super(owner.getX(), owner.getY());
		this.owner = owner;
		this.angle = angle;
		this.speed = 3;
		sprite = new Sprite(10, 10, Color.ORANGE);
		width = sprite.getWidth();
		height = sprite.getHeight();
		start = System.currentTimeMillis();
	}
	
	public void update() {
		age = System.currentTimeMillis() - start;
		move();
	}
	
	public void move() {
		double xSpeed = Math.cos(angle) * speed;
		double ySpeed = Math.sin(angle) * speed;
		this.x += xSpeed;
		this.y += ySpeed;
	}
	
	public double getAge() {
		return age;
	}
}
