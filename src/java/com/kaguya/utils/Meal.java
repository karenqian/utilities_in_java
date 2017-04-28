package com.kaguya.utils;

import java.time.*;

public class Meal {

	private String _studentId;
	private String _mealType;
	private LocalDate _date;

	public void setDate(String date) {
		String[] dateArray = date.split("/|,|-");
		setDate(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));

	}

	public void setDate(int year, int month, int dayOfMonth) {
		_date = LocalDate.of(year, month, dayOfMonth);
	}
	
	public String getDate(){
		return _date.toString();
	}

	public void setStudentId(String studentId) {
		_studentId = studentId;
	}

	public void setMealType(String mealType) {
		_mealType = mealType;
	}
	
	@Override
	public String toString() {
		return "[" + _date + "]:[" + _studentId + "]:[" + _mealType + "]";
	}
	

}
