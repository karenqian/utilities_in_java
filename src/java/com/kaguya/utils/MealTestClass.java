package com.kaguya.utils;

public class MealTestClass {
	
	public static void main(String[] args) throws Exception{
		
		MealList m = new MealList();
		m.loadFromFile("C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\m1.txt");
		
		m.prettyDebugPrint();
		m.sort();
		System.out.println();
		m.prettyDebugPrint();
		
		
	}
	
}
