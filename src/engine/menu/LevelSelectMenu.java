package engine.menu;

import util.Environment;
import util.text.TextObject;
import engine.level.Level;

public class LevelSelectMenu extends Menu {
	private Level selectedLevel;
	private Menu prevMenu;
	public LevelSelectMenu(Menu prevMenu) {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Level Select"));
		this.prevMenu = prevMenu;
	}
	
	public Level getSelectedLevel() {
		return selectedLevel;
	}
	
	

}
