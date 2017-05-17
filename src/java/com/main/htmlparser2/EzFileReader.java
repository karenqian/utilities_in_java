package com.main.htmlparser2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO EXTEND BUFFERED READER INSTEAD

public class EzFileReader{
	
	public EzFileReader(File file) throws IOException{
		this(file, "UTF-8");
	}
	
	public EzFileReader(File file, String encoding) throws IOException{
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
	}
	
	public int read() throws IOException{
		return br.read();
	}

	public String readLine() throws IOException{
		return br.readLine();
	}
	
	public int getPointerCharacter() throws IOException{
		int i;
		br.mark(1);
		i = br.read();
		br.reset();
		return i;
	}
	
	public String readUntil(char c) {

		int i;
		StringBuilder sb = new StringBuilder();
		try {
			while ((i = br.read()) != c) {
				sb.append((char) i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public String readUntilBack1(char c) {
		int i;
		StringBuilder sb = new StringBuilder();
		try {
			br.mark(2);
			while ((i = br.read()) != c) {
				br.mark(2);
				sb.append((char) i);
			}
			br.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
