package com.model;

public class Car {
	
	String Registration_Number;
	String Color;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getRegistration_Number() {
		return Registration_Number;
	}
	public void setRegistration_Number(String registration_number) {
		Registration_Number = registration_number;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	@Override
	public String toString() {
		return "\nRegistration_Number=" + Registration_Number + "\nColor=" + Color ;
	}
	
	
	
	

}
