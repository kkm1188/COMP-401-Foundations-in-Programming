package a4;

import a4.RideRequest;

public class RideRequestImpl implements RideRequest {
	private Position clientPosition;
	private Position destination;
	private boolean isComplete;
	private CompletedRideImpl ride;
	
	public RideRequestImpl(Position clientPosition, Position destination) {
		
		this.clientPosition = clientPosition;
		if(clientPosition == null) {
			throw new RuntimeException("Not acceptable to call null");
		}
		this.destination = destination;
		if(destination == null) { 
			throw new RuntimeException("Not acceptable to call null");
		}
		isComplete = false;
	}

	public Position getClientPosition() {
		return clientPosition;
	}
	@Override
	public Position getDestination() {
		return destination;
	}
	public boolean getIsComplete() {
		return isComplete;
		
			}
	
	public CompletedRide complete(Driver driver) {
		
		if(isComplete) {
			return ride;
		} else {
			isComplete = true;
			ride = new CompletedRideImpl(this, driver);
			
			driver.getVehicle().moveToPosition(clientPosition);
			driver.getVehicle().moveToPosition(destination);
			return ride; 
		}
	}
}    