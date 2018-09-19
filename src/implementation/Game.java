package implementation;
import util.Environment;
import util.Keyboard;
import util.Mouse;
import util.Printer;
import engine.level.World;


public class Game {
	private boolean running = false;
	private int currentFrame = 0;
	private int renderLimit = 60;
	private Screen screen;
	
	private Thread updateThread, renderThread;
	private Environment env;
	private Keyboard keyboard;
	private Mouse mouse;
	private World world;

	public Game() {
		
		mouse = new Mouse();
		keyboard = new Keyboard();
		env = Environment.getInstance();
		screen = new Screen(env.getWidth(), env.getHeight());
		screen.addInput(keyboard, mouse);
		screen.fill(0x0);
		world = new World(keyboard, screen);
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
		renderThread = new Thread("Render") {
			/*
			 * rivate long last_time = System.nanoTime();
private double ns = 1000000000/25D;
private double delta = 0;
@Override
public void run() {
    while(Universe.IsGameRunning) {
        long time  = System.nanoTime();
        delta += (int)(time - last_time)/ns;
        last_time = time;
        System.out.println(delta);
        if(delta>=1) {
            render();
            delta--;
        }
    }
}
			 * 
			 */
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
					/*double startTime = System.currentTimeMillis();
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
					}*/
				}
			}
		};
	}

	public void start() {
		running = true;
		//updateThread.start();
		renderThread.start();
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