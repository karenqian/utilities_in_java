package com.main.htmlparser;

public class TestClass {
	
	public static void main(String[] args) {
		
		/*  
		 *  Change file destinations yourself.
		 *  HtmlParser.parse() actually supposed to return HtmlElement file, but I just casted it to a HtmlTag object because lots of the method I can't use.
		 *  I seem to have used inheritance wrong. 
		 *  
		 *  haha
		 *  
		 *  No support or attributes or anything out of the ordinary.
		 *  Just symmetrical tags.
		 *  
		 */
		
		HtmlParser h = new HtmlParser("D:\\test.html"); // Make new parser h for file stated in params.
		HtmlTag doc = h.parse(); // parse() method of HtmlParser returns a HtmlTag object, which is supposed to be the tag <html> at the start of every html document.
		
		doc.debug(); // Prints out tree of HtmlTag object.
		System.out.println(doc.buildString()); // buildString() attempts to put HtmlTag object back into HTML format.
		
		doc.dump("D:\\test2.html"); // Dumps String after buildString() is called on HtmlTag into a HTML file stated in params.
		
	}
	
}
