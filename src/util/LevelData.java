package util;

import java.util.ArrayList;
import java.util.List;

import engine.graphics.Sprite;
import engine.level.Level;

public class LevelData {
	
	public static List<LevelData> levels;
	
	public Sprite map, enemies;
	public int playerSpawnX, playerSpawnY;
	public String name;
	
	public LevelData(String name, Sprite map, Sprite enemies, int playerSpawnX, int playerSpawnY) {
		this.name = name;
		this.map = map;
		this.enemies = enemies;
		this.playerSpawnX = playerSpawnX;
		this.playerSpawnY = playerSpawnY;
	}
	
	private static void loadLevelData() {
		levels = new ArrayList<LevelData>();
		int currentLevel = 0;
		while(true) {
			Sprite map = Sprite.loadLevel("/maps/level"+currentLevel+".png");
			Sprite enemies = Sprite.loadLevel("/maps/level"+currentLevel+"_enemies.png");
			if(map == null && currentLevel > 0) {
				break;
			}
			levels.add(new LevelData("Level "+currentLevel, map, enemies, 4, 4));
			currentLevel++;
			
		}
	}
			
	
	public static List<LevelData> getAllLevelData() {
		loadLevelData();
		return levels;
	}

}
