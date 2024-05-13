package code.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class AutopilotControlPanel extends JPanel{

    private boolean autopilotStatus;
    private double altitude;
    private double speed;
    private double heading;
    private boolean faultStatus;

    private JButton autopilotButton;
    private JToggleButton manualOverrideButton;
    private JSlider altitudeSlider;
    private JSlider speedSlider;
    private JSlider headingSlider;
    private JLabel autopilotStatusLabel;

    public AutopilotControlPanel(){
        super(new GridLayout(6,1));
        autopilotButton = new JButton("Engage Autopilot");
        manualOverrideButton = new JToggleButton("Manual Override");
        altitudeSlider = new JSlider(JSlider.VERTICAL, -1000, 50000, 0);
        speedSlider = new JSlider(JSlider.VERTICAL, 0, 500, 10);
        headingSlider = new JSlider(JSlider.VERTICAL, -180, 180, 10);
        autopilotStatusLabel = new JLabel("Autopilot: Disengaged");

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

        setBorder(BorderFactory.createTitledBorder("Autopilot Control Panel"));
        add(autopilotButton);
        add(manualOverrideButton);
        add(new JLabel("Altitude:"));
        add(altitudeSlider);
        add(new JLabel("Speed:"));
        add(speedSlider);
        add(new JLabel("Heading:"));
        add(headingSlider);
        add(autopilotStatusLabel);
    }

    public void engageAutoPilot(){

    }

    public void disengageAutoPilot(){
        
    }

    public void adjustAltitude(double altitude){
        
    }

    public void adjustSpeed(double speed){
        
    }

    public void adjustHeading(double heading){
        
    }

    public String getAutopilotStatus(){
        return "";
    }

    public void displayIndicatorLights(){

    }
    
}
