package engine.management;

import implementation.Screen;

import java.util.ArrayList;
import java.util.List;

import util.LevelData;
import engine.level.Level;
import engine.level.LevelFactory;

public class LevelManager {
	
	private static LevelManager instance;
	private List<LevelData> levels;
	private Screen screen;
	private Level currentLevel;
	
	public static LevelManager getInstance() {
		if(instance == null) {
			System.err.println("Level Manager has not been built yet...");
		}
		return instance;
	}
	
	public static void build(Screen screen) {
		if(instance == null) {
			instance = new LevelManager(screen);
		}
	}
	
	private LevelManager(Screen screen) {
		this.screen = screen;
		levels = new ArrayList<LevelData>(LevelData.getAllLevelData());
		//loadLevels();
		currentLevel = LevelFactory.createLevel(screen, levels.get(1));
		
	}
	
//	private void loadLevels() {
//		List<LevelData> leveldata = LevelData.getAllLevelData();
//		for(int i = 0; i < leveldata.size(); i++) {
//			levels.add(LevelFactory.createLevel(screen, leveldata.get(i)));
//		}
//		System.out.println(levels.size() + " Levels were found and loaded.");
//	}
	
	public void setCurrentLevel(Level level) {
		this.currentLevel.stop();
		this.currentLevel = level;
		this.currentLevel.start();
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public Level getLevel(int index) {
		return LevelFactory.createLevel(screen, levels.get(index));
	}
	
	public int getNumberOfLevels() {
		return levels.size();
	}
}
