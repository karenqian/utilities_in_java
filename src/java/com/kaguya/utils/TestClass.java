package com.kaguya.utils;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) throws IOException {
		TransactionList ts1 = new TransactionList();
		TransactionList ts2 = new TransactionList();

		ts1.loadFromFile("C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\f1.txt");
		ts2.loadFromFile("C:\\Users\\jackj\\Documents\\GitHub\\utilities_in_java\\exercise\\f2.txt");

		ts1.debugPrint();
		System.out.println();
		ts2.debugPrint();
		System.out.println();

	}
}
