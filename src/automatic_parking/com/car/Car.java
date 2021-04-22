package automatic_parking.com.car;

public class Car {
	String registration_Number;
	String color_of_car;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getregistration_Number() {
		return registration_Number;
	}
	public void setregistration_Number(String registration_number) {
		registration_Number = registration_number;
	}
	public String getcolor_of_car() {
		return color_of_car;
	}
	public void setcolor_of_car(String color) {
		color_of_car = color;
	}
	@Override
	public String toString() {
		return "\nRegistration Number=" + registration_Number + "\nColor=" + color_of_car ;
	}
	

}
