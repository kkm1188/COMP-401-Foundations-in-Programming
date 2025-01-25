package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.math.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class InfoView extends JPanel implements PandemicModelObserver, ChangeListener, ActionListener {
	
	public static final String START_BUTTON = "Start";
	public static final String STOP_BUTTON = "Stop";
	
	
	private PandemicModel _model;
	private PandemicView _view;
	
	private JPanel infoPanel;
	
	private JPanel resetPanel;
	private JTextField popField;
	private JTextField scaleField;
	private JTextField stepField;
	private JButton _startStop;
	
	private JPanel sliderPanel;
	private SpringLayout sliderLayout; 

	private JLabel _time;
	private JLabel _infected;
	private JLabel _recovered;
	private JLabel _dead;
	private JLabel _damage;
	
	private JLabel[] sliderLabels;
	private JSlider[] sliders;
	
	public InfoView(PandemicModel model, PandemicView view) {
		_model = model;
		_view = view;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(650,220));
		_model.addObserver(this);
		
		
		_time = new JLabel("Time: " + _model.getTime() + " days\t");
		_infected = new JLabel("Infected: " + _model.getInfectedCount() + "\t");
		_recovered = new JLabel("Recovered: " + _model.getRecoveredCount() + "\t");
		_dead = new JLabel("Dead: " + _model.getDeadCount() + "\t");
		_damage = new JLabel("Damage: " + _model.getEconomicDamage() + "\t");
		_startStop = new JButton("Start");
		_startStop.setName("startStop");
		_startStop.addActionListener(this);
		
		
		infoPanel = new JPanel();
		infoPanel.add(_startStop);
		infoPanel.add(_time);
		infoPanel.add(_infected);
		infoPanel.add(_recovered);
		infoPanel.add(_dead);
		infoPanel.add(_damage, BorderLayout.LINE_END);
		
		resetPanel = new JPanel();
		addResetComponents();
		
		sliderPanel = new JPanel();
		sliderLayout = new SpringLayout();
		sliderPanel.setLayout(sliderLayout);
		sliderLabels = new JLabel[5];
		sliders = new JSlider[5];
		addSliders();
		
		add(infoPanel, BorderLayout.NORTH);
		add(sliderPanel, BorderLayout.CENTER);
		add(resetPanel, BorderLayout.SOUTH);
	}
	
	public void addResetComponents() {
		JLabel popLabel = new JLabel("Population: ");
		popField = new JTextField("100", 5);
		resetPanel.add(popLabel);
		resetPanel.add(popField);
		
		JLabel scaleLabel = new JLabel("Scale: ");
		scaleField = new JTextField("100", 5);
		resetPanel.add(scaleLabel);
		resetPanel.add(scaleField);
		
		JLabel stepLabel = new JLabel("Step Size: ");
		stepField = new JTextField("0.01", 5);
		resetPanel.add(stepLabel);
		resetPanel.add(stepField);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setName("reset");
		resetPanel.add(resetButton);
	}
	
	public void addSliders() {
		sliderLabels[0] = new JLabel("Sim Speed (steps/sec): 100");
		JSlider _simSpeed = new JSlider(1,1000,100);
		_simSpeed.addChangeListener(this);
		_simSpeed.setName("simSpeed");
		sliders[0] = _simSpeed;
		
		sliderLabels[1] = new JLabel("Lockdown Factor: 0%");
		JSlider _lockdown = new JSlider(0,100,0);
		_lockdown.addChangeListener(this);
		_lockdown.setName("lockdown");
		sliders[1] = _lockdown;
		
		sliderLabels[2] = new JLabel("Asymptomacity: 50%");
		JSlider _asymp = new JSlider(0,100,50);
		_asymp.addChangeListener(this);
		_asymp.setName("asymp");
		sliders[2] = _asymp;
		
		sliderLabels[3] = new JLabel("Mortality: 5%");
		JSlider _mortality = new JSlider(0,100,5);
		_mortality.addChangeListener(this);
		_mortality.setName("mort");
		sliders[3] = _mortality;
		
		sliderLabels[4] = new JLabel("Disease Duration: 10.0");
		JSlider _duration = new JSlider(0,200,100);
		_duration.addChangeListener(this);
		_duration.setName("duration");
		sliders[4] = _duration;
		
		for (int i=0; i<5; i++) {
			sliderLayout.putConstraint(SpringLayout.WEST, sliderLabels[i], 5, SpringLayout.WEST, sliderPanel);
			sliderLayout.putConstraint(SpringLayout.NORTH, sliderLabels[i], 5+30*i, SpringLayout.NORTH, sliderPanel);
			sliderLayout.putConstraint(SpringLayout.NORTH, sliders[i], 30*i, SpringLayout.NORTH, sliderPanel);
			sliderLayout.putConstraint(SpringLayout.WEST, sliders[i], 5, SpringLayout.EAST, sliderLabels[i]);
			sliderPanel.add(sliderLabels[i]);
			sliderPanel.add(sliders[i]);
		}
	}
	
	@Override
	public void update(PandemicModel model, PandemicModelEvent event) {
		DecimalFormat df = new DecimalFormat("0.##");
		_time.setText("Time: " + df.format(model.getTime()) + " days\t");
		_infected.setText("Infected: " + model.getInfectedCount() + "\t");
		_recovered.setText("Recovered: " + model.getRecoveredCount() + "\t");
		_dead.setText("Dead: " + model.getDeadCount() + "\t");
		_damage.setText("Damage: " + model.getEconomicDamage() + "\t");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		double newValue = source.getValue();
				
		switch (source.getName()) {
			case "simSpeed":
				sliderLabels[0].setText("Sim Speed (steps/sec): " + (int)newValue);
				break;
			case "lockdown":
				sliderLabels[1].setText("Lockdown Factor: " + (int)newValue + "%");
				break;
			case "asymp":
				sliderLabels[2].setText("Asymptomacity: " + (int)newValue + "%");
				break;
			case "mort":
				sliderLabels[3].setText("Mortality: " + (int)newValue + "%");
				break;
			case "duration":
				sliderLabels[4].setText("Disease Duration: " + newValue/10.0);
				break;
			default:
				break;
		}
		if (!source.getValueIsAdjusting()) {
			switch (source.getName()) {
			case "simSpeed":
				_view.setSimSpeed((int)newValue);
				break;
			case "lockdown":
				_model.setLockdownFactor(newValue / 100.0);
				break;
			case "asymp":
				_model.setAsymptomaticity(newValue / 100.0);
				break;
			case "mort":
				_model.setMortality(newValue / 100.0);
				break;
			case "duration":
				_model.setDiseaseDuration(newValue / 10.0);
				break;
			default:
				break;
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getName()) {
			case "reset":
				sliderLabels[0].setText("Sim Speed (steps/sec): " + PandemicModel.SIM_SPEED);
				sliders[0].setValue(100);
				sliderLabels[1].setText("Lockdown Factor: " + (int)(100*PandemicModel.LOCKDOWN_FACTOR) + "%");
				sliders[1].setValue(0);
				sliderLabels[2].setText("Asymptomacity: " + (int)(100*PandemicModel.ASYM_PERC) + "%");
				sliders[2].setValue(50);
				sliderLabels[3].setText("Mortality: " + (int)(100*PandemicModel.MORTALITY) + "%");
				sliders[3].setValue(5);
				sliderLabels[4].setText("Disease Duration: " + PandemicModel.DISEASE_DURATION);
				sliders[4].setValue(100);
				_startStop.setText(START_BUTTON);
				_view.reset(popField.getText(), scaleField.getText(), stepField.getText());
				break;
			case "startStop":
				switch (_startStop.getText()) {
					case START_BUTTON:
						_startStop.setText(STOP_BUTTON);
						_view.start();
						break;
					case STOP_BUTTON:
						_startStop.setText(START_BUTTON);
						_view.stop();
						break;
					default: 
						break;
				}
				break;
		}
		
		
	}

}
