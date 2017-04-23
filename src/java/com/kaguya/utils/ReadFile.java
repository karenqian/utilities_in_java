package com.kaguya.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

	public static void main(String[] args) {
		stupid("C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\base.csv");
	}

	public static void testReadFile(String filePath) {
		final int BUFFER_SIZE = 4096;
		final String SPACE = " ";

		try (FileInputStream fis = new FileInputStream(filePath); InputStream is = new BufferedInputStream(fis)) {
			byte[] buffer = new byte[BUFFER_SIZE];

			int n = is.read(buffer);
			while (n > 0) {
				for (int i = 0; i < n; i++) {
					byte b = buffer[i];
					if (b == '\r') { // 0x0d
						// ignore
					} else if (b == '\n') { // 0x0a
						System.out.println();
					} else {
						String s = Integer.toHexString(b);
						if (s.length() == 1) {
							s = "0" + s;
						}
						System.out.print(s + SPACE);
					}
				}

				n = is.read(buffer);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void stupid(String filePath) {
		try (RandomAccessFile aFile = new RandomAccessFile(filePath, "r"); FileChannel inChannel = aFile.getChannel()){
			// RandomAccessFile aFile = new RandomAccessFile(
			// "D:\\asdf.txt","r");
			
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);

			int bytesRead = inChannel.read(buffer);
			System.out.println(bytesRead);

			byte tByte;

			//buffer.rewind();
			buffer.flip();
			while(buffer.hasRemaining()) {
				tByte = buffer.get();

				if (tByte == 32) {
					System.out.print(",");
				} else {
					System.out.print((char) tByte);
				}
			}
		} catch (IOException exc) {
			System.out.println(exc);
			System.exit(1);
		}
	}

	public static void textInNumbers(String filePath) { //OLD
		try {
			// RandomAccessFile aFile = new RandomAccessFile(
			// "D:\\asdf.txt","r");
			RandomAccessFile aFile = new RandomAccessFile(filePath, "r");
			FileChannel inChannel = aFile.getChannel();
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);

			int bytesRead = inChannel.read(buffer);
			System.out.println(bytesRead);

			inChannel.read(buffer);

			byte tByte;

			// buffer.rewind();
			buffer.flip();
			for (int i = 0; i < fileSize; i++) {
				tByte = buffer.get();
				if (tByte == 13) {
					System.out.println();
				} else {
					System.out.print(tByte + " ");
				}
			}
			inChannel.close();
			aFile.close();
		} catch (IOException exc) {
			System.out.println(exc);
			System.exit(1);
		}
	}
	
	public static void testMethod(String filePath) {
		
	}

}
