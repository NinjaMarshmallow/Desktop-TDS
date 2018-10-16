package util;

public class Stats {
	
	
	//Default Data
	public final static double DEFAULT_SPEED = 10;
	public final static double DEFAULT_RANGE = 300;
	public final static double DEFAULT_POWER = 5;
	
	public final static int DEFAULT_MAX_AMMO = 10; 
	public final static int DEFAULT_FIRE_RATE = 6;  // Projectiles per second
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
	
	// Watermelon Data
	public final static double WATERMELON_SPEED = 6;
	public final static double WATERMELON_RANGE = 500;
	public final static double WATERMELON_POWER = 15;
	
	public final static int WATERMELON_LAUNCHER_MAX_AMMO = 5;
	public final static int WATERMELON_LAUNCHER_FIRE_RATE = 2;
	public final static int WATERMELON_LAUNCHER_RELOAD_TIME = 5 * Environment.getInstance().getFPS(); // 5 Seconds
	public final static int WATERMELON_LAUNCHER_INITIAL_CLIPS = 5;
	
	// Lightning Data
	public final static double LIGHTNING_SPEED = 24;
	public final static double LIGHTNING_RANGE = 700;
	public final static double LIGHTNING_POWER = 32;
	
	public final static int LIGHTNING_SHOOTER_MAX_AMMO = 5;
	public final static int LIGHTNING_SHOOTER_FIRE_RATE = 20;
	public final static int LIGHTNING_SHOOTER_RELOAD_TIME = 2 * Environment.getInstance().getFPS(); // 5 Seconds
	public final static int LIGHTNING_SHOOTER_INITIAL_CLIPS = 10;
	
	// Hatchet Data
	public final static double HATCHET_SPEED = 6;
	public final static double HATCHET_RANGE = 800;
	public final static double HATCHET_POWER = 51;
	
	public final static int HATCHET_TOSSER_MAX_AMMO = 5;
	public final static int HATCHET_TOSSER_FIRE_RATE = 2;
	public final static int HATCHET_TOSSER_RELOAD_TIME = 2 * Environment.getInstance().getFPS(); // 5 Seconds
	public final static int HATCHET_TOSSER_INITIAL_CLIPS = 10;
	
	// Player Data
	public final static double PLAYER_SPEED = 5;
	
	// Enemy Data
	public final static double ENEMY_SPEED = 3;
	public final static int ENEMY_MELEE_DAMAGE = 10;
	public final static int ENEMY_MELEE_RATE = 60;
	public final static int CHASER_RANGE = 50;
	public final static int HOPE_STUDENT_RANGE = 500;
}
