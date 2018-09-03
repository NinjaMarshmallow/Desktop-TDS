package engine;

import util.Keyboard;
import util.Printer;

public class Player extends Mob {
	
	private Keyboard keyboard;
	
	public Player(Keyboard keyboard) {
		super();
		x = y = 100;
		width = height = 100;
		this.keyboard = keyboard;
	}
	
	public Player(double x, double y, Keyboard keyboard) {
		super();
		this.x = x;
		this.y = y;
		width = height = 100;
	}
	
	public void update() {
		keyboard.update();
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
	
	public Player(double x, double y, Sprite sprite, Keyboard keyboard) {
		super();
	}

}
