package a7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver>{

	private Iterator<Driver> driverPoolIterator;
	private Position client_position;
	private int expansion_step;
	private Driver next_driver;
	public Iterable<Driver> driver_pool_copy;
	private int size = 0;
	private int prox_limit = 1;
	private int prox_limit2 = 0;
	private int count = 0;
	private int next_count = 0;

	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		if(driver_pool == null || client_position == null || expansion_step <= 0) {
			throw new IllegalArgumentException("Cannot be these values");
		}

		this.client_position = client_position;
		this.expansion_step = expansion_step;
		this.next_driver = null;
		this.driver_pool_copy = driver_pool;
		this.driverPoolIterator = driver_pool_copy.iterator();
		
		Iterator<Driver> place = driver_pool_copy.iterator();
		while(place.hasNext()) {
			place.next();
			size++;
		}


	}

	@Override
	public boolean hasNext() {

		if(this.next_driver != null) {
			return true;	
		}
		while(next_count < size) {
			while(driverPoolIterator.hasNext()) {
				Driver placeholder = driverPoolIterator.next();
				if (placeholder.getVehicle().getPosition().getManhattanDistanceTo(client_position) <= prox_limit && 
						(placeholder.getVehicle().getPosition().getManhattanDistanceTo(client_position) > prox_limit2)) {
					this.next_driver = placeholder;
					next_count += 1;
					return true;
				}
			}
			if(next_count < size) {
				count++;
				prox_limit = 1 + (count * expansion_step);
				prox_limit2 = 1 + ((count - 1) * expansion_step);
				this.driverPoolIterator = driver_pool_copy.iterator();
			}
		}

		return false;

	}
	@Override
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