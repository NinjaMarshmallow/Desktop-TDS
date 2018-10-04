package engine.menu;

import implementation.Screen;

import java.awt.Font;

import util.Color;
import util.Environment;
import util.Mouse;
import engine.behaviors.Positionable;

public class Button implements Positionable {
	protected int x, y, width, height;
	protected ClickBehavior clickBehavior;
	protected int backgroundColor, foregroundColor, normalColor, highlightColor;
	protected String text;
	protected Font font;
	public Button() {
		this.x = Environment.getInstance().getWidth()/2;
		this.y = Environment.getInstance().getHeight()/2;
		width = 300;
		height = 100;
		backgroundColor = normalColor = Color.GOLD;
		highlightColor = Color.SAND;
		foregroundColor = Color.MAROON;
		text = "Play";
		font = new Font("Consolas", Font.BOLD, 24);
	}
	
	public Button(int x, int y, int width, int height, int backgroundColor, int foregroundColor, String text) {
		
	}
	
	public Button(Button button, int position, int padding) {
		
	}
	
	public void setClickBehavior(ClickBehavior clickBehavior) {
		this.clickBehavior = clickBehavior;
	}
	
	public void click() {
		this.clickBehavior.onClick();
	}
	
	public void highlight() {
		this.backgroundColor = highlightColor;
	}
	
	public void unhighlight() {
		this.backgroundColor = normalColor;
	}
	
	public void update() {
		if(contains(Mouse.getX(), Mouse.getY())) {
			if(Mouse.getB() == 1) {
				click();
			} else {
				highlight();
			}
		} else {
			unhighlight();
		}
	}
	
	public boolean contains(int x, int y) {
		return true;
	}
	
	public void draw(Screen screen) {
		screen.renderRectangle(x  - width/2, y - height/2, width, height, foregroundColor, backgroundColor);
		screen.renderText(x, y, text, font, foregroundColor);
	}

	public void setX(double x) {
		this.x = (int)x;
	}

	public void setY(double y) {
		this.y = (int)y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distanceTo(Positionable pos) {
		return 0;
	}

	public double distanceTo(double x, double y) {
		return 0;
	}

	public double angleTo(Positionable pos) {
		return 0;
	}

	public double angleTo(double x, double y) {
		return 0;
	}

}
