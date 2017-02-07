import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ClientListener {
	private static ArrayList<Timer> timers = new ArrayList<Timer>();
	
	/**
	 * Creates a listener on the Socket for requests and starts the handler on the response.
	 * @param socket
	 */
	
	public static void addListener(ClientSocket socket) {
		int index = timers.size();
		timers.add(new Timer());
		timers.get(index).scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				String responseString;
				try {
					DebugConsole.writeLine("Server is now listening to " + socket.getIpAddress());
					if((responseString = socket.getResponse()) != null) {
						Server.rh.start(responseString, socket);
						DebugConsole.writeLine(socket.getIpAddress() + " sent a request...");
					}
				} catch (Exception e) {
					ServerBuilder.removeSocket(socket);
					DebugConsole.writeLine("Server has stopped listening to " + socket.getIpAddress());
					timers.get(index).cancel();
				}
			}
		}, 1000, 1000);
	}
}