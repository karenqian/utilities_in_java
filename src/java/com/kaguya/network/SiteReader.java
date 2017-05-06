package com.kaguya.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class SiteReader {

	public void test() {
		try {
			URL site = new URL("https://www.google.com");
			BufferedReader br = new BufferedReader(new InputStreamReader(site.openStream(), "utf-8"));

			String in;
			while ((in = br.readLine()) != null) {
				System.out.println(in);
			}

			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void getSite(URL u, String filePath) {
		
		getSite(u.getHost(), u.getFile() == "" ? "/" : u.getFile(), 80, filePath);
		
	}

	public static void getSite(String hostname, String url, int port, String filePath) {
		try {
			Socket socket = new Socket(hostname, port);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));

			writer.println("GET " + url + " HTTP/1.1");
			writer.println("Host: " + hostname);
			writer.println("Accept: */*");
			writer.println("User-Agent: Java");
			writer.println("Connection: close");
			writer.println("");
			writer.flush();

			File targetFile = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));

			String in;
			String responseCode;
			int c = 0;

			mainloop: while ((in = reader.readLine()) != null) {

				if (c == 0) {
					c++;
					responseCode = in.substring(9, 12);

					if (responseCode.substring(0, 1).equals("3")) {
						while (!"Location".equals(in.substring(0, 8))) {
							in = reader.readLine();
						}

						URL u = new URL(in.substring(10, in.length()));

						getSite(u.getHost(), u.getFile() == "" ? "/" : u.getFile(), 80, filePath);

						break mainloop;
					}
				}
				System.out.println(in);
				if (in.isEmpty()) {
					while ((in = reader.readLine()) != null) {
						bw.write(in);
						bw.newLine();
						bw.flush();

						c = c + in.length();
					}

				}

			}

			System.out.println("imout");

			bw.close();
			socket.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//getSite("www.google.com", "/", 80, "D:\\test.html");
		try {
		getSite(new URL("http://www.google.com"),"D:\\test.html");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}