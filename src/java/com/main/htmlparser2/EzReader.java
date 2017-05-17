package com.main.htmlparser2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class EzReader extends BufferedReader {
	
	
	public EzReader(Reader reader){
		super(reader);
	}
	
	public EzReader(Reader reader, int sz){
		super(reader, sz);
	}
	
	// Stops pointer on char c without printing char c.
	public String readUntil(char c) {

		int i;
		StringBuilder sb = new StringBuilder();
		try {
			while ((i = this.read()) != c) {
				sb.append((char) i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	// Stops pointer one index before char c without printing char c.
	public String readUntilBack1(char c) {
		int i;
		StringBuilder sb = new StringBuilder();
		try {
			this.mark(2);
			while ((i = this.read()) != c) {
				this.mark(2);
				sb.append((char) i);
			}
			this.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public String readChars(int chars) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		int k = 0;
		
		this.mark(chars + 1);
		while (k<chars){
			sb.append((char) this.read());
			k++;
		}
		this.reset();
		return sb.toString();
		
		
	}
	
}
