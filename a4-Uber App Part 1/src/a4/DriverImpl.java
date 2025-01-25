package a4;


public class DriverImpl implements Driver{
	private String first;
	private String last;
	private int id;
	private Vehicle vehicle;
	
	public DriverImpl(String first, String last, int id, Vehicle vehicle) { 
		this.first = first;
		if(first == null) {
			throw new RuntimeException("Null values are not excepted");
		}
		
		this.last = last;
		if(last == null) {
			throw new RuntimeException("Null values are not excepted");
		}
		
		this.id = id;
		
		this.vehicle = vehicle;
		if(vehicle == null) {
			throw new RuntimeException("Null values are not excepted");
		}
	}
	public String getFirstName() {
		return first;
	}
	public String getLastName() {
		return last;
	}
	
	public int getID() {
		return id;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle v) {
		if(v == null) {
			throw new RuntimeException("Null values are not excepted");
		}
		this.vehicle = v;
	}
} 