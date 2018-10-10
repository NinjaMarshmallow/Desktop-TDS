package engine.menu;

import util.Environment;
import util.text.TextObject;

public class OptionsMenu extends Menu {
	private Menu prevMenu;
	public OptionsMenu(Menu prevMenu) {
		super(new TextObject(Environment.getInstance().getWidth()/2, 100, "Options"));
		this.prevMenu = prevMenu;
	}

}
