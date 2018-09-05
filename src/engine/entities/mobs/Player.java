package engine.entities.mobs;

import util.Color;
import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.behaviors.Weapon;
import engine.entities.weapons.Flamethrower;
import engine.graphics.Sprite;

public class Player extends Mob {
	
	private Keyboard keyboard;
	private Weapon weapon;
	
	public Player(Keyboard keyboard) {
		super(100, 100, 100, 100);
		this.keyboard = keyboard;
		this.weapon = new Flamethrower(this);
		sprite = new Sprite(width, height, Color.ROYAL_BLUE);
	}
	
	public Player(double x, double y, Keyboard keyboard) {
		super(x, y, 100, 100);
	}
	
	public Player(double x, double y, Sprite sprite, Keyboard keyboard) {
		super(x, y, sprite.getWidth(), sprite.getHeight());
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
			ySpeed = -speed;
		}
		if (keyboard.down) {
			ySpeed = speed;
		}
		if (keyboard.left) {
			xSpeed = -speed;
		}
		if (keyboard.right) {
			xSpeed = speed;
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
