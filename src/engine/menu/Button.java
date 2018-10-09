package engine.menu;

import implementation.Screen;

import java.awt.Font;

import util.Color;
import util.Environment;
import util.Mouse;
import util.text.TextObject;
import engine.behaviors.Positionable;

public class Button implements Positionable {
	protected int x, y, width, height;
	protected ClickBehavior clickBehavior;
	protected int backgroundColor, foregroundColor, normalColor, highlightColor;
	protected String text;
	protected Font font;
	public Button() {
		initializePosition(Environment.getInstance().getWidth()/2, Environment.getInstance().getHeight()/4, 300, 100);
		initializeStyle(Color.GOLD, Color.MAROON, "Play", new Font("Consolas", Font.BOLD, 24));
	}
	
	public Button(int x, int y, int width, int height, int backgroundColor, int foregroundColor, String text) {
		initializePosition(x, y, width, height);
		initializeStyle(backgroundColor, foregroundColor, text, new Font("Consolas", Font.BOLD, 24));
	}
	
	public Button(Button button, int position, int padding, String text) {
		int yOffset = 0, xOffset = 0;
		switch(position) {
		case 0:
			yOffset = button.height - padding;
			break;
		case 1:
			xOffset = button.width + padding;
			break;
		case 2:
			yOffset = button.height + padding;
			break;
		case 3:
			xOffset = button.width - padding;
			break;
		}
		initializePosition((int)button.getX() + xOffset, (int)button.getY() + yOffset, button.width, button.height);
		initializeStyle(button.backgroundColor, button.foregroundColor, text, button.font);
	}
	
	private void initializePosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	private void initializeStyle( int backgroundColor, int foregroundColor, String text, Font font) {
		this.backgroundColor = normalColor = backgroundColor;
		this.highlightColor = Color.lighten(normalColor);
		this.foregroundColor = foregroundColor;
		this.text = text;
		this.font = font;
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
		if(x > this.x - width/2 && x < this.x + width/2 ) {
			if(y > this.y - height/2 && y < this.y + height/2) {
				return true;
			}
		}
		return false;
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
