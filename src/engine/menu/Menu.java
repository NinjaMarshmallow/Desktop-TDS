
package engine.menu;

import implementation.Screen;

import java.util.ArrayList;
import java.util.List;

import util.Color;
import util.Environment;
import util.text.TextObject;

public class Menu {
	
	protected int backgroundColor = Color.SKY_BLUE;
	protected List<Button> buttons;
	protected boolean play = false;
	protected TextObject title;
	protected Menu next;
	protected int ABOVE = 0, BELOW = 2, RIGHT = 1, LEFT = 3;
	protected int normalPadding = 100;
	
	public Menu() {
		initialize(new TextObject(Environment.getInstance().getWidth()/2, 200, "Title"));
	}
	
	public Menu(TextObject title) {
		initialize(title);
	}
	
	protected void initialize(TextObject title) {
		this.title = title;
		buttons = new ArrayList<Button>();
		next = null;
	}
	
	public void setButtons(Button... buttons) {
		for(Button button : buttons) {
			this.buttons.add(button);
		}
	}
	
	public void setTitle(TextObject text) {
		title = text;
	}
	
	public void update() {
		for(int i = 0; i < buttons.size(); i++) {
			buttons.get(i).update();
		}
	}
	
	public void draw(Screen screen) {
		screen.fill(backgroundColor);
		for(int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(screen);
		}
		screen.renderText(title);
	}
	
	public void startGame() {
		play = true;
	}
	
	public boolean isReady() {
		return play;
	}
	
	public void setNextMenu(Menu menu) {
		this.next = menu;
	}
	
	public boolean hasNextMenu() {
		return next != null;
	}
	
	public Menu nextMenu() {
		return next;
	}
}
