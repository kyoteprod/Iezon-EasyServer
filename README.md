# Iezon-EasyServer (c)
Easy to implement Server created by Iezon (c). Copyright (c) 2017 Iezon, All Rights Reserved.

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
  if(args.length < 0 && args[0].equalsIgnoreCase("true")) {
    // static method to enable mode
    DebugConsole.setDebugState(true);
	}
}
```

# Server Request Handler
To rewrite the request handler, you must first access the Server class and Override its run method.

```java
public static void main(String[] args) {
	Server.rh = new RequestHandler() {
		@Override
		public void run(String requestString, ClientSocket socket) {
			// TODO: handle the request
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
		socket.sendRequest("foo");
	}
```

# Running your Server
There is a ServerBuilder class which you can just instance to start your Server which expects a port number.

```java
public static void main(String[] args) {
  ServerBuilder sb = new ServerBuilder(4444);
}
```
