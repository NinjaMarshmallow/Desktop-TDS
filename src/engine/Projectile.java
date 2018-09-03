package engine;

public class Projectile extends Mob {
	
	private double angle;
	
	public Projectile(double angle) {
		this.angle = angle;
		this.speed = 10;
		
	}
}
