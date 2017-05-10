package com.main.sockettest;

import java.net.*;
import java.io.*;

public class SocketTestClient {

	private String hostName;
	private int portNumber;

	private Socket echoSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void close(){
		try {
		echoSocket.close();
		out.close();
		in.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { String hostName = args[0]; int
	 * portNumber = Integer.parseInt(args[1]);
	 * socketTestClientUserInput(hostName, portNumber); }
	 */

	public static void socketTestClientUserInput(String hostName, int portNumber) {
		System.out.println(hostName + " " + portNumber);
		try (Socket echoSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("connected");
			String userInput;
			String response = "";
			while (!"bye".equals(response)) {

				userInput = stdIn.readLine();
				out.println(userInput);
				out.flush();
				try {
					response = in.readLine();
				} catch (IOException e) {
					break;
				}
				System.out.println("echo: " + response);

			}
			System.out.println("i broke out");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SocketTestClient(String hostName, int portNumber) throws IOException {

		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	public void initiateConnection() {
		try {
			echoSocket = new Socket(hostName, portNumber);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String sendRequest(String request){
		
		String response = "";
		
		out.println(request);
		out.flush();
		
		try {
			response = in.readLine();
		} catch (IOException e) {
			System.out.println("Error: Connection closed.");
		}
		
		return response;
	}
	
	public String sendRequestUserInput(){
		String response = "";
		
		return response;
	}

	public String getHostname() {
		return hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

}
