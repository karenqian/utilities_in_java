package com.kaguya.network;

import java.net.*;

public class SiteReaderTest {
	
	public static void main(String[] args){
		try {
		URL url = new URL("http://www.google.com/a/b/c/d/e.html?asd=a");
		p(url.getProtocol());
		p(url.getHost());
		p(url.getPath());
		p(url.getPort());
		p(url.getFile());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public static void p(Object obj){
		System.out.println(obj);
	}

}
