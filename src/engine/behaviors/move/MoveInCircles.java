package engine.behaviors.move;

import engine.entities.mobs.Mob;

public class MoveInCircles implements MoveBehavior {
	private int time = 0;
	private int state = 0;
	private Mob m;
	public void execute(Mob m) {
		this.m = m;
		calculateDirection();
		move();
		time++;
	}
	
	private void calculateDirection() {
		if(time % 60 == 0) {
			state++;
			state %= 4;
		}
	}
	
	private void move() {
		if (state == 0) {
			m.setXSpeed(0);
			m.setYSpeed(-m.getBaseSpeed());
		} else if (state == 1) {
			m.setXSpeed(m.getBaseSpeed());
			m.setYSpeed(0);
		} else if (state == 2) {
			m.setXSpeed(0);
			m.setYSpeed(m.getBaseSpeed());
		} else if (state == 3) {
			m.setXSpeed(-m.getBaseSpeed());
			m.setYSpeed(0);
		}
	}

}
