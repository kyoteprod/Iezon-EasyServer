
public class RequestHandler {
	/**
	 * Handles any client request.
	 * @param requestString
	 * @param socket
	 */
	
	public  void run(String requestString, ClientSocket socket) {
		System.out.println("Request was recieved but not handled...");
		return;
	}
	
	/**
	 * Starts the handler.
	 * @param requestString
	 * @param socket
	 */
	
	public void start(String requestString, ClientSocket socket) {
		this.run(requestString, socket);
	}
}
