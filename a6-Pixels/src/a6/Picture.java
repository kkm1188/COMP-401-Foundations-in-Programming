package a6;


public interface Picture {
	
	// Getters for the dimensions of a picture.
	// Width refers to the number of columns and 
	// height is the number of rows.
	
	public int getWidth();
	public int getHeight();
	
	// getPixel(x, y) retrieves the pixel at position (x,y) in the
	// picture. The coordinate (0,0) is the upper left
	// corner of the picture. The coordinate (getWidth()-1, getHeight()-1)
	// is the lower right of the picture. An IllegalArgumentException
	// is thrown if x or y are not in range.
	
	public Pixel getPixel(int x, int y);
	
	// The various forms of the paint() method return a new
	// picture object based on this picture with certain pixel
	// positions "painted" with a new value.
	
	// paint(int x, int y, Pixel p) paints the pixel at
	// position (x,y) with the pixel value p. The second 
	// form includes a factor parameter that specifies a
	// blending factor. A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.
	
	public default void paint(int x, int y, Pixel p) {
		paint(x, y, p, 1.0);
	}
	public void paint(int x, int y, Pixel p, double factor);
	
	
	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value. The second form
	// should blend with the specified factor as previously described.
	
	public default void paint(int ax, int ay, int bx, int by, Pixel p) {
		paint(ax, ay, bx, by, p, 1.0);
	}
	
	public default void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {

	if (ax < 0 || ax >= getWidth()
		|| bx < 0 || bx >= getWidth()
		|| by < 0 || by >= getHeight()
		|| ay < 0 || ay >= getHeight()) {
		throw new IllegalArgumentException("values not accepted");
	
		}
	int minx = 0;
	int maxx = 0;
	int miny = 0;
	int maxy = 0;
	
	if (ax <= bx) {
		 minx = ax;
		 maxx = bx;
	}
	if (bx < ax) {
		 minx = bx;
		 maxx = ax;
	}
	if (ay < by) {
		miny = ay;
		maxy = by;
	}
	if (by <= ay) {
		miny = by;
		maxy = ay;
	}
	 
	
	
	for ( int x = minx; x <= maxx; x++) {
		for (int y = miny; y <= maxy; y++) {
			paint(x, y, p, factor);
		}
	}
	}
	
	
	
	
	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p.  Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame). 
	
	// Calculate the distance of a particular (x,y) position to (cx,cy) with
	// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))	

	// The second form with factor, blends as previously described.
	
	public default void paint(int cx, int cy, double radius, Pixel p) {
	paint(cx, cy, radius, p, 1.0);
	}
	
	public default void paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (factor < 0.0 || factor > 2.0) {
			throw new IllegalArgumentException("value not accepted");
		}
		if (p == null) {
			throw new IllegalArgumentException("value not accepted");
		}
		if (radius < 0) {
			throw new IllegalArgumentException("value not accepted");
		}
		int minx = (int) (cx - radius - 1);
		int maxx = (int) (cx + radius + 1);
		int miny = (int) (cy - radius - 1);
		int maxy = (int) (cy + radius + 1);
		
		if (minx < 0) {
			minx = 0;
		}
		if (maxx >= getWidth()) {
			maxx = getWidth() -1;
		}
		if (maxy >= getHeight()) {
			maxy = getHeight() -1;
		}
		if (miny < 0) {
			miny = 0;
		}
	
		
		
		for (int k = minx; k <= maxx; k++) {
			for (int l = miny; l <= maxy; l++) {
				if (Math.sqrt(Math.pow((k - cx) ,2) + Math.pow((l - cy),2)) <= radius) {
					paint(k, l, p, factor);
				}
			}
		}
	}
}

