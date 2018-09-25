package engine.entities.mobs;

import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Stats;
import engine.behaviors.Weapon;
import engine.behaviors.move.KeyboardControlled;
import engine.entities.weapons.WatermelonLauncher;
import engine.graphics.Sprite;

public class Player extends Mob {
	
	private Keyboard keyboard;
	private Weapon weapon;
	
	public Player(Keyboard keyboard) {
		super(Environment.getInstance().getWidth()/2, Environment.getInstance().getHeight()/2, Sprite.PLAYER);
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
		this.setMoveBehavior(new KeyboardControlled(keyboard));
		this.weapon = new WatermelonLauncher(this);
		baseSpeed = Stats.PLAYER_SPEED;
		this.x -= this.getWidth()/2;
		this.y -= this.getHeight()/2;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void update() {
		keyboard.update();
		handleShooting();
		super.update();
		
	}
	
	public void handleShooting() {
		if(Mouse.getB() == 1) {
			double mx = Mouse.getXWithOffset();
			double my = Mouse.getYWithOffset();
			double angle = angleTo(mx, my);
			weapon.shoot(angle);
		}
	}
}
