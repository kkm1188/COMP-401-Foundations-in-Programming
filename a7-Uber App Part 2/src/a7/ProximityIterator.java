package a7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
	
	private Iterator<Driver> driverPoolIterator;
	private Position client_position;
	private int proximity_limit;
	private Driver next_driver;
	
	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit){
		if(driver_pool == null || client_position == null || proximity_limit <= 0) {
				throw new IllegalArgumentException("values cannot be null");
		}
		
		this.driverPoolIterator = driver_pool.iterator();
		this.next_driver = null;
		this.client_position = client_position;
		this.proximity_limit = proximity_limit;
	}

	public boolean hasNext() {
		if(this.next_driver != null) {
			return true;	
		}
		while(driverPoolIterator.hasNext()) {
			Driver placeholder = driverPoolIterator.next();
			if (placeholder.getVehicle().getPosition().getManhattanDistanceTo(client_position) <= proximity_limit) {
				this.next_driver = placeholder;
				return true;
			}
		}
		return false;
	}

	public Driver next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		} else {
			Driver next_driver_local = this.next_driver;
			this.next_driver = null;
			return next_driver_local;
		}
	}
}