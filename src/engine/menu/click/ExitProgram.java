package engine.menu.click;

import engine.menu.ClickBehavior;

public class ExitProgram implements ClickBehavior {

	public void onClick() {
		//SaveManager.save()
		System.exit(0);
	}

}
