package com.kaguya.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class CompareFiles {
	
	
	
	public static void compare(String filePath1, String filePath2) {
		try {
		final int STRINGBUILDER_SIZE = 16;
			
		RandomAccessFile file1 = new RandomAccessFile(filePath1, "r");
		RandomAccessFile file2 = new RandomAccessFile(filePath2, "r");
		
		FileChannel channel1 = file1.getChannel();
		FileChannel channel2 = file2.getChannel();
		
		long fileSize1 = channel1.size();
		long fileSize2 = channel2.size();
		
		ByteBuffer buffer1 = ByteBuffer.allocate((int) fileSize1);
		ByteBuffer buffer2 = ByteBuffer.allocate((int) fileSize2);
		
		channel1.read(buffer1);
		channel2.read(buffer2);
		
		byte tByte = 0;
		//byte pByte;
		
		//byte[] b1 = new byte[(int) fileSize1];
		//byte[] b2 = new byte[(int) fileSize2];
		
		List<String> a1 = new ArrayList<String>();
		List<String> a2 = new ArrayList<String>();
		
		StringBuilder tSB = new StringBuilder(STRINGBUILDER_SIZE);
		
		buffer1.flip();
		buffer2.flip();
		
		int transactionNumber;
		
		while (buffer1.hasRemaining()) {
			//get transaction number details
			while (tByte != 44){
				tByte = buffer1.get();
				tSB.append((char) tByte);
			}
			
			transactionNumber = Integer.parseInt(tSB.toString().replaceAll("[^0-9]", ""));
			
			tSB = new StringBuilder(STRINGBUILDER_SIZE);
			//System.out.println(transactionNumber);
			while (transactionNumber > a1.size() + 1) {
				a1.add("");
			}
			
			while (tByte != 13 && buffer1.hasRemaining())	{
				tByte = buffer1.get();
				tSB.append((char) tByte);
			}
			
			a1.add(tSB.toString());
			tSB = new StringBuilder(STRINGBUILDER_SIZE);
		}
		
		tByte = 0;
		transactionNumber = 0;
		
		while(buffer2.hasRemaining()) {
			//get transaction number details
			while (tByte != 44){
				tByte = buffer2.get();
				tSB.append((char) tByte);
			}
			transactionNumber = Integer.parseInt(tSB.toString().replaceAll("[^0-9]", ""));
			
			tSB = new StringBuilder(STRINGBUILDER_SIZE);
			
			//System.out.println(transactionNumber);
			
			while (transactionNumber > a2.size() + 1) {
				a2.add("");
			}
			
			
			while (tByte != 13 && buffer2.hasRemaining())	{
				tByte = buffer2.get();
				tSB.append((char) tByte);
			}
			
			a2.add(tSB.toString());
			tSB = new StringBuilder(STRINGBUILDER_SIZE);
		}
		
		System.out.println(a1.get(1));
		System.out.println(a2.get(6));
		
		} catch (IOException exc) {
			exc.getStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		
		compare("C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\f1.txt","C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\f2.txt");
		
		
	}

}
