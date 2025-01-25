package a7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {

	private List<Iterable<Driver>> driver_pool;
	private List<Iterator<Driver>> driverPoolIterable;
	private Driver next_driver = null;
	private int index = 0;
	private boolean goAgain = false;
	private int size;
	private boolean check = true;
	private boolean isForward = true;

	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		if (driver_pools == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < driver_pools.size(); i++) {
			if (driver_pools.get(i) == null) {
				throw new IllegalArgumentException();
			}
		}
		this.driver_pool = driver_pools;

		this.driverPoolIterable = new ArrayList<Iterator<Driver>>();
		for (Iterable<Driver> i : driver_pools) {
			this.driverPoolIterable.add(i.iterator());
		}
		this.size = driverPoolIterable.size();

	}

	public boolean hasNext() {
		int index2 = index;

		if (this.next_driver != null) {
			return true;
		}

		if (driver_pool.isEmpty()) {
			return false;
		}

		while (check) {
			while (isForward) {
				while (index < size) {
					if (driverPoolIterable.get(index).hasNext()) {
						next_driver = driverPoolIterable.get(index).next();
						index++;
						return true;
					}
					index++;
					if (index == index2) {
						if (goAgain) {
							check = false;
						}
						goAgain = true;
					}
				}
				isForward = false;
				index--;
			}
		while (!(isForward)) {
			while (index >= 0) {
				if (driverPoolIterable.get(index).hasNext()) {
					next_driver = driverPoolIterable.get(index).next();
					index--;
					return true;
				}
				index--;
				if (index == index2) {
					if (goAgain) {
						check = false;
					}
						goAgain = true;
					}
				}
				isForward = true;
				index++;
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