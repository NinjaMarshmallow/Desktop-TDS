package engine.behaviors;

public interface Positionable {
	
	public void setX(double x);
	public void setY(double y);
	public double getX();
	public double getY();
	public int getWidth();
	public int getHeight();
	public double distanceTo(Positionable pos);
	public double distanceTo(double x, double y);
	public double angleTo(Positionable pos);
	public double angleTo(double x, double y);
	public boolean collide(Positionable pos);
	public void update();
}
