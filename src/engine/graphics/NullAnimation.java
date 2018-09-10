package engine.graphics;

import java.util.List;

public class NullAnimation extends AnimatedSprite {

	public NullAnimation(List<Sprite> sprites) {
		super(sprites);
	}
	
	public void update() {
		
	}
	
	public void draw() {
		
	}
	
	public int readPixel(int x, int y) {
		return 0;
	}

}
