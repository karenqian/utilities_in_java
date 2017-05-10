package com.main.htmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlParser {
	
	// THIS IS A MESS  HAh AH AH H

	private File file;
	private BufferedReader br;

	final String TAG_OPENER = "<";
	final String TAG_CLOSER = ">";
	final String CLOSING_TAG_MARKER = "/";
	final String VALUE_MARKER = "";

	public void feed(String filePath, String encoding) {
		try {
			file = new File(filePath);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void feed(String filePath) {
		feed(filePath, "UTF-8");
	}

	// public HtmlElement parseElement() {
	// return parseElement(null);
	// }

	public HtmlTag parse() {

		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HtmlTag he;
		he = (HtmlTag) parseElement();

		return he;
	}

	public HtmlElement parseElement() {
		try {

			StringBuilder psb1 = new StringBuilder();
			int i1;

			HtmlTag ht;
			HtmlElement he;

			if ((i1 = br.read()) == '<') {

				if ((i1 = br.read()) == '/') {
					while ((i1 = br.read()) != '>') {
					}
					return null;
				}

				psb1.append((char) i1);

				while ((i1 = br.read()) != '>') {
					psb1.append((char) i1);
				}

				ht = new HtmlTag(psb1.toString());

				while ((he = parseElement()) != null) {
					ht.addChild(he);
				}

				return ht;

			} else if (i1 == '\n' || i1 == '\r') {
				return parseElement();
			} else {

				psb1.append((char) i1);
				while ((i1 = br.read()) != '<' && i1 != '\n' && i1 != '\r') {
					psb1.append((char) i1);
					br.mark(5);
				}

				br.reset();

				return new HtmlText(psb1.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("SOMETHING GOES WRONG");
		return null;
	}

	public HtmlParser(String filePath, String encoding) {
		feed(filePath, encoding);
	}

	public HtmlParser(String filePath) {
		feed(filePath);
	}

	public HtmlParser() {

	}

	// DEBUG AREA

	StringBuilder sb;

	public void brToSbAndPrint() {
		String line;
		sb = new StringBuilder();
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());

	}

	public void test() {
		int hold;
		try {

			br.mark(5);

			for (int k = 0; k < 10; k++) {
				hold = br.read();
				System.out.print((char) hold);
			}

			br.reset();

			for (int k = 0; k < 10; k++) {
				hold = br.read();
				System.out.print((char) hold);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
