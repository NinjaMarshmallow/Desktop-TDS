package engine.menu.click;


public class ExitProgram implements ClickBehavior {

	public void onClick() {
		//SaveManager.save()
		System.exit(0);
	}

}
