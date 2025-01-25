package a4;

public class PositionImpl implements Position{
	private int x;
	private int y;

	public PositionImpl(int x, int y) { 
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
} 