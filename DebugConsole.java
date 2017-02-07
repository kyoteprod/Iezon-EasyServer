
public class DebugConsole {
	private static boolean state = false;
	
	/**
	 * Turn on and off debug mode.
	 * @param onoff
	 */
	
	public static void setDebugState(boolean onoff) {
		state = onoff;
	}
	
	/**
	 * Write to the console if the debug mode is true.
	 * @param debugString
	 */
	
	public static void writeLine(String debugString) {
		if(state) {
			System.out.println(debugString);
		}
	}
}
