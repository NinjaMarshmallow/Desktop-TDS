package engine.menu.click;

import engine.menu.ClickBehavior;
import engine.menu.LevelSelectMenu;
import engine.menu.Menu;

public class LevelSelect implements ClickBehavior {
	
	private Menu menu, level;
	
	public LevelSelect(Menu menu, LevelSelectMenu level) {
		this.menu = menu;
		this.level = level;
	}
	
	public void onClick() {
		menu.setNextMenu(level);
	}
}
