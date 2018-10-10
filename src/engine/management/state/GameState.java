package engine.management.state;

import implementation.Screen;
import engine.level.Level;
import engine.management.LevelManager;

public class GameState implements State {
	private Level level;
	private boolean changeState = false;
	public GameState() {
	}
	
	public void start() {
		this.level = LevelManager.getInstance().getCurrentLevel();
		this.level.start();
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
