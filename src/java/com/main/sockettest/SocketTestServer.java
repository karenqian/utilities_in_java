package com.main.sockettest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer {

	public static void main(String[] args) throws Exception {
		SocketTestServer myServer = new SocketTestServer();
		myServer.run(7777);
	}

	public void run(int portNumber) throws Exception {
		String message;
		// Initializes the port the serverSocket will be on
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (true) {
				message = "";
				System.out.println("The Server is waiting for a client on port " + portNumber);
				// Accepts the connection for the client socket
				Socket socket = serverSocket.accept();
				try (InputStreamReader ir = new InputStreamReader(socket.getInputStream());
						BufferedReader br = new BufferedReader(ir)) {
					while (true) {

						try {
							message = br.readLine();
						} catch (IOException e) {
							break;
						}
						if (message == null) {
							break;
						}
						System.out.println(message);

						if ("hello".equalsIgnoreCase(message)) {
							PrintStream ps = new PrintStream(socket.getOutputStream());
							ps.println("Received your hello message.");
						} else if (message.equalsIgnoreCase("bye")) {
							PrintStream ps = new PrintStream(socket.getOutputStream());
							ps.println("bye");
							break;
						} else {
							PrintStream ps = new PrintStream(socket.getOutputStream());
							ps.println("Did not receive your hello message");
						}
					}

				}
			}
		}
	}
}
