package engine.entities.gauges;

import util.Color;
import util.Printer;
import engine.entities.Entity;
import engine.entities.mobs.Mob;

public class Healthbar extends Entity {
	private Mob owner;
	private int foregroundColor, backgroundColor, borderColor;
	private final static int height = 12;
	private int offset = 10;
	public Healthbar(Mob owner) {
		super(owner.getX(), owner.getY(), owner.getWidth(), height);
		y -= offset - height - owner.getHeight()/2;
		this.owner = owner;
		foregroundColor = Color.GREEN;
		backgroundColor = Color.RED;
		borderColor = Color.BLACK;
		sprite.fill(backgroundColor);
		
	}
	
	public void update() {
		move();
		drawRemainingHP();
	}
	
	public void move() {
		x = owner.getX();
		y = owner.getY() - offset - height;
	}
	
	private void drawRemainingHP() {
		double percentFull = owner.getHealth()/owner.getMaxHealth();
		int fillerWidth = (int) (percentFull * width);
		sprite.fill(backgroundColor);
		sprite.fill(foregroundColor, fillerWidth);
	}
}
