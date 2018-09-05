package engine;

import implementation.Screen;
import util.Color;
import util.Environment;
import util.Keyboard;
import engine.entities.mobs.Enemy;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.management.Mediator;

public class World {
	
	private Keyboard keyboard;
	private Player player;
	private Enemy enemy;
	private Mediator mediator;
	private Sprite sprite;
	
	public World(Keyboard keyboard) {
		this.keyboard = keyboard;
		mediator = Mediator.getInstance();
		player = new Player(keyboard);
		enemy = new Enemy(500, 400, 150, 150);
		mediator.add(enemy);
		mediator.add(player);
		Environment env = Environment.getInstance();
		this.sprite = new Sprite(env.getWidth(), env.getHeight(), Color.FOREST_GREEN);
	}
	
	public void update() {
		mediator.update();
	}
	
	public void draw(Screen screen) {
		screen.renderSprite(0, 0, sprite);
		mediator.drawEntities(screen);
		mediator.drawEnemies(screen);
		mediator.drawPlayers(screen);
		mediator.drawProjectiles(screen);
	}
}
