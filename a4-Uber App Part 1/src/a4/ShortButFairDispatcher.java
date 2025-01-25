package a4;

import java.util.ArrayList;

public class ShortButFairDispatcher implements Dispatcher{

	private int number;
	private ArrayList<Driver> listDrivers = new ArrayList<Driver>();
	
	public ShortButFairDispatcher() {
		this.number = 0;
		this.listDrivers.add(null);
		this.listDrivers.add(null);
		this.listDrivers.add(null);
		this.listDrivers.add(null);
		this.listDrivers.add(null);
	}
	
	@Override
	public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
		
		number = 0;
		double shortestDistance = Integer.MAX_VALUE;
		for(int i = 0; i < availableDrivers.length; i++) {
			double double3 = availableDrivers[i].getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
			if(listDrivers.contains(availableDrivers[i])) {
				continue;
		} else if(shortestDistance > double3) {
			shortestDistance = double3;
			number = i;
		}
	}
	listDrivers.add(availableDrivers[number]);
	if (listDrivers.size() > 5) {
		listDrivers.remove(0);
	}
	System.out.println(request.complete(availableDrivers[number]).getWaitTime());
	System.out.println(request.getRideTime());
	System.out.println(request.complete(availableDrivers[number]).getProfit());
	return availableDrivers[number];

}
}