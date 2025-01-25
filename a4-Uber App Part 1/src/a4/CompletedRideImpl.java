package a4;

public class CompletedRideImpl implements CompletedRide {
	private RideRequest request;
	private Driver driver;
	private int waitTime;
	
	public CompletedRideImpl(RideRequest request, Driver driver) {
		this.request = request;
		if(request == null) {
			throw new RuntimeException("An explanation of why");
		}
		this.driver = driver;
		if(driver == null) {
			throw new RuntimeException("An explanation of why");
		}
		this.waitTime = driver.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
	}
	

	@Override
	public int getWaitTime() {
		return waitTime;
	}
	@Override
	public RideRequest getRequest() {
		return request;
	}
	@Override
	public Driver getDriver() {
		return driver;
	}
} 