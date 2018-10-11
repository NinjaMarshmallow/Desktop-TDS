package engine.menu;

import java.util.ArrayList;
import java.util.List;

import util.Environment;
import util.PlayerData;
import util.text.TextObject;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.management.LevelManager;
import engine.menu.button.Button;
import engine.menu.button.ButtonFactory;

public class CharacterSelectMenu extends Menu {
	private Player selectedPlayer;
	private List<Sprite> sprites;
	private Menu nextMenu;
	private Menu prevMenu;
	public CharacterSelectMenu(Menu prevMenu, Menu nextMenu) {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Character Select"));
		this.nextMenu = nextMenu;
		this.prevMenu = prevMenu;
		sprites = new ArrayList<Sprite>();
		sprites.add(Sprite.TYLER);
		sprites.add(Sprite.EMMA);
		addButtons();
		for(int i = 0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			button.setX(Environment.getInstance().getWidth()/2 + (button.getWidth() + 50) * (i / 4));
			button.setY(title.getY() + (button.getHeight() + 50) * (i % 4 + 1));
		}
	}
	
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}
	
	private void addButtons() {
		List<PlayerData> playerdata = PlayerData.getAllPlayerData();
		for(int i = 0; i < playerdata.size(); i++) {
			PlayerData data = playerdata.get(i);
			buttons.add(ButtonFactory.createChooseCharacterButton(data, this, nextMenu));
		}
		buttons.add(ButtonFactory.createBackButton(this, prevMenu));
	}
}
