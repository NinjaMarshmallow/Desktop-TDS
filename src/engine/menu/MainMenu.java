package engine.menu;

import util.Environment;
import util.text.TextObject;
import engine.menu.button.Button;
import engine.menu.button.ButtonFactory;
import engine.menu.click.SwitchMenu;

public class MainMenu extends Menu {
	
	private LevelSelectMenu levelSelectMenu;
	private Button startGame, levelSelect, charSelect, exit;
	
	
	public MainMenu() {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Game Title"));
		addStartGameButton();
		//addLevelSelectButton();
		//addCharacterSelectButton();
		addExitButton();
	}
	
	private void addStartGameButton() {
		startGame = new Button();
		startGame.setX(Environment.getInstance().getWidth()/2);
		startGame.setY(title.getY() + 100);
		//startGame.setClickBehavior(new StartGame(this));
		startGame.setClickBehavior(new SwitchMenu(this, new CharacterSelectMenu(this, new LevelSelectMenu(this))));
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
	
	private void addCharacterSelectButton() {
		charSelect = new Button("Character Select");
		charSelect.setX(Environment.getInstance().getWidth()/2);
		charSelect.setY(levelSelect.getY() + levelSelect.getHeight() + 100);
		charSelect.setClickBehavior(new SwitchMenu(this, new CharacterSelectMenu(this, this)));
		buttons.add(charSelect);
	}
	
	private void addExitButton() {
		exit = ButtonFactory.createExitButton();
		exit.setX(Environment.getInstance().getWidth()/2);
		exit.setY(startGame.getY() + startGame.getHeight() + 100);
		buttons.add(exit);
	}
}