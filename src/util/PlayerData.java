package util;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.Weapon;
import engine.entities.weapons.WatermelonLauncher;
import engine.graphics.Sprite;

public class PlayerData {
	
	public static List<PlayerData> players;
	
	public Sprite sprite;
	public String name;
	public String weapon;
	
	public PlayerData(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
		this.weapon = "WM";
	}
	
	public PlayerData(String name, Sprite sprite, String weapon) {
		this.name = name;
		this.sprite = sprite;
		this.weapon = weapon;
	}
	private static void loadPlayerData() {
		players = new ArrayList<PlayerData>();
		players.add(new PlayerData("Emma", Sprite.EMMA));
		players.add(new PlayerData("Tyler", Sprite.TYLER, "FB"));
		players.add(new PlayerData("Jack", Sprite.JACK, "HA"));
	}
			
	
	public static List<PlayerData> getAllPlayerData() {
		loadPlayerData();
		return players;
	}

}
