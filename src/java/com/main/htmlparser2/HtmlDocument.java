package com.main.htmlparser2;

public class HtmlDocument {
	
	String docTypeDeclaration;
	HtmlTag htmlTag;
	
	public HtmlDocument() {
		
	}
	
	
	
	public HtmlDocument(String docType, HtmlTag inputElement){
		this.setDocTypeDeclaration(docType);
		this.setHtmlTag(inputElement);
	}
	
	public void setDocTypeDeclaration(String docType){
		this.docTypeDeclaration = docType;
	}
	
	public String getDocTypeDeclaration() {
		return docTypeDeclaration;
	}
	
	public void setHtmlTag(HtmlTag inputElement) {
		htmlTag = inputElement;
	}
	
}
