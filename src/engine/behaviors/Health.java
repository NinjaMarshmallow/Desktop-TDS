package engine.behaviors;


public interface Health {
	
	public double getHealth();
	public boolean isAlive();
	public void heal(double hp);
	public void damage(double hp);
}
