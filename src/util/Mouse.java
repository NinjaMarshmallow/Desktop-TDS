package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getB() {
		return mouseB;
	}
	
	public void mouseMoved(MouseEvent q) {
		mouseX = q.getX();
		mouseY = q.getY();
	}
		
	public void mousePressed(MouseEvent q) {
		mouseB = q.getButton();
	}

	public void mouseReleased(MouseEvent q) {
		mouseB = -1;
	}
	
	public void mouseDragged(MouseEvent q) {
		mouseX = q.getX();
		mouseY = q.getY();
	}
	public void mouseClicked(MouseEvent q) {}
	public void mouseEntered(MouseEvent q) {}
	public void mouseExited(MouseEvent q) {}

}
