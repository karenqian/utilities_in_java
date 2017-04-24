package com.kaguya.utils;

public class Transaction {

	private String _id;

	public void setId(String id) {
		_id = id;
	}

	public String getId() {
		return _id;
	}

	private String _value;

	public void setValue(String value) {
		_value = value;
	}

	public String getValue() {
		return _value;
	}
	
	@Override
	public String toString() {
		return "[" + _id + "]:[" + _value + "]";
	}
	
}
