package engine.level;

import implementation.Screen;
import util.Keyboard;
import util.LevelData;
import engine.graphics.Sprite;

public class LevelFactory {
	
	public static Level createLevel(Keyboard keyboard, Screen screen, Sprite map, Sprite enemies) {
		return new Level("Some level", keyboard, screen, map, enemies);
	}
	
	public static Level createLevel(Keyboard keyboard, Screen screen, LevelData data) {
		return new Level(data.name, keyboard, screen, data.map, data.enemies, data.playerSpawnX, data.playerSpawnY);
	}
}
