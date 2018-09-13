package engine.entities.mobs;

import util.Keyboard;
import util.Mouse;
import util.Stats;
import engine.behaviors.Weapon;
import engine.entities.weapons.WatermelonLauncher;
import engine.graphics.Sprite;

public class Player extends Mob {
	
	private Keyboard keyboard;
	private Weapon weapon;
	
	public Player(Keyboard keyboard) {
		super(100, 100, Sprite.PLAYER);
		initialize(keyboard);
	}
	
	public Player(double x, double y, Keyboard keyboard) {
		super(x, y, Sprite.PLAYER);
		initialize(keyboard);
	}
	
	public Player(double x, double y, Sprite sprite, Keyboard keyboard) {
		super(x, y, sprite);
		initialize(keyboard);
	}
	
	private void initialize(Keyboard keyboard) {
		this.keyboard = keyboard;
		this.weapon = new WatermelonLauncher(this);
		baseSpeed = Stats.PLAYER_SPEED;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void update() {
		keyboard.update();
		handleShooting();
		super.update();
		
	}
	
	public void move() {
		xSpeed = ySpeed = 0;
		if (keyboard.up) {
			ySpeed = -baseSpeed;
		}
		if (keyboard.down) {
			ySpeed = baseSpeed;
		}
		if (keyboard.left) {
			xSpeed = -baseSpeed;
		}
		if (keyboard.right) {
			xSpeed = baseSpeed;
		}
		this.x += xSpeed;
		this.y += ySpeed;
	}
	
	public void handleShooting() {
		if(Mouse.getB() == 1) {
			double mx = Mouse.getX();
			double my = Mouse.getY();
			double angle = angleTo(mx, my);
			weapon.shoot(angle);
		}
	}
}
