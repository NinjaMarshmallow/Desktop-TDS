package engine.menu.click;

import util.PlayerData;
import engine.entities.mobs.Player;
import engine.management.Mediator;
import engine.menu.Menu;

public class ChooseCharacter implements ClickBehavior {
	
	private PlayerData playerData;
	
	public ChooseCharacter(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	public void onClick() {
		Mediator.getInstance().clear();
		new Player(playerData.name, playerData.sprite);
	}
}