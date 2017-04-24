package com.kaguya.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class TransactionList {

	private List<Transaction> _transactions = new ArrayList<Transaction>();
	
	public void loadFromFile2(String filePath) throws IOException {
		try (FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr)) {
			String line = br.readLine();
			while(line != null) {
				String[] items = line.split(",");
				if (items.length != 2) {
					System.err.println("Unrecognized line: " + line);
					continue;
				}
				
				Transaction t = new Transaction();
				t.setId(items[0]);
				t.setValue(items[1]);
				
				_transactions.add(t);
				
				line = br.readLine();
			}
		}
	}

	public void loadFromFile(String filePath) throws IOException {
		RandomAccessFile file1 = new RandomAccessFile(filePath, "r");
		FileChannel channel1 = file1.getChannel();
		long fileSize1 = channel1.size();
		ByteBuffer buffer1 = ByteBuffer.allocate((int) fileSize1);
		channel1.read(buffer1);
		byte tByte = 0;
		buffer1.flip();
		StringBuilder tSB;

		while (buffer1.hasRemaining()) {

			tSB = new StringBuilder();

			Transaction t = new Transaction();
			// get transaction number details
			
			tByte = buffer1.get();
			while (tByte != ',') {
				tSB.append((char) tByte);
				tByte = buffer1.get();
			}

			t.setId(tSB.toString().trim());
			tSB = new StringBuilder();
			tByte = 0;

			while (tByte != 13 && buffer1.hasRemaining()) {
				tByte = buffer1.get();
				tSB.append((char) tByte);
			}

			t.setValue(tSB.toString().trim());

			_transactions.add(t);
		}

		channel1.close();
		file1.close();

	}

	public void saveToFile(String filePath) {
		// TODO:
	}
	
	public void debugPrint() {
		System.out.print(_transactions);
	}
	
}
