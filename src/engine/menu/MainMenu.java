package engine.menu;

import util.Environment;
import util.text.TextObject;
import engine.menu.click.LevelSelect;
import engine.menu.click.StartGame;

public class MainMenu extends Menu {
	
	private LevelSelectMenu levelSelectMenu;
	private Button startGame, levelSelect;
	
	
	public MainMenu() {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Game Title"));
		addStartGameButton();
		addLevelSelectButton();
		addExitButton();
	}
	
	private void addStartGameButton() {
		startGame = new Button();
		startGame.setClickBehavior(new StartGame(this));
		buttons.add(startGame);
	}
	
	private void addLevelSelectButton() {
		levelSelectMenu = new LevelSelectMenu(this);
		levelSelect = new Button(startGame, BELOW, normalPadding, "Select Level");
		levelSelect.setClickBehavior(new LevelSelect(this, levelSelectMenu));
		buttons.add(levelSelect);
	}
	
	private void addExitButton() {
		buttons.add(new ExitButton(levelSelect, BELOW, normalPadding));
	}
}