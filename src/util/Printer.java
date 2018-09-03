package util;

public class Printer {

	private static boolean debug = true;
	public static final int NORMAL = 0;
	public static final int WARNING = 1;
	public static final int ERROR = 2;
	public enum FLAGS { 
		RENDER, PLAYER, WARNING, ERROR 
	}

	public static void print(Object message) {
		if (debug) System.out.println(message);
	}
	
	public static void print(Object message, FLAGS flag) {
		switch(flag) {
		case WARNING:
			System.out.println(message);
			break;
		case ERROR:
			System.err.println(message);
			break;
		default:
			System.out.println(message);
		}
	}

}
