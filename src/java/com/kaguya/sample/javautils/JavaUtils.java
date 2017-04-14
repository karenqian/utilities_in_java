package javautils;

import java.nio.channels.*;
import java.io.*;

public class JavaUtils {
	
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
	public static void main(String[] args){
		File srcFile = new File("asdasd");
		File destFile = new File("");
		
		try {
			copyFileUsingChannel(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("haha");
			e.printStackTrace();
		}
	}
	
}
