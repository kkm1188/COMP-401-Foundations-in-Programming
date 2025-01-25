package a6;

public class MonochromePicture implements Picture {

		private int width;
		private int height;
		private Pixel value;
	
	public MonochromePicture(int width, int height, Pixel value) {
		if(width < 1) {
			throw new IllegalArgumentException("value cannot be less than one");
		}
		if(height < 1) {
			throw new IllegalArgumentException("value cannot be less than one");
		}
		if(value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		
		this.width  = width;
		this.height = height; 
		this.value = value;
		
	}
	
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x > getWidth() - 1 || x < 0 || y > getHeight() - 1 || y < 0) {
			throw new IllegalArgumentException("Out of range");
		}
		return value;
	}

	@Override
	public void paint(int x, int y, Pixel p) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void paint(int x, int y, Pixel p, double factor) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p, double factor) {
		throw new UnsupportedOperationException();
		
	}
	
	
}