package engine.behaviors;

import engine.entities.Entity;

public interface Weapon {
	
	public void shoot(double angle);
	public boolean hasAmmo();
	public void setOwner(Entity en);
}
