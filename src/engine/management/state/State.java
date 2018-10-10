package engine.management.state;

import implementation.Screen;

public interface State {
	
	public void update();
	public void draw(Screen screen);
	public State changeState();
	public boolean isReadyForStateChange();
	public void start();
}
