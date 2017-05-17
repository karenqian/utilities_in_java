package com.main.htmlparser2;

import java.io.*;

public class HtmlParser {

	private File file;
	private EzReader er;

	String cachedFilePath;
	String cachedEncoding;

	public HtmlParser() {

	}

	public HtmlParser(String filePath) {
		this(filePath, "UTF-8");
	}

	public HtmlParser(String filePath, String encoding) {
		feed(filePath, encoding);
	}

	public void feed(String filePath) {
		feed(filePath, "UTF-8");
	}

	public void feed(String filePath, String encoding) {
		try {
			cachedFilePath = filePath;
			cachedEncoding = encoding;

			file = new File(filePath);
			er = new EzReader(new InputStreamReader(new FileInputStream(file), encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void resetPointer() {
		try {
			file = new File(cachedFilePath);
			er = new EzReader(new InputStreamReader(new FileInputStream(file)));
		} catch (IOException ex) {

		}
	}

	// Parse work from here down.

	public String getDocTypeDeclaration() {
		
		// runs all the way til first <html> tag
		
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			while (!"<html>".equals(er.readChars(6))) {
				sb.append((char) er.read());
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return sb.toString().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
	}

	public HtmlDocument parse() {
		
		int i;
		Parsed p;
		HtmlDocument doc = null;
		
		try {
			doc = new HtmlDocument();
			doc.setDocTypeDeclaration(getDocTypeDeclaration());
			

			while ((i = er.read()) != -1) {
				if(i == '<'){
					
					// Handle tag + children check
					
					handleTagStart();
					checkChildren();
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return doc;
	}
	
	private HtmlTag handleTagStart(){ // No support for attributes
		
		return new HtmlTag(er.readUntil('>'));
		
	}

	public Parsed parseIndividual(){
		try {
			
		} catch () {
			
		}
	}

}
