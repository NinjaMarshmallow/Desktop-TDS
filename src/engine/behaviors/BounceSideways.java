package engine.behaviors;

import util.Environment;
import engine.entities.mobs.Mob;

public class BounceSideways implements MoveBehavior {
	
	public void execute(Mob mob) {
		mob.setXSpeed(mob.getBaseSpeed());
		if(mob.getX() < 0 || mob.getX() > Environment.getInstance().getWidth() - mob.getWidth()) {
			mob.setXSpeed(mob.getXSpeed() * -1);
		}
	}

}
