package engine.menu.click;

import engine.menu.Menu;

public class StartGame implements ClickBehavior {
	private Menu menu;
	public StartGame(Menu menu) {
		this.menu = menu;
	}
	
	public void onClick() {
		this.menu.startGame();
	}

}
