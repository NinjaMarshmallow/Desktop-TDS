package util;

public class Stats {
	//Projectile Data
	
	public final static double DEFAULT_SPEED = 10;
	public final static double DEFAULT_RANGE = 300;
	
	public final static double FIREBALL_SPEED = 10;
	public final static double FIREBALL_RANGE = 300;
	
	
	//Weapon Data
	
	public final static int FLAMETHROWER_MAX_AMMO = 10;
	public final static int FLAMETHROWER_FIRE_RATE = 10;
	public final static int FLAMETHROWER_RELOAD_TIME = 3 * Environment.getInstance().getFPS(); // 3 Seconds
	public final static int FLAMETHROWER_INITIAL_CLIPS = 3;
	

}
