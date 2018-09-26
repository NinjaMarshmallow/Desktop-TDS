package engine.behaviors;

public interface Collideable extends Positionable {
	
	public boolean collides(Collideable col);
	public int getWidth();
	public int getHeight();

}
