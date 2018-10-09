package engine.menu;

import util.Environment;
import engine.menu.click.ExitProgram;

public class ExitButton extends Button {
	
	public ExitButton() {
		super();
		text = "Exit";
		clickBehavior = new ExitProgram();
		y = Environment.getInstance().getHeight()/2 + 400;
	}
	
	public ExitButton(Button button, int position, int padding) {
		super(button, position, padding, "Exit");
		clickBehavior = new ExitProgram();
	}
}
