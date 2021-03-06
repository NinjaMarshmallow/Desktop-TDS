package engine.management.state;

import implementation.Screen;
import engine.menu.MainMenu;
import engine.menu.Menu;

public class MenuState implements State {
	
	private State gameState;
	private boolean changeState = false;
	private Menu menu;

	public MenuState(State state) {
		this.gameState = state;
		menu = new MainMenu();
	}
	
	public void update() {
		if(menu.hasNextMenu()) {
			menu = menu.nextMenu();
		}
		menu.update();
	}

	public void draw(Screen screen) {
		menu.draw(screen);
	}

	public State changeState() {
		changeState = false;
		menu.reset();
		return gameState;
	}

	public boolean isReadyForStateChange() {
		return menu.isReady();
	}

	public void start() {}
}
