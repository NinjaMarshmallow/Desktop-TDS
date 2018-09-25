package engine.behaviors.move;

import util.Keyboard;
import engine.entities.mobs.Mob;

public class KeyboardControlled implements MoveBehavior {
	private Keyboard keyboard;
	public KeyboardControlled() {
		this.keyboard = new Keyboard();
	}
	
	public KeyboardControlled(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	public void execute(Mob m) {
		m.setXSpeed(0);
		m.setYSpeed(0);
		if (keyboard.up) {
			m.setYSpeed(-m.getBaseSpeed());
		}
		if (keyboard.down) {
			m.setYSpeed(m.getBaseSpeed());
		}
		if (keyboard.left) {
			m.setXSpeed(-m.getBaseSpeed());
		}
		if (keyboard.right) {
			m.setXSpeed(m.getBaseSpeed());
		}
	}

}
