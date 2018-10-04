package engine.management;

import implementation.Screen;
import engine.level.Level;

public class GameState implements State {
	private Level level;
	private boolean changeState = false;
	public GameState(Level level) {
		this.level = level;
	}
	
	public void update() {
		this.level.update();
	}

	public void draw(Screen screen) {
		this.level.draw();
	}

	public State changeState() {
		return this;
	}
	
	public boolean isReadyForStateChange() {
		return changeState;
	}
	
	

}
