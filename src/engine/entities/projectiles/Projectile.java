package engine.entities.projectiles;

import util.Color;
import util.Printer;
import util.Stats;
import engine.entities.Entity;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Mob;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;

public class Projectile extends Entity {
	
	protected double angle;
	protected double start, age;
	protected double speed, range, power;
	protected double distanceTraveled;
	protected Entity owner;
	
	public Projectile(Entity owner, double angle) {
		super(owner.getX(), owner.getY());
		this.owner = owner;
		this.angle = angle;
		speed = Stats.DEFAULT_SPEED;
		range = Stats.DEFAULT_SPEED;
		power = Stats.DEFAULT_POWER;
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
	
	public void hit(Entity e) {
		Printer.print(owner + "\t" + e);
		if(owner instanceof Player) {
			if(e instanceof Enemy) {
				Enemy enemy = ((Enemy) e);
				enemy.damage(power);
				kill();
			}
		} else if (owner instanceof Enemy) {
			if(e instanceof Player) {
				Player player = (Player) e;
				player.damage(power);
				kill();
			}
		} else if(e instanceof Mob) {
			Mob mob = (Mob) e;
			mob.damage(power);
			kill();
		}
	}
}