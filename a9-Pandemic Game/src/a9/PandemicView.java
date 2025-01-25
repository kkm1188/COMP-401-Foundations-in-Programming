package a9;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import a9.PandemicModelEvent.PMEType;

public class PandemicView extends JPanel implements PandemicModelObserver {

	private PandemicModel _model;

	private World _world;
	
	private InfoView _infoView;

	private List<PandemicViewObserver> _observers;

	public PandemicView(PandemicModel model) {
		_observers = new ArrayList<PandemicViewObserver>();

		_model = model;
		_model.addObserver(this);

		_world = new World(model, this);
		_infoView = new InfoView(model, this);

		setLayout(new BorderLayout());
		add(_world, BorderLayout.CENTER);
		add(_infoView, BorderLayout.PAGE_END);

		// Start a thread for
		// controlling the application via 
		// commands entered on the console.

		new Thread(new Runnable() {
			public void run() {
				Scanner s = new Scanner(System.in);
				
				// Commands recognized:
				// help
				// reset <int population>? <double scale>? <double step_size>?
				// step <int num_steps>? <int delay_between_steps>?

				
				while (true) {
					String command_line = s.nextLine();
					String[] parts = command_line.split(" ");

					if (parts.length > 0) {
						String command = parts[0].trim();

						if (command.equals("reset")) {
							try {
								int population = (parts.length > 1) ? Integer.parseInt(parts[1]) : _model.getPopulation();
								double scale = (parts.length > 2) ? Double.parseDouble(parts[2]) : _model.getScale();
								double step_size = (parts.length > 3) ? Double.parseDouble(parts[3]) : _model.getStepSize();

								notifyObservers(new PVEReset(population, scale, step_size));
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: reset <int population>? <double scale>? <double step_size>?");
							}
						} else if (command.equals("step")) {
							try {
								int num_steps = (parts.length > 1) ? Integer.parseInt(parts[1]) : 1;
								int delay = (parts.length > 2) ? Integer.parseInt(parts[2]) : 0;
								for (int i=0; i<num_steps; i++) {
									notifyObservers(new PVEStep());
									try {
										Thread.sleep(delay);
									} catch (InterruptedException e) {
									}
								}
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: step <int num_steps>? <int delay>?");
							}
						} else if (command.equals("lockdown")) {
							try {
								if (parts.length > 1) {
									double lockdown_factor = Double.parseDouble(parts[1]);
									lockdown_factor = (lockdown_factor < 0.0) ? 0.0 : (lockdown_factor > 1.0) ? 1.0 : lockdown_factor;
									_model.setLockdownFactor(lockdown_factor);
								}
								System.out.println("Lockdown factor: " + _model.getLockdownFactor());
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: lockdown <double lockdown_factor>?");
							}
						} else if (command.equals("mortality")) {
							try {
								if (parts.length > 1) {
									double mortality = Double.parseDouble(parts[1]);
									mortality = (mortality < 0.0) ? 0.0 : (mortality > 1.0) ? 1.0 : mortality;
									_model.setMortality(mortality);
								}
								System.out.println("Mortality: " + _model.getMortality());
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: mortality <double mortality>?");
							}
						} else if (command.equals("asymptomaticity")) {
							try {
								if (parts.length > 1) {
									double asymptomaticity = Double.parseDouble(parts[1]);
									asymptomaticity = (asymptomaticity < 0.0) ? 0.0 : (asymptomaticity > 1.0) ? 1.0 : asymptomaticity;
									_model.setAsymptomaticity(asymptomaticity);
								}
								System.out.println("Asymptomaticity: " + _model.getAsymptomaticity());								
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: asymptomaticity <double asymptomaticity>?");
							}
						} else if (command.equals("duration")) {
							try {
								if (parts.length > 1) {
									double duration = Double.parseDouble(parts[1]);
									duration = (duration < 0.0) ? 0.0 : duration;
									_model.setDiseaseDuration(duration);
								}
								System.out.println("Disease Duration: " + _model.getDiseaseDuration());
							} catch (NumberFormatException e) {
								System.out.println("Error in command: " + command_line);
								System.out.println("Usage: duration <double duration>?");
							}
						} else if (command.equals("settings")) {
							System.out.println("Lockdown Factor: " + _model.getLockdownFactor());
							System.out.println("Mortality: " + _model.getMortality());
							System.out.println("Asymptomaticity: " + _model.getAsymptomaticity());
							System.out.println("Disease Duration: " + _model.getDiseaseDuration());
						} else if (command.equals("status")) {
							System.out.println("Infected count: " + _model.getInfectedCount());
							System.out.println("Recovered count: " + _model.getRecoveredCount());
							System.out.println("Dead count: " + _model.getDeadCount());
							System.out.println("Economic damage: " + _model.getEconomicDamage());
						} else if (command.equals("help")) {
							System.out.println("Recognized commands:");
							System.out.println("reset <int population>? <double scale>? <double step_size>?");
							System.out.println("step <int num_steps>? <int delay>?");
							System.out.println("lockdown <double lockdown_factor>?");
							System.out.println("mortality <double mortality_rate>?");
							System.out.println("asymptomaticity <double asymptomatic_rate>?");
							System.out.println("duration <double disease_duration>?");							
							System.out.println("settings");
							System.out.println("status");
						} else {
							System.out.println("Unrecognized command: " + command_line);
							System.out.println("Use 'help' for command list");
						}
					}
				}
			}
		}).start();
	}
	
	public void reset(String pop, String scale, String step) {
		try {
			int population = (pop != "") ? Integer.parseInt(pop) : _model.getPopulation();
			double scaleVal = (scale != "") ? Double.parseDouble(scale) : _model.getScale();
			double step_size = (step != "") ? Double.parseDouble(step) : _model.getStepSize();

			notifyObservers(new PVEReset(population, scaleVal, step_size));
		} catch (NumberFormatException e) {
			System.out.println("Error in reset");
			System.out.println("Usage: reset <int population>? <double scale>? <double step_size>?");
		}
	}
	
	public void setSimSpeed(int speed){
		notifyObservers(new PVESetSimSpeed(speed));
	}
	
	public void start() {
		notifyObservers(new PVEStart());
	}
	
	public void stop() {
		notifyObservers(new PVEStop());
	}

	public void infectArea(double x, double y, double width, double height) {
		notifyObservers(new PVEInfectArea(x, y, width, height));
	}
	
	public void addObserver(PandemicViewObserver o) {
		_observers.add(o);
	}

	public void removeObserver(PandemicViewObserver o) {
		_observers.remove(o);
	}

	private void notifyObservers(PandemicViewEvent pve) {
		for (PandemicViewObserver o : _observers) {
			o.handleEvent(pve);
		}
	}
	
	@Override
	public void update(PandemicModel model, PandemicModelEvent event) {
		if (event.getType() == PMEType.DEATH) {
			PMEDeath death = (PMEDeath) event;
			System.out.println(death.getPerson() + " died at " + String.format("%.2f", death.getTime()));
		} else if (event.getType() == PMEType.INFECTION) {
			PMEInfection infection = (PMEInfection) event;
			System.out.println(infection.getPerson() + " infected at " + String.format("%.2f", infection.getTime()));
		} else if (event.getType() == PMEType.RECOVERY) {
			PMERecovery recovery = (PMERecovery) event;
			System.out.println(recovery.getPerson() + " recovered at " + String.format("%.2f", recovery.getTime()));
		}
	}
}