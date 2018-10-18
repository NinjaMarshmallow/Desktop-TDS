package engine.entities.projectiles;

import util.Color;
import util.Stats;
import engine.entities.Entity;
import engine.entities.mobs.Mob;
import engine.entities.mobs.Player;
import engine.entities.mobs.enemy.Enemy;
import engine.graphics.AnimatedSprite;
import engine.graphics.AnimationFactory;
import engine.graphics.Sprite;
import engine.management.Mediator;

public class Projectile extends Entity {
	
	protected double angle;
	protected double start, age;
	protected double speed, range, power;
	protected double distanceTraveled;
	protected AnimatedSprite hitAnimation;
	protected Entity owner;
	
	public Projectile(Entity owner, double angle) {
		super(owner.getX(), owner.getY());
		initialize(owner, angle, new Sprite(10, 10, Color.ORANGE));
	}
	
	public Projectile(Entity owner, double angle, Sprite sprite) {
		super(owner.getX(), owner.getY());
		initialize(owner, angle, sprite);
	}
	
	private void initialize(Entity owner, double angle, Sprite sprite) {
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
		Mediator.getInstance().add(this);
	}
	
	public void update() {
		super.update();
		age = System.currentTimeMillis() - start;
		if(distanceTraveled > range) kill();
		move();
	}
	
	public void move() {
		distanceTraveled += speed;
		this.x += Math.cos(angle) * speed;
		this.y += Math.sin(angle) * speed;
	}
	
	public double getAge() {
		return age;
	}
	
	public void hit(Entity e) {
		Mob mob = null;
		if(owner instanceof Player && e instanceof Enemy) {
			mob = ((Enemy) e);
		} else if (owner instanceof Enemy && e instanceof Player) {
			mob = (Player) e;
		} else if(e instanceof Mob) {
			mob = (Mob) e;
		}
		if(mob != null) {
			mob.damage(power);
			kill();
		}
	}
	
	public void kill() {
		super.kill();
		hitAnimation.play((int)this.x, (int)this.y);
	}
	
	public Entity getOwner() {
		return owner;
	}
}