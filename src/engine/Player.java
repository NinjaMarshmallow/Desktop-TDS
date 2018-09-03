package engine;

import engine.behaviors.Weapon;
import util.Keyboard;
import util.Mouse;
import util.Printer;

public class Player extends Mob {
	
	private Keyboard keyboard;
	private Weapon weapon;
	
	public Player(Keyboard keyboard) {
		super(100, 100, 100, 100);
		this.keyboard = keyboard;
		this.weapon = new NullWeapon();
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
			Printer.print("Move Up");
			ySpeed = -speed;
		}
		if (keyboard.down) {
			Printer.print("Move Down");
			ySpeed = speed;
		}
		if (keyboard.left) {
			Printer.print("Move Left");
			xSpeed = -speed;
		}
		if (keyboard.right) {
			Printer.print("Move Right");
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
			Printer.print("Shooting at " + angle);
			weapon.shoot(angle);
		}
	}
	
	

}
