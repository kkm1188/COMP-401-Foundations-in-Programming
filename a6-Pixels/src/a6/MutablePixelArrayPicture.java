package a6;

public class MutablePixelArrayPicture implements Picture {

	private int width;
	private int height;
	private Pixel initial_value;
	private Pixel[][] pixelArray;
	private int minx;
	private int maxx;
	private int miny;
	private int maxy;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {

		
		if (pixel_array == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		if (pixel_array.length == 0) {
			throw new IllegalArgumentException("unacceptable value");
		}
		for (int i = 0; i < pixel_array.length; i++) { 
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("value is not accepted");
			}
			if (pixel_array[i].length != pixel_array[0].length) {
				throw new IllegalArgumentException("length is not accepted");
			}
			
		}
		this.width = pixel_array.length;
		this.height = pixel_array[0].length;
		
		this.pixelArray = new Pixel[pixel_array.length][pixel_array[0].length];
		for (int i = 0; i < pixel_array.length; i++) {
			for (int y = 0; y < pixel_array[0].length; y++) {
				
				if(pixel_array[i][y] == null) {
					throw new IllegalArgumentException("value is not accepted");
				}
				
				pixelArray[i][y] = pixel_array[i][y];
			}
		}
	}

	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {

		this.width = width;
		if(width < 1) {
			throw new IllegalArgumentException("Value out of range");
		}
		this.height = height;
		if(height < 1) {
			throw new IllegalArgumentException("Value out of range");
		}

		this.initial_value = initial_value;

		if(initial_value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		Pixel[][] pixel_array2 = new Pixel[width][height];
		
		for(int i = 0; i < width; i++) {
			for(int r = 0; r < height; r++) {
				pixel_array2[i][r] = initial_value;
			}
		}
		this.pixelArray = pixel_array2;

	}

	public MutablePixelArrayPicture(int width, int height) {

		this.width = width;
		if(width <= 0) {
			throw new IllegalArgumentException("Value out of range");
		}
		this.height = height;
		if(height <= 0) {
			throw new IllegalArgumentException("Value out of range");
		}
		GrayPixel graypixel = new GrayPixel(0.5);
		this.initial_value = graypixel;
		
		Pixel[][] pixel_array3 = new Pixel[width][height];
		
		for(int i = 0; i < width; i++) {
			for(int r = 0; r < height; r++) {
				pixel_array3[i][r] = graypixel;
			}
		}
		this.pixelArray = pixel_array3;
		
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
		if(x < 0 || x >= pixelArray.length || y < 0 || y >= pixelArray[0].length) {
			throw new IllegalArgumentException("Out of range");
		}
		return pixelArray[x][y];
	}

	@Override
	public void paint(int x, int y, Pixel p) {

		if (x < 0 || x >= pixelArray.length || y < 0 || y >= pixelArray[0].length) {
			throw new IllegalArgumentException("Value is not accepted");
		}
		if (p == null) {
			throw new IllegalArgumentException("P is null");
		}
		pixelArray[x][y] = p;
	}

	@Override
	public void paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= pixelArray.length || y < 0 || y >= pixelArray[0].length) {
			throw new IllegalArgumentException("Value is not accepted");
		}

		if (p == null) {
			throw new IllegalArgumentException("P is null");
		}

		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("Value is not accepted");
		}
		if (factor == 1.0) {
			pixelArray[x][y] = p;
		}

		pixelArray[x][y] = pixelArray[x][y].blend(p, factor);
	}

}
