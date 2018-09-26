package engine.behaviors;

import engine.entities.Spawner;

public class NullSpawnBehavior implements SpawnBehavior {

	public void execute(Spawner spawner) {
		System.out.print("Spawn a thing...");
	}

}
