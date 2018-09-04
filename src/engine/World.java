package engine;

import implementation.Screen;
import util.Keyboard;

public class World {
	
	private Keyboard keyboard;
	private Player player;
	private Mediator mediator;
	
	public World(Keyboard keyboard) {
		this.keyboard = keyboard;
		mediator = Mediator.getInstance();
		player = new Player(keyboard);
		
	}
	
	public void update() {
		player.update();
		mediator.update();
	}
	
	public void draw(Screen screen) {
		mediator.draw(screen);
		player.draw(screen);
	}
}
