package code.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import code.autopilot.AutoPilotSystem;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 22/05/2024
 */
public class AutopilotControlPanel{

    private boolean autopilotStatus;
    private double altitude;
    private double speed;
    private double heading;
    private boolean faultStatus;

    private static AutoPilotSystem autoPilotSystem;
    private static AutoPilotSystem backupAutoPilotSystem;

    private static JPanel panel;
    private static JButton autopilotButton;
    private static JToggleButton manualOverrideButton;
    private static JSlider altitudeSlider;
    private static JSlider speedSlider;
    private static JSlider headingSlider;
    private static JLabel autopilotStatusLabel;

    public static void initialize(){
        panel = new JPanel(new GridLayout(6,1));
        autopilotButton = new JButton("Engage Autopilot");
        autopilotButton.setEnabled(false);
        manualOverrideButton = new JToggleButton("Manual Override");
        manualOverrideButton.setEnabled(false);
        altitudeSlider = new JSlider(JSlider.VERTICAL, -1000, 50000, 0);
        altitudeSlider.setEnabled(false);
        speedSlider = new JSlider(JSlider.VERTICAL, 0, 500, 10);
        speedSlider.setEnabled(false);
        headingSlider = new JSlider(JSlider.VERTICAL, -180, 180, 10);
        headingSlider.setEnabled(false);
        autopilotStatusLabel = new JLabel("Autopilot: Disengaged");

        // Add action listeners for buttons
        autopilotButton.addActionListener(e -> engageAutoPilot());
        manualOverrideButton.addActionListener(e -> disengageAutoPilot());

        panel.setBorder(BorderFactory.createTitledBorder("Autopilot Control Panel"));
        panel.add(autopilotButton);
        panel.add(manualOverrideButton);
        panel.add(new JLabel("Altitude:"));
        panel.add(altitudeSlider);
        panel.add(new JLabel("Speed:"));
        panel.add(speedSlider);
        panel.add(new JLabel("Heading:"));
        panel.add(headingSlider);
        panel.add(autopilotStatusLabel);
    }

    public static JPanel getPanel(){
        return panel;
    }

    public static void enableControls(){
        autopilotButton.setEnabled(true);
        altitudeSlider.setEnabled(true);
        speedSlider.setEnabled(true);
        headingSlider.setEnabled(true);
    }

    public static void addAutoPilotSystem(AutoPilotSystem aps, AutoPilotSystem baps){
        autoPilotSystem = aps;
        backupAutoPilotSystem = baps;
    }

    public static void engageAutoPilot(){
        autopilotButton.setBackground(Color.GREEN);
        // start autopilot
        autoPilotSystem.start();
        backupAutoPilotSystem.start();
        // make the button green
        autopilotButton.setEnabled(false);
        manualOverrideButton.setEnabled(true);
        altitudeSlider.setEnabled(false);
        speedSlider.setEnabled(false);
        headingSlider.setEnabled(false);
        autopilotStatusLabel.setText("Autopilot: Engaged");

    }

    public static void disengageAutoPilot(){
        autoPilotSystem.stop();
        backupAutoPilotSystem.stop();
        autopilotButton.setEnabled(true);
        autopilotButton.setBackground(null);
        manualOverrideButton.setEnabled(false);
        altitudeSlider.setEnabled(true);
        speedSlider.setEnabled(true);
        headingSlider.setEnabled(true);
        autopilotStatusLabel.setText("Autopilot: Disgaged");
    }

    

    public static void adjustAltitude(double altitude){
        
    }

    public static void adjustSpeed(double speed){
        
    }

    public static void adjustHeading(double heading){
        
    }

    public static String getAutopilotStatus(){
        return "";
    }

    public static void displayIndicatorLights(){

    }
    
}
