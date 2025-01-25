package a4;

public class ShortestWaitDispatcher implements Dispatcher {
	
	private int number;
	
	public ShortestWaitDispatcher() {
		number = 0;
}
	
	@Override
	public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
		number = 0;
		
		if(availableDrivers == null || request == null) {
			throw new RuntimeException("Cannot have null value");
		}
		
		double shortestDistance = availableDrivers[0].getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
		
	
		for(int i = 1; i < availableDrivers.length; i++) {
			double double2 = availableDrivers[i].getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
			if(shortestDistance > double2) {
				shortestDistance = double2;
				number = i;
				
			} 
			
		}
		System.out.println(request.complete(availableDrivers[number]).getWaitTime());
		System.out.println(request.getRideTime());
		System.out.println(request.complete(availableDrivers[number]).getProfit());
		
		return availableDrivers[number];
	}
} 