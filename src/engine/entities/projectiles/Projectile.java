package engine.entities.projectiles;

import util.Color;
import util.Printer;
import util.Stats;
import engine.entities.Entity;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Mob;
import engine.entities.mobs.Player;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;

public class Projectile extends Entity {
	
	protected double angle;
	protected double start, age;
	protected double speed, range, power;
	protected double distanceTraveled;
	protected AnimatedSprite hitAnimation;
	protected Entity owner;
	
	public Projectile(Entity owner, double angle) {
		super(owner.getX(), owner.getY());
		this.owner = owner;
		this.angle = angle;
		speed = Stats.DEFAULT_SPEED;
		range = Stats.DEFAULT_RANGE;
		power = Stats.DEFAULT_POWER;
		sprite = new Sprite(10, 10, Color.ORANGE);
		width = sprite.getWidth();
		height = sprite.getHeight();
		start = System.currentTimeMillis();
		hitAnimation = AnimationFactory.createAnimation(this);
	}
	
	public Projectile(Entity owner, double angle, Sprite sprite) {
		super(owner.getX(), owner.getY());
		this.owner = owner;
		this.angle = angle;
		this.sprite = sprite;
		speed = Stats.DEFAULT_SPEED;
		range = Stats.DEFAULT_SPEED;
		power = Stats.DEFAULT_POWER;
		width = sprite.getWidth();
		height = sprite.getHeight();
		start = System.currentTimeMillis();
		hitAnimation = AnimationFactory.createAnimation(this);
	}
	
	public void update() {
		age = System.currentTimeMillis() - start;
		if(distanceTraveled > range) kill();
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
	
	public void kill() {
		super.kill();
		hitAnimation.play((int)this.x - this.getWidth()/2, (int)this.y - this.getHeight()/2);
	}
}