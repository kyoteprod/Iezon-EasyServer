import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServerBuilder {
	
	private static ServerSocket socket;
	private static Timer timer = new Timer();
	private static ArrayList<ClientSocket> sockets = new ArrayList<ClientSocket>();
	
	/**
	 * Opens a connection on the node and creates a background thread to accept connections.
	 * @param portNumber
	 * @throws IOException
	 */
	
	public ServerBuilder(int portNumber) throws IOException {
		DebugConsole.writeLine("Attempting to open connection on port " + portNumber + "...");
		socket = new ServerSocket(portNumber);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					DebugConsole.writeLine("Listening for Client connections on port " + portNumber + "...");
					ClientSocket s = new ClientSocket(openConnection());
					sockets.add(s);
					ClientListener.addListener(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 1000, 1000);
	}
	
	/**
	 * Creates a socket connection to the client when it arrives.
	 * @return Socket
	 * @throws IOException
	 */
	
	public static Socket openConnection() throws IOException {
		Socket client = socket.accept();
		DebugConsole.writeLine(client.getRemoteSocketAddress() + " connected to the Server...");
		return client;
	}
	
	/**
	 * Remove a socket from the ArrayList.
	 * @param socket
	 */
	
	public static void removeSocket(ClientSocket socket) {
		int index = 0;
		for(ClientSocket s : sockets) {
			if(s.getIpAddress().equalsIgnoreCase(socket.getIpAddress())) {
				break;
			}
			index++;
		}
		sockets.get(index).destroy();
		sockets.remove(index);
	}

}
