package util.text;

import java.awt.Font;

import util.Color;


public class TextObject {
	
	private int x, y, color;
	private String text;
	private Font font;
	
	public TextObject(String text) {
		initialize(0, 0, text);
		this.color = Color.BLACK;
	}
	
	public TextObject(int x, int y, String text) {
		initialize(x, y, text);
		this.color = Color.BLACK;
		this.font = new Font("Cambria", Font.PLAIN, 24);
	}
	
	public TextObject(int x, int y, String text, Font font, int color) {
		initialize(x, y, text);
		this.color = color;
		this.font = font;
	}
	
	private void initialize(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
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
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public Font getFont() {
		return font;
	}
	
	public int getWidth() {
		return font.getSize()/2 * text.length();
	}
	
	public int getHeight() {
		return font.getSize();
	}
}
