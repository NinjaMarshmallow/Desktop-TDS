package engine.behaviors.move;


public interface Moveable {
	
	public void move();
	public double getXSpeed();
	public double getYSpeed();
	public void setXSpeed(double speed);
	public void setYSpeed(double speed);
	public void setMoveBehavior(MoveBehavior moveBehavior);
}
