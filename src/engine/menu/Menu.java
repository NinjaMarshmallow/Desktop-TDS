package engine.menu;

import implementation.Screen;

import java.util.ArrayList;
import java.util.List;

import util.Color;
import engine.menu.click.StartGame;

public class Menu {
	private int backgroundColor = Color.SKY_BLUE;
	private List<Button> buttons;
	private boolean play = false;
	public Menu() {
		buttons = new ArrayList<Button>();
		Button button = new Button();
		button.setClickBehavior(new StartGame(this));
		buttons.add(button);
		buttons.add(new ExitButton());
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
	}
	
	public void startGame() {
		play = true;
	}
	
	public boolean isReady() {
		return play;
	}

}
