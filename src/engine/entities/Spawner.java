package engine.entities;

import implementation.Screen;
import engine.behaviors.Drawable;
import engine.behaviors.NullSpawnBehavior;
import engine.behaviors.Positionable;
import engine.behaviors.SpawnBehavior;
import engine.management.Mediator;

public class Spawner implements Positionable, Drawable {
	private SpawnBehavior spawnBehavior;
	private double x, y;
	private int time = 0;
	private int reloadTime = 60;
	public Spawner(double x, double y) {
		initialize(x, y);
		this.spawnBehavior = new NullSpawnBehavior();
	}
	
	public Spawner(double x, double y, SpawnBehavior spawnBehavior) {
		initialize(x, y);
		this.spawnBehavior = spawnBehavior;
	}
	
	private void initialize(double x, double y) {
		this.x = x;
		this.y = y;
		Mediator.getInstance().add(this);
	}
	
	public void setSpawnBehavior(SpawnBehavior spawnBehavior) {
		this.spawnBehavior = spawnBehavior;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double distanceTo(Positionable pos) {
		double xdiff = (this.x - pos.getX()) * (this.x - pos.getX());
		double ydiff = (this.y - pos.getY()) * (this.y - pos.getY());
		double result = Math.sqrt(xdiff + ydiff);
		return result;
	}
	
	public double distanceTo(double x, double y) {
		double xdiff = (this.x - x) * (this.x - x);
		double ydiff = (this.y - y) * (this.y - y);
		double result = Math.sqrt(xdiff + ydiff);
		return result;
	}

	public double angleTo(Positionable pos) {
		double xdiff = pos.getX() - this.x;
		double ydiff = pos.getY() - this.y;
		double result = Math.atan2(ydiff, xdiff);
		return result;
	}
	
	public double angleTo(double x, double y) {
		double xdiff = x - this.x;
		double ydiff = y - this.y;
		double result = Math.atan2(ydiff, xdiff);
		return result;
	}

	public void update() {
		if (time % reloadTime == 0) this.spawnBehavior.execute(this);
		time++;
	}

	public void draw(Screen screen) {
	}

	public int readPixel(int x, int y) {
		return 0;
	}

	public boolean isAlive() {
		return true;
	}
}
