package engine.menu.click;

import engine.menu.Menu;

public class SwitchMenu implements ClickBehavior {
	
	private Menu menu, next;
	
	public SwitchMenu(Menu menu, Menu next) {
		this.menu = menu;
		this.next = next;
	}
	
	public void onClick() {
		menu.setNextMenu(next);
	}
}
