# Iezon-EasyServer (c)
Easy to implement Server created by Iezon (tm). Copyright (c) 2017 Kyle Thomas, All Rights Reserved.

# Installation Instructions
Please find enclosed the MIT Liscence before continuing with this instillation.

- Inside your SRC path, copy and paste the files into a default package.
- Turn on debugging for the Server.
- Create a handler for your Client Socket requests.
- Start the Server.

# Server Debug Mode
DebugConsole is a class which allows you to write to the console when Debug mode is ON.

```java
public static void main(String[] args) {
  DebugConsole.setDebugState(true);
}
```

Once you have compiled your Server, you can access the args by running this command in your CMD window.

```batch
java -jar path/to/server.jar true
```

# Server Request Handler
To rewrite the request handler, you must first access the Server class and Override its run method.

```java
public static void main(String[] args) {
	Server.rh = new RequestHandler() {
		@Override
		public void run(String requestString, ClientSocket socket) {
			// TODO: handle the request
			if(requestString.equals("foo")) System.out.println("Bar");
			return;
		}
	};
}
```

# Sending a Request to the Client
Inside your request handler, there is a ClientSocket which is passed in. This can be used to send requests.

```java
[...]
	@Override
	public void run(String requestString, ClientSocket socket) {
		socket.sendRequest("bar");
	}
```

# Running your Server
There is a ServerBuilder class which you can just instance to start your Server which expects a port number.

```java
public static void main(String[] args) {
  ServerBuilder sb = new ServerBuilder(4444);
}
```

# Connecting to the Server and sending/recieving a request
For this to work, this must be a seperate project and this isn't part of the Server - you should create a client yourself.

```java
Socket socket = new Socket("your_ip", 4444);
PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
outputStream.println("foo");
BufferedReader inputStream = new BufferedReader(new InputStream(socket.getInputStream()));
System.out.println(inputStream.readLine()); // will return bar following the example
```
# One-Factor Authentication - Stopping fake Clients
This is only a quick fix which you can add to your request handler in the Server, it means that the first request being sent must come from a known client Auth. On the Server you can add this:

```java
Server.authenticationString = "d4cUdx73__s2ci";		// keep this complex!
server.rh = new RequestHandler() {
	@Override
	public void run(String requestString, ClientSocket socket) {
	        boolean authState, destoryState;
		if(!(authState = socket.isAuthenticated())) destroyState = socket.authenticate(requestString);
		if(!destroyState) ServerBuilder.removeSocket(socket);		// fake client, disconnect it
		if(authState) {
			// TODO: handle request
		}
	}
};
```

Inside the client, after you establish a connection, send the authenticationString over to the Server:

```java
PrintWriter outputStream = new PrintWriter(new Socket("ip_address", 4444).getOutputStream(), true);
outputStream.println("d4cUdx73__s2ci");		// your complex authentication string
```
