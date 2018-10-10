package engine.menu.click;

import engine.level.Level;
import engine.management.LevelManager;
import engine.menu.Menu;

public class PlayLevel implements ClickBehavior {
	private Level level;
	private Menu menu;
	public PlayLevel(Level level, Menu menu) {
		this.level = level;
		this.menu = menu;
	}

	public void onClick() {
		LevelManager.getInstance().setCurrentLevel(level);
		menu.startGame();
	}
}
