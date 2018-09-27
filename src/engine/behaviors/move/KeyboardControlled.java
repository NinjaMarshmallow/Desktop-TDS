package engine.behaviors.move;

import util.Keyboard;
import engine.entities.mobs.Mob;

public class KeyboardControlled implements MoveBehavior {
	private Keyboard keyboard;
	private boolean sprint = false;
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
		if (keyboard.sprint && !sprint) {
			m.setBaseSpeed(m.getBaseSpeed() * 2);
			sprint = true;
		} else if (!keyboard.sprint && sprint) {
			m.setBaseSpeed(m.getBaseSpeed() / 2);
			sprint = false;
		}
	}

}
