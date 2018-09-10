package engine.behaviors;

import implementation.Screen;

public interface Drawable {
	
	public void update();
	public void draw(Screen screen);
	public int readPixel(int x, int y);
}
