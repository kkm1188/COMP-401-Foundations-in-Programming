package a6;

public class VerticalStackPicture implements Picture {
	
	private int pWidth;
	private int pHeight;
	private Picture topPicture, bottomPicture;
	
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		if (top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("must be equal sizes");
		}
		pWidth = top.getWidth();
		pHeight = top.getHeight() + bottom.getHeight();
		
		topPicture = top;
		bottomPicture = bottom;
		
		
	}

	@Override
	public int getWidth() {
		return topPicture.getWidth();
	}

	@Override
	public int getHeight() {
		return topPicture.getHeight() + bottomPicture.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("not acceptable values");
		}
		if (y < topPicture.getHeight()) {
			return topPicture.getPixel(x, y);
		} else if (y >= topPicture.getHeight()) {
			return bottomPicture.getPixel(x, y - topPicture.getHeight());
		} else {
			return null;
		}
	}



	@Override
	public void paint(int x, int y, Pixel p, double factor) {
		if(x < 0 || x >= pWidth || y < 0 || y >= pHeight || p == null || factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("invalid" );
		}
		if (y < topPicture.getHeight()) {
			topPicture.paint(x, y, p, factor);
		} else if (y >= topPicture.getHeight()){
			bottomPicture.paint(x, y - topPicture.getHeight(), p, factor);
		} else {
			throw new IllegalArgumentException("Invalid");
		}
	}

	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p) {
		paint(ax, ay, bx, by, p, 1.0);
	}

	@Override
	public void paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null || ax<0 || ax>=getWidth() || ay<0 || ay>=getHeight() || bx<0 || bx>=getWidth() || by<0 || by>=getHeight() || factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("null");
		}
		
		int max_x = Math.max(ax, bx);
		int min_x = Math.min(ax, bx);
		int max_y = Math.max(ay, by);
		int min_y = Math.min(ay, by);
		if (max_y < topPicture.getHeight()) {
			topPicture.paint(ax,  ay, bx, by, p, factor);
		} else if (min_y >= topPicture.getHeight()) {
			bottomPicture.paint(ax, ay - topPicture.getHeight(), bx, by - topPicture.getHeight(), p, factor);
		} else {
			topPicture.paint(min_x, min_y, max_x, topPicture.getHeight() - 1, p, factor);
			bottomPicture.paint(min_x,  0,  max_x, max_y - topPicture.getHeight(), p, factor);
		}
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p) {
		paint(cx, cy, radius, p, 1.0);
	}

	@Override
	public void paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null || factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("Parameters invalid.");
		}
		if (cy + radius < topPicture.getHeight()) {
			topPicture.paint(cx, cy, radius, p, factor);
		} else if (cy - radius >= topPicture.getHeight()) {
			bottomPicture.paint(cx, cy - topPicture.getHeight(), radius, p, factor);
		} else {
			for (int i = 0; i < topPicture.getWidth(); i++) {
				for (int j = 0; j < topPicture.getHeight(); j++) {
					double distance = Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy));
					if (distance <= radius) {
						topPicture.paint(i, j, p);
					}
				}
			}
			for (int i = 0; i < topPicture.getWidth(); i++) {
				for (int j = topPicture.getHeight(); j < getHeight(); j++) {
					double distance = Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy));
					if (distance <= radius) {
						bottomPicture.paint(i, j - topPicture.getHeight(), p);
					}
				}
			}
		}
	}

}





