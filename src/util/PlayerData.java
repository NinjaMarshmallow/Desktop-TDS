package util;

import java.util.ArrayList;
import java.util.List;

import engine.graphics.Sprite;

public class PlayerData {
	
	public static List<PlayerData> players;
	
	public Sprite sprite;
	public String name;
	
	public PlayerData(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
	}
	private static void loadPlayerData() {
		players = new ArrayList<PlayerData>();
		players.add(new PlayerData("Emma", Sprite.EMMA));
		players.add(new PlayerData("Tyler", Sprite.TYLER));
	}
			
	
	public static List<PlayerData> getAllPlayerData() {
		loadPlayerData();
		return players;
	}

}
