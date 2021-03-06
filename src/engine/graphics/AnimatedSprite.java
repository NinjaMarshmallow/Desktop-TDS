package engine.graphics;

import implementation.Screen;

import java.util.List;

import util.Color;
import util.Printer;
import engine.behaviors.Drawable;
import engine.management.Mediator;

public class AnimatedSprite extends Sprite implements Drawable {
	
	public static final int EXPLOSION = 1;
	public static final int WATERMELON = 2;
	public static final int ELECTRIC = 3;
	public static final int HATCHET_BREAK = 4;
	
	private Sprite currentSprite;
	private Sprite[] frames;
	private int time, currentFrame;
	private double animationSpeed;
	private int x, y;
	private boolean alive;
	
	public AnimatedSprite() {
		super(100, 100, Color.BLACK);
		frames = new Sprite[3];
		frames[0] = new Sprite(100, 100, Color.BLACK);
		frames[1] = new Sprite(100, 100, Color.PINK);
		frames[2] = new Sprite(100, 100, Color.BLACK);
		currentSprite = frames[0];
		alive = true;
		time = currentFrame = 0;
		animationSpeed = 1;
	}
	
	public AnimatedSprite(List<Sprite> sprites) {
		super(sprites.get(0).getWidth(), sprites.get(0).getHeight(), Color.BLACK);
		initialize(sprites);
	}
	
	public AnimatedSprite(List<Sprite> sprites, double animationSpeed) {
		super(sprites.get(0).getWidth(), sprites.get(0).getHeight(), Color.BLACK);
		initialize(sprites);
		this.animationSpeed = animationSpeed;
	}
	
	private void initialize(List<Sprite> sprites) {
		frames = new Sprite[sprites.size()];
		for(int i = 0; i < sprites.size(); i++) {
			frames[i] = sprites.get(i);
		}
		currentSprite = frames[0];
		alive = true;
		time = currentFrame = 0;
		animationSpeed = 1;
	}
	
	public void play(int x, int y) {
		this.x = x;
		this.y = y;
		Mediator.getInstance().add(this);
		Printer.print("Play!");
		System.out.println("Play");
		
	}

	public void update() {
		if(!alive) {
			System.out.println("Remove");
			Mediator.getInstance().remove(this);
		} else {
			int mod = ((int) (7/animationSpeed));
			if(time % mod == 0) {
				if(currentFrame > frames.length - 1) {
					alive = false;
				} else {
					currentSprite = frames[currentFrame++];
				}
			}
		}
		time++;
	}

	public void draw(Screen screen) {
		System.out.println(currentSprite == null);
		screen.renderSprite(x, y, currentSprite);
	}

	public int readPixel(int x, int y) {
		return currentSprite.getPixelAt(x, y);
	}

	public boolean isAlive() {
		return alive;
	}
	
	
	
}
