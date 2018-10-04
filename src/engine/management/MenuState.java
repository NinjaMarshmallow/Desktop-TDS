package engine.management;

import implementation.Screen;
import util.Color;
import engine.menu.Menu;

public class MenuState implements State {
	
	private State gameState;
	private boolean changeState = false;
	private Menu menu;
	
	public MenuState(State state) {
		this.gameState = state;
		menu = new Menu();
	}
	
	public void update() {
		menu.update();
	}

	public void draw(Screen screen) {
		menu.draw(screen);
	}

	public State changeState() {
		return gameState;
	}

	public boolean isReadyForStateChange() {
		return menu.isReady();
	}
}
