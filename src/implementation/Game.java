package implementation;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import engine.graphics.Sprite;
import engine.level.Level;
import engine.management.LevelManager;
import engine.management.state.GameState;
import engine.management.state.MenuState;
import engine.management.state.State;


public class Game {
	
	private boolean running = false;
	private int currentFrame = 0;
	private int renderLimit = 60;
	private Screen screen;
	private Thread updateThread, thread;
	private Environment env;
	private Keyboard keyboard;
	private Mouse mouse;
	private Level world;
	private State state, gameState, menuState;

	public Game() {
		mouse = new Mouse();
		Keyboard.build();
		env = Environment.getInstance();
		screen = new Screen(env.getWidth(), env.getHeight());
		try {
			LevelManager.build(screen);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Can't load Levels");
		}
		gameState = new GameState();
		menuState = new MenuState(gameState);
		state = menuState;
		thread = new Thread("Update") {
			public void run() {
				long last_time = System.nanoTime();
				double ns = 1000000000/60D;
				double delta = 0;
				while (running) {
					long time  = System.nanoTime();
			        delta += (int)(time - last_time)/ns;
			        last_time = time;
			        if(delta>=1) {
			            update();
			            delta--;
			        }
			        render();
				}
			}
		};
	}

	public void start() {
		running = true;
		thread.start();
	}

	public void stop() {
		running = false;
	}

	private void update() {
		Keyboard keyboard = Keyboard.getInstance();
		keyboard.update();
		if(keyboard.menu) state = menuState;
		state.update();
		if(state.isReadyForStateChange()) {
			state = state.changeState();
			state.start();
		}
		currentFrame++;
		currentFrame %= env.getFPS();
	}

	private void render() {
		screen.clear();
		state.draw(screen);
		screen.render();
	}
}