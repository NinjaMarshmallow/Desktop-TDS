package engine.menu.click;

public class CompoundClickBehavior implements ClickBehavior {
	
	private ClickBehavior[] clicks;
	
	public CompoundClickBehavior(ClickBehavior ... clickBehaviors) {
		this.clicks = clickBehaviors;
	}
	
	public void onClick() {
		for(ClickBehavior click : clicks) {
			click.onClick();
		}
	}

}
