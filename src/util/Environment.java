package util;


public class Environment {
	private static Environment env;
	private final int FPS = 60;
	private final int WIDTH = 1600, HEIGHT = 900;
	
	private Environment() {
	}
	
	public static Environment getInstance() {
		if (env == null) {
			env = new Environment();
		}
		return env;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getFPS() {
		return FPS;
	}
}
