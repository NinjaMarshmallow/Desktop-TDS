package engine.entities.mobs;

import java.util.ArrayList;
import java.util.List;

import util.Color;
import util.Environment;
import util.Printer;
import util.Stats;
import engine.behaviors.PlayerObserver;
import engine.graphics.Sprite;
import engine.management.Mediator;

public class Enemy extends Mob implements PlayerObserver {
	protected List<Player> players;
	protected double meleeDamage;
	protected double meleeRate;
	public Enemy() {
		super();
	}
	
	public Enemy(double x, double y, int width, int height) {
		super(x, y, new Sprite(width, height, Color.FOREST_GREEN));
		initialize();
	}
	
	public Enemy(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		initialize();
		
	}
	
	private void initialize() {
		baseSpeed = Stats.ENEMY_SPEED;
		meleeDamage = Stats.ENEMY_MELEE_DAMAGE;
		meleeRate = Stats.ENEMY_MELEE_RATE;
		Mediator.getInstance().addPlayersObserver(this);
		players = new ArrayList<Player>();
	}
	
	public void notifyOfPlayers(List<Player> players) {
		Printer.print("New Player List!");
		this.players = players;
	}
	
	public void update() {
		super.update();
		for(int i =0; i < players.size(); i++) {
			Player player = players.get(i);
			if(collides(player) && time % Environment.getInstance().getFPS()/meleeRate == 0) {
				player.damage(meleeDamage);
			}
		}
		
	}
	
}
