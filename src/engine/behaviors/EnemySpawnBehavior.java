package engine.behaviors;

import engine.entities.Spawner;
import engine.entities.mobs.enemy.HopeStudent;

public class EnemySpawnBehavior implements SpawnBehavior {

	public void execute(Spawner spawner) {
		new HopeStudent(spawner.getX(), spawner.getY());
	}
	
	
}
