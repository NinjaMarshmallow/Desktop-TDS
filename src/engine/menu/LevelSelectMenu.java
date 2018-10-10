package engine.menu;

import util.Environment;
import util.text.TextObject;
import engine.level.Level;
import engine.management.LevelManager;
import engine.menu.button.Button;
import engine.menu.button.ButtonFactory;

public class LevelSelectMenu extends Menu {
	private Level selectedLevel;
	private Menu prevMenu;
	public LevelSelectMenu(Menu prevMenu) {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Level Select"));
		this.prevMenu = prevMenu;
		addButtons();
		for(int i = 0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			button.setX(Environment.getInstance().getWidth()/2 + (button.getWidth() + 50) * (i / 4));
			button.setY(title.getY() + (button.getHeight() + 50) * (i % 4 + 1));
		}
	}
	
	public Level getSelectedLevel() {
		return selectedLevel;
	}
	
	private void addButtons() {
		for(int i = 0; i < LevelManager.getInstance().getNumberOfLevels(); i++) {
			buttons.add(ButtonFactory.createPlayLevelButton(LevelManager.getInstance().getLevel(i), this));
			
		}
		addBackButton();
	}
	
	
	private void addBackButton() {
		Button back = ButtonFactory.createBackButton(this, prevMenu);
		buttons.add(back);
	}
	
	

}
