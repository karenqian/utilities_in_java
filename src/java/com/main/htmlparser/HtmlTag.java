package com.main.htmlparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtmlTag extends HtmlElement {

	private String name = "";
	final private String type = "HTML_TAG";

	public String getType() {
		return type;
	}

	private List<HtmlElement> children = new ArrayList<HtmlElement>();

	public String getIdentity() {
		return name;
	}

	public HtmlTag(String s) {
		name = s;
	}

	public HtmlTag addChild(HtmlElement e) {
		if (e != null) {
			children.add(e);
		}

		return this;
	}

	public String toString(int n) {

		StringBuilder sb = new StringBuilder();

		sb.append("+ " + "HtmlTag: <" + name + ">" + System.lineSeparator());

		for (HtmlElement he : children) {
			sb.append(String.join("", Collections.nCopies(n + 1, "|")) + he.toString(n + 1));
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return toString(0);
	}

	public void debug() {
		System.out.println(toString());
	}

	public void dump(String filePath) {

		File file = new File(filePath);
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));) {

			bw.write("<!DOCTYPE html>" + System.lineSeparator());
			bw.write(buildString());

			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String buildString() {

		StringBuilder sb = new StringBuilder();
		sb.append("<" + name + ">");
		for (HtmlElement he : children) {
			if ("HTML_TAG".equals(he.getType())) {
				sb.append(System.lineSeparator() + he.buildString());
			} else if ("HTML_TXT".equals(he.getType())) {
				sb.append(he.getIdentity() + " ");
			}
		}

		if ((sb.charAt(sb.length() - 1)) == ' ') {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("</" + name + ">" + System.lineSeparator());

		return sb.toString();
	}

}
