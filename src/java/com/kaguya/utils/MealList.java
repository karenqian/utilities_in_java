package com.kaguya.utils;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class MealList {
	
	private List<Meal> _mealList = new ArrayList<Meal>();
	
	public void loadFromFile(String filePath) throws IOException {
		try (FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr)) {
			String line = br.readLine();
			while(line != null) {
				String[] items = line.split(",");
				if (items.length != 3) {
					System.err.println("Unrecognized line: " + line);
					continue;
				}
				
				Meal m = new Meal();
				m.setStudentId(items[0]);
				m.setMealType(items[1]);
				m.setDate(items[2]);
				
				_mealList.add(m);
				
				line = br.readLine();
				
			}
		}
	}
	
	public List<Meal> sortByDate(){
			
		Collections.sort(_mealList, new Comparator<Meal>() {
		    @Override
		    public int compare(Meal o1, Meal o2) {
		        final String date1 = o1.getDate();
		        final String date2 = o2.getDate();
		        return date1.compareTo(date2);
		    }
		});
		
		return _mealList;
	}
	
	public void debugPrint() {
		System.out.println(_mealList);
	}
	
	public void prettyDebugPrint(){
		for (Meal m:_mealList) {
			System.out.println(m.toString());
		}
	}
}
