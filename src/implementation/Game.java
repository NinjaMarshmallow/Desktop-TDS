package implementation;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.graphics.Sprite;
import engine.level.Level;


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

	public Game() {
		mouse = new Mouse();
		keyboard = new Keyboard();
		env = Environment.getInstance();
		screen = new Screen(env.getWidth(), env.getHeight());
		screen.addInput(keyboard, mouse);
		screen.fill(0x0);
		world = new Level(keyboard, screen, Sprite.LEVEL1, Sprite.LEVEL1_ENEMIES);
		updateThread = new Thread("Update") {
			public void run() {
				while (running) {
					double startTime = System.currentTimeMillis();
					update();
					render();
					double endTime = System.currentTimeMillis();
					double timeTaken = endTime - startTime;
					double timeBetweenFrames = 1000 / env.getFPS();
					if (timeTaken > timeBetweenFrames) {
						Printer.print("Updating is taking too long...", Printer.FLAGS.WARNING);
					} else {
						int sleepTime = (int) (timeBetweenFrames - timeTaken);
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		thread = new Thread("Render") {
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
		//updateThread.start();
		thread.start();
	}

	public void stop() {
		running = false;
	}

	private void update() {
		world.update();
		currentFrame++;
		currentFrame %= env.getFPS();
	}

	private void render() {
		screen.clear();
		world.draw();
		screen.render();
	}
}