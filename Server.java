import java.io.IOException;

public class Server {
	public static RequestHandler rh = new RequestHandler();
	
	/**
	 * Example of connection
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		// Check if we should compile the Server in debug mode
		if(args.length < 0 && args[0].equalsIgnoreCase("true")) {
			DebugConsole.setDebugState(true);
		}
		
		// Create a request handler
		rh = new RequestHandler() {
			@Override
			public void run(String requestString, ClientSocket socket) {
				System.out.println(socket.getIpAddress() + " sent a request: " + requestString);
				socket.sendRequest("bar");
				return;
			}
		};
		
		// Start the Server
		ServerBuilder server = new ServerBuilder(43594);
	}
}
