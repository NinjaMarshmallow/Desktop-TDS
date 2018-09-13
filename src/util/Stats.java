package util;

public class Stats {
	
	
	//Default Data
	public final static double DEFAULT_SPEED = 10;
	public final static double DEFAULT_RANGE = 300;
	public final static double DEFAULT_POWER = 5;
	
	public final static int DEFAULT_MAX_AMMO = 10;
	public final static int DEFAULT_FIRE_RATE = 6;
	public final static int DEFAULT_RELOAD_TIME = 3 * Environment.getInstance().getFPS(); // 3 Seconds
	public final static int DEFAULT_INITIAL_CLIPS = 3;
	
	
	// Fireball Data
	public final static double FIREBALL_SPEED = 10;
	public final static double FIREBALL_RANGE = 500;
	public final static double FIREBALL_POWER = 15;
	
	public final static int FLAMETHROWER_MAX_AMMO = 10;
	public final static int FLAMETHROWER_FIRE_RATE = 6;
	public final static int FLAMETHROWER_RELOAD_TIME = 3 * Environment.getInstance().getFPS(); // 3 Seconds
	public final static int FLAMETHROWER_INITIAL_CLIPS = 3;
	
	
	//Watermelon Data
	public final static double WATERMELON_SPEED = 6;
	public final static double WATERMELON_RANGE = 500;
	public final static double WATERMELON_POWER = 22;
	
	public final static int WATERMELON_LAUNCHER_MAX_AMMO = 5;
	public final static int WATERMELON_LAUNCHER_FIRE_RATE = 2;
	public final static int WATERMELON_LAUNCHER_RELOAD_TIME = 5 * Environment.getInstance().getFPS(); // 5 Seconds
	public final static int WATERMELON_LAUNCHER_INITIAL_CLIPS = 5;
	
	//Player Data
	public final static int PLAYER_SPEED = 5;
	public final static int ENEMY_SPEED = 3;
	
}
