package automatic_parking.com.car;

public class Car {
	String RegistrationNumber;
	String Color;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(String registration_number) {
		RegistrationNumber = registration_number;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	@Override
	public String toString() {
		return "\nRegistration Number=" + RegistrationNumber + "\nColor=" + Color ;
	}
	

}
