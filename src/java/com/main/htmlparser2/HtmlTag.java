package com.main.htmlparser2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlTag implements Parsed{
	
	private String type = "TAG";
	private String name;
	private Map m = new HashMap<>();
	private List<Parsed> children = new ArrayList<Parsed>();
	
	public String getType(){
		return type;
		m.
	}
	
	public HtmlTag(String s) {
		name = s;
	}
	
	public void setName(String s){
		name = s;
	}
	
	public String getName(){
		return name;
	}
	
}
