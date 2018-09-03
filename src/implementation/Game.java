package implementation;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.Entity;
import engine.Player;


public class Game {
	private boolean running = false;
	private int currentFrame = 0;
	private int renderLimit = 120;
	private Screen screen;
	private Entity player;
	private Thread updateThread, renderThread;
	private Environment env;
	private Keyboard keyboard;
	private Mouse mouse;

	public Game() {
		env = Environment.getInstance();
		keyboard = new Keyboard();
		player = new Player(keyboard);
		screen = new Screen(env.getWidth(), env.getHeight());
		screen.addInput(keyboard, mouse);
		screen.fill(0x0);
		updateThread = new Thread("Update") {
			public void run() {
				while (running) {
					double startTime = System.currentTimeMillis();
					update();
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
		renderThread = new Thread("Render") {
			public void run() {
				while (running) {
					double startTime = System.currentTimeMillis();
					render();
					double endTime = System.currentTimeMillis();
					double timeTaken = endTime - startTime;
					double timeBetweenFrames = 1000 / renderLimit;
					if (timeTaken > timeBetweenFrames) {
						if (renderLimit > 30) renderLimit -= 30;
						Printer.print("Rendering is taking too long. Switching to " + renderLimit + " FPS", Printer.FLAGS.WARNING);
						
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
	}

	public void start() {
		running = true;
		updateThread.start();
		renderThread.start();
	}

	public void stop() {
		running = false;
	}

	private void update() {
		player.update();
		currentFrame++;
		currentFrame %= env.getFPS();
	}

	private void render() {
		screen.clear();
		player.draw(screen);
		screen.render();
	}
}