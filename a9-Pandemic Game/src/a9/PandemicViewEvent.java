package a9;

import java.awt.geom.Rectangle2D;

abstract public class PandemicViewEvent {

	public enum PVEType {INFECT_AREA, START, STOP, STEP, RESET, SET_SIM_SPEED};

	private PVEType _type;
	
	protected PandemicViewEvent(PVEType type) {
		_type = type;
	}
	
	public PVEType getType() {
		return _type;
	}
}

class PVEStart extends PandemicViewEvent {
	protected PVEStart() {
		super(PVEType.START);
	}
}

class PVEStop extends PandemicViewEvent {
	protected PVEStop() {
		super(PVEType.STOP);
	}
}

class PVEStep extends PandemicViewEvent {
	protected PVEStep() {
		super(PVEType.STEP);
	}
}

class PVESetSimSpeed extends PandemicViewEvent {
	private int stepsPerSecond;
	
	protected PVESetSimSpeed(int stepsPerSec) {
		super(PVEType.SET_SIM_SPEED);
		stepsPerSecond = stepsPerSec;
	}
	
	public int getSimSpeed() {
		return stepsPerSecond;
	}
}


class PVEReset extends PandemicViewEvent {
	private int _population;
	private double _scale;
	private double _step_size;
	
	protected PVEReset(int population, double scale, double step_size) {
		super(PVEType.RESET);
		_population = population;
		_scale = scale;
		_step_size = step_size;
	}
	
	public int getPopulation() {
		return _population;
	}
	
	public double getScale() {
		return _scale;
	}
	
	public double getStepSize() {
		return _step_size;
	}
}

class PVEInfectArea extends PandemicViewEvent {

	private Rectangle2D.Double _area;
	
	protected PVEInfectArea(double x, double y, double w, double h) {
		super(PVEType.INFECT_AREA);
		
		_area = new Rectangle2D.Double(x, y, w, h);
	}

	public Rectangle2D.Double getArea() {
		return _area;
	}
}
