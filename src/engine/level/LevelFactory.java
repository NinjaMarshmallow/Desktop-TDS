package engine.level;

import implementation.Screen;
import util.Keyboard;
import util.LevelData;
import engine.graphics.Sprite;

public class LevelFactory {
	
	public static Level createLevel(Screen screen, Sprite map, Sprite enemies) {
		return new Level("Some level", screen, map, enemies);
	}
	
	public static Level createLevel(Screen screen, LevelData data) {
		return new Level(data.name, screen, data.map, data.enemies, data.playerSpawnX, data.playerSpawnY);
	}
}
