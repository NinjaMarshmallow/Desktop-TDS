package engine.behaviors;

import engine.entities.Spawner;
import engine.entities.mobs.Chaser;
import engine.entities.mobs.HopeStudent;

public class EnemySpawnBehavior implements SpawnBehavior {

	public void execute(Spawner spawner) {
		Chaser enemy = new HopeStudent(spawner.getX(), spawner.getY());
	}
	
	
}
