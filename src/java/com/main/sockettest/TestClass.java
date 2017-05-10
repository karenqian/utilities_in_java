package com.main.sockettest;

import java.io.IOException;

public class TestClass {

	public static void main(String[] args) {
		try {
			SocketTestClient s = new SocketTestClient("127.0.0.1", 7777);

			s.initiateConnection();
			System.out.println(s.sendRequest("HELLO"));
			System.out.println(s.sendRequest("bye"));
			System.out.println(s.sendRequest("HELLO"));

			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
