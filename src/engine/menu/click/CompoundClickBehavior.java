package engine.menu.click;

public class CompoundClickBehavior implements ClickBehavior {
	
	private ClickBehavior click1, click2;
	
	public CompoundClickBehavior(ClickBehavior click1, ClickBehavior click2) {
		this.click1 = click1;
		this.click2 = click2;
	}
	public void onClick() {
		this.click1.onClick();
		this.click2.onClick();
	}

}
