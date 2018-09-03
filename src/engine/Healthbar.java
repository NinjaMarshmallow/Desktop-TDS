package engine;

import util.Color;

public class Healthbar extends Entity {
	private Mob owner;
	private int foregroundColor, backgroundColor, borderColor;
	private final static int height = 12;
	private final static int offset = 10;
	public Healthbar(Mob owner) {
		super(owner.getX(), owner.getY() - height - offset, owner.width, height);
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
		y = owner.getY() - height - offset;
	}
	
	private void drawRemainingHP() {
		double percentFull = owner.getHealth()/owner.getMaxHealth();
		int fillerWidth = (int) percentFull * width;
		sprite.fill(foregroundColor, fillerWidth);
	}
}
