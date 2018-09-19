package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	private static double xOffset, yOffset = 0;
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getB() {
		return mouseB;
	}
	
	public static void setOffset(int x, int y) {
		xOffset = x;
		yOffset = y;
	}
	
	public static void shift(int direction, double velocity) {
		if(direction == HORIZONTAL) xOffset += velocity;
		if(direction == VERTICAL) yOffset += velocity;
	}
	
	public static double getXWithOffset() {
		double newX = mouseX + xOffset;
		Printer.print("newX: " + newX);
		return newX;
	}
	
	public static double getYWithOffset() {
		double newY = mouseY + yOffset;
		Printer.print("newY" + newY);
		return newY;
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
