package engine.menu;

import util.Environment;
import engine.menu.click.ExitProgram;

public class ExitButton extends Button {
	
	public ExitButton() {
		super();
		text = "Exit";
		clickBehavior = new ExitProgram();
		y = Environment.getInstance().getHeight()/2 + 200;
	}

}
