package a6;

public class GradientPicture implements Picture {

	int width;
	int height;
	Pixel upper_left;
	Pixel upper_right;
	Pixel lower_left;
	Pixel lower_right;

	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {

		if(width < 1 || height < 1 || upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
			throw new IllegalArgumentException("in valid input");	
		}

		this.width = width; 
		this.height = height; 
		this.upper_left = upper_left;
		this.upper_right = upper_right;
		this.lower_left = lower_left;
		this.lower_right = lower_right;

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
		if (x > getWidth() - 1 || x< 0 || y > getHeight() -1 || y < 0) {
			throw new IllegalArgumentException("no null values");
		}
		Pixel left = upper_left.blend(lower_left, ((double) y / (double) (height - 1)));
		
		System.out.println((double) y / (double) height);
		
		Pixel right = upper_right.blend(lower_right,  ((double) y / (double) (height -1)));
		
		System.out.println((double) y / (double) height);
		
		System.out.println((double) x / (double) width);
		
		return left.blend(right, ((double) x / (double) (width- 1)));
		
	}
		
	@Override
	public void paint(int x, int y, Pixel p, double factor) {
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