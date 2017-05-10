package com.main.htmlparser;

public class HtmlText extends HtmlElement{
	
	final private String type = "HTML_TXT";
	
	public String getType(){
		return type;
	}
	
	private String text = "";
	
	public String getIdentity() {
		return text;
	}
	
	public void setText(String s) {
		text = s;
	}
	
	public HtmlText(String s) {
		text = s;
	}
	
	public HtmlText() {
		
	}
	
	@Override
	public String toString() {
		return "+ HtmlTxt: " + text + System.lineSeparator();
	}
	
	public String toString(int n) {
		return toString();
	}
}
