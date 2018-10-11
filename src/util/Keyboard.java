package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private static Keyboard instance;
	private boolean[] keys = new boolean[1000];
	public boolean up, down, left, right;
	public boolean sprint;
	public boolean exit;
	public boolean menu;
	
	public static void build() {
		instance = new Keyboard();
	}
	
	public static Keyboard getInstance() {
		if(instance == null) {
			instance = new Keyboard();
		}
		return instance;
	}
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		sprint = keys[KeyEvent.VK_SHIFT];
		menu = keys[KeyEvent.VK_ESCAPE];
		
		exit = keys[KeyEvent.VK_1];
		
		for(int q = 0; q < keys.length; q++) {
			if(keys[q]) {
				//System.out.println("Key: " + q);
			}
		}
	}
	
	public void keyPressed(KeyEvent q) {
		keys[q.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent q) {
		keys[q.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent q) {}
}