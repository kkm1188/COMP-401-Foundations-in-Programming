package a4;

public class VehicleImpl implements Vehicle {
	private String make;
	private String model;
	private String plate;
	private Position position;
	private int mileage;
	
	public VehicleImpl(String make, String model, String plate, Position position) {
		this.make = make;
		if(make == null) {
			throw new RuntimeException("An explanation of why");
		}
		this.model = model;
		if(model == null) {
			throw new RuntimeException("An explanation of why");
		}
		this.plate = plate;
		if(plate == null) {
			throw new RuntimeException("An explanation of why");
		}
		this.position = position;
		if(position == null) {
			throw new RuntimeException("An explanation of why");  
		}
		mileage = 0;
	}
	
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public String getPlate() {
		return plate;
	}
	public int getMileage() { 
		return mileage;
	}
	public Position getPosition() {
		return position;
	}
	public void moveToPosition(Position p) {
		this.mileage += this.getPosition().getManhattanDistanceTo(p);
		position = p;
	}
} 