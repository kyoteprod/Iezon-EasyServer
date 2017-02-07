import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
	
	private Socket socket;
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private String ipAddress;
	private boolean authState = false;
	
	/**
	 * Constructor method to instance the input and output streams.
	 * @param connection
	 * @throws IOException
	 */
	
	public ClientSocket(Socket connection) throws IOException {
		this.socket = connection;
		this.outputStream = new PrintWriter(this.socket.getOutputStream(), true);
		this.inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.ipAddress = this.socket.getRemoteSocketAddress().toString();
		this.outputStream.println("test"); // sends a test
	}
	
	public boolean authenticate(String responseText) {
		if(responseText == Server.authenticationString) {
			this.authState = true;
			return true;
		} else {
			DebugConsole.writeLine(this.ipAddress + " was not authenticated...");
			return false;
		}
	}
	
	/**
	 * Check to see if the Client has been authenticated
	 * @return Authentication State
	 */
	
	public boolean isAuthenticated() {
		return this.authState;
	}
	
	/**
	 * Send a request to the Client.
	 * @param requestString
	 */
	
	public void sendRequest(String requestString) {
		this.outputStream.println(requestString);
		DebugConsole.writeLine("Server sent " + this.ipAddress + " a request...");
	}
	
	/**
	 * Read the Client request sent to the Server.
	 * Note - If the method throws an Exception, the connection died.
	 * @return Request String
	 * @throws Exception containing Socket IP Address
	 */
	
	public String getResponse() throws Exception {
		try {
			return this.inputStream.readLine();
		} catch (IOException e) {
			throw new Exception("Client Socket is no longer active.");
		}
	}
	
	/**
	 * Get the IP Address of the Client Socket.
	 * @return IP Address
	 */
	
	public String getIpAddress() {
		return this.ipAddress;
	}
	
	/**
	 * Destroy the connection Socket to the client.
	 * @throws IOException
	 */
	
	public void destroy() {
		try {
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
			DebugConsole.writeLine(this.ipAddress + " disconnected from the Server...");
		} catch (IOException e) { }
	}
}
