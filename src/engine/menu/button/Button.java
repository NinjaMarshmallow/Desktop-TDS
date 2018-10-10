package engine.menu.button;

import implementation.Screen;

import java.awt.Font;

import util.Color;
import util.Mouse;
import engine.menu.click.ClickBehavior;

public class Button {
	protected int x, y, width, height;
	protected ClickBehavior clickBehavior;
	protected int backgroundColor, foregroundColor, normalColor, highlightColor;
	protected String text;
	protected Font font;
	public Button() {
		initializePosition(300, 100);
		initializeStyle(Color.GOLD, Color.MAROON, "Play", new Font("Consolas", Font.BOLD, 24));
	}
	
	public Button(int width, int height, int backgroundColor, int foregroundColor, String text) {
		initializePosition(width, height);
		initializeStyle(backgroundColor, foregroundColor, text, new Font("Consolas", Font.BOLD, 24));
	}
	
	public Button(String text) {
		initializePosition(300, 100);
		initializeStyle(Color.GOLD, Color.MAROON, text, new Font("Consolas", Font.BOLD, 24));
	}
	
	private void initializePosition(int width, int height) {
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
		Mouse.resetB();
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
