package engine.entities.mobs.enemy;

import java.util.ArrayList;
import java.util.List;

import util.Color;
import util.Environment;
import util.Stats;
import engine.behaviors.PlayerObserver;
import engine.behaviors.attack.AttackBehavior;
import engine.behaviors.attack.DamageOnCollide;
import engine.entities.mobs.Mob;
import engine.entities.mobs.Player;
import engine.graphics.Sprite;
import engine.management.Mediator;

public class Enemy extends Mob implements PlayerObserver {
	protected List<Player> players;
	protected double meleeRate;
	protected AttackBehavior attackBehavior;
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
		attackBehavior = new DamageOnCollide();
	}
	
	public void notifyOfPlayers(List<Player> players) {
		this.players = players;
	}
	
	public void update() {
		super.update();
		attack();
	}
	
	public void attack() {
		if(players.size() > 0) {
			for(int i =0; i < players.size(); i++) {
			attackBehavior.execute(this, players.get(i));
			}
		} else {
			attackBehavior.reset();
		}
	}
}