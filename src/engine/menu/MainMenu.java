package engine.menu;

import util.Environment;
import util.text.TextObject;
import engine.menu.button.Button;
import engine.menu.button.ButtonFactory;
import engine.menu.click.StartGame;
import engine.menu.click.SwitchMenu;

public class MainMenu extends Menu {
	
	private LevelSelectMenu levelSelectMenu;
	private Button startGame, levelSelect, exit;
	
	
	public MainMenu() {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Game Title"));
		addStartGameButton();
		addLevelSelectButton();
		addExitButton();
	}
	
	private void addStartGameButton() {
		startGame = new Button();
		startGame.setX(Environment.getInstance().getWidth()/2);
		startGame.setY(title.getY() + 100);
		startGame.setClickBehavior(new StartGame(this));
		buttons.add(startGame);
	}
	
	private void addLevelSelectButton() {
		levelSelectMenu = new LevelSelectMenu(this);
		levelSelect = new Button("Select Level");
		levelSelect.setX(Environment.getInstance().getWidth()/2);
		levelSelect.setY(startGame.getY() + startGame.getHeight() + 100);
		levelSelect.setClickBehavior(new SwitchMenu(this, levelSelectMenu));
		buttons.add(levelSelect);
	}
	
	private void addExitButton() {
		exit = ButtonFactory.createExitButton();
		exit.setX(Environment.getInstance().getWidth()/2);
		exit.setY(levelSelect.getY() + levelSelect.getHeight() + 100);
		buttons.add(exit);
	}
}