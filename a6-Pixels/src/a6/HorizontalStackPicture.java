package a6;

public class HorizontalStackPicture implements Picture {
	private int width;
	private int height;
	private Picture leftPicture, rightPicture;

	public HorizontalStackPicture(Picture left, Picture right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		if (left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("must be same size");
		}
		width = left.getWidth() + right.getWidth();
		height = left.getHeight();

		leftPicture = left;
		rightPicture = right;
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
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IllegalArgumentException("values not accepted");
		}
		if (x < leftPicture.getWidth()) {
			return leftPicture.getPixel(x, y);
		} else if (x >= leftPicture.getWidth()) {
			return rightPicture.getPixel(x - leftPicture.getWidth(), y);
		} else {
			return null;
		}
	}

	@Override
	public void paint(int x, int y, Pixel p) {
		paint(x, y, p, 1.0);
	}

	@Override
	public void paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= width || y < 0 || y >= height || p == null || factor < 0.0 || factor > 1.0 ) {
			throw new IllegalArgumentException("values not accepted");
		}
		if (x < leftPicture.getWidth()) {
			leftPicture.paint(x, y, p, factor);
		} else if (x >= leftPicture.getWidth() && x < getWidth()) {
			rightPicture.paint(x - leftPicture.getWidth(), y, p, factor);
		} else {
			throw new IllegalArgumentException("values not accepted");
		}
	}
	public void paint(int ax, int ay, int bx, int by, Pixel p) {
		paint(ax, ay, bx, by, p, 1.0);
	}

	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (bx < 0 || bx >= getWidth() || by < 0 
				|| by >= getHeight() || ay < 0 || ay >= getHeight()
				|| ax < 0 || ax >= getWidth() 
				|| p == null || factor < 0.0 
				|| factor > 1.0) {
			throw new IllegalArgumentException("vlaues not accepted");
		}

		int max_x = Math.max(ax, bx);
		int min_x = Math.min(ax, bx);
		int max_y = Math.max(ay, by);
		int min_y = Math.min(ay, by);
		
		if (max_x < leftPicture.getWidth()) {
			leftPicture.paint(ax, ay, bx, by, p, factor);
		} else if (min_x >= leftPicture.getWidth()) {
			rightPicture.paint(ax - leftPicture.getWidth(), ay, bx - leftPicture.getWidth(), by, p, factor);
		} else {
			leftPicture.paint(min_x, min_y, leftPicture.getWidth() - 1, max_y, p, factor);
			rightPicture.paint(0, min_y, max_x - leftPicture.getWidth(), max_y, p, factor);
		}
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p) {
		paint(cx, cy, radius, p, 1.0);
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null || factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("values not accepted");
		}
		if (cx + radius < leftPicture.getWidth()) {
			leftPicture.paint(cx, cy, radius, p, factor);
		} else if (cx - radius >= leftPicture.getWidth()) {
			rightPicture.paint(cx - leftPicture.getWidth(), cy, radius, p, factor);
		} else {
			for (int i = 0; i < leftPicture.getWidth(); i++) {
				for (int j = 0; j < leftPicture.getHeight(); j++) {
					double distance = Math.sqrt((i - cx) * (i - cx) + (j - cy) * (j - cy));
					if (distance <= radius) {
						leftPicture.paint(i, j, p, factor);
					}
				}
			}
			for (int i = leftPicture.getWidth(); i < getWidth(); i++) {
				for (int j = 0; j < leftPicture.getHeight(); j++) {
					double d = Math.sqrt((i - cx) * (i - cx) + (j - cy) * (j - cy));
					if (d <= radius) {
						rightPicture.paint(i - leftPicture.getWidth(), j, p, factor);
					}
				}
			}
		}
	}
}
