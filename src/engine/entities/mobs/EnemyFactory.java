package engine.entities.mobs;

import util.Color;

public class EnemyFactory {
	
	public static Enemy createEnemy(int x, int y, int color) {
		switch(color) {
		case Color.HOPE_STUDENT:
			return new HopeStudent(x, y);
		case Color.ENEMY:
			return new Enemy(x, y, 50, 50);
		}
		return null;
	}

}
