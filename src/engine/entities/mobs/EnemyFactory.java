package engine.entities.mobs;

import util.Color;

public class EnemyFactory {
	
	public static Enemy createEnemy(int x, int y, int color) {
		Enemy enemy = null;
		switch(color) {
		case Color.HOPE_STUDENT:
			enemy = new HopeStudent(x, y);
			break;
		case Color.ENEMY:
			enemy = new Enemy(x, y, 50, 50);
			break;
		}
		try {
		enemy.setX(enemy.getX() - enemy.getWidth()/2);
		enemy.setY(enemy.getY() - enemy.getHeight()/2);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Null enemy. Color is not set as an enemy type");
		}
		return enemy;
	}

}
