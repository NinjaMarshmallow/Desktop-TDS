package engine.menu.click;

import util.PlayerData;
import engine.entities.mobs.Player;
import engine.entities.weapons.WeaponFactory;
import engine.management.Mediator;

public class ChooseCharacter implements ClickBehavior {
	
	private PlayerData playerData;
	
	public ChooseCharacter(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	public void onClick() {
		Mediator.getInstance().clear();
		Player player = new Player(playerData.name, playerData.sprite);
		player.setWeapon(WeaponFactory.createWeapon(playerData.weapon));
	}
}