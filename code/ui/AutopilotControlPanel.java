package code.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import code.autopilot.AutoPilotSystem;


/*
 * Autopilot Control Panel
 * This contains all the code allowing the pilot to engage and disengage the autopilot,
 * and the controls for a manual override.
 * 
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 27/05/2024
 */
public class AutopilotControlPanel{

    // Fields
    /**private boolean autopilotStatus;
    private double altitude;
    private double speed;
    private double heading;
    private boolean faultStatus;**/
    private static AutoPilotSystem autoPilotSystem;
    private static AutoPilotSystem backupAutoPilotSystem;
    private static JPanel panel;
    private static JButton autopilotButton;
    private static JToggleButton manualOverrideButton;
    private static JSlider altitudeSlider;
    private static JSlider speedSlider;
    private static JSlider headingSlider;
    private static JLabel autopilotStatusLabel;

    /**
     * Initializes all the components of the control panel
     */
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

    /**
     * Allows other classes to access the JPanel itself,
     * and therefore make changes to the appearance of the 
     * control panel.
     * @return The panel
     */
    public static JPanel getPanel(){
        return panel;
    }

    /**
     * Enables the controls (they are disabled by default at the start of a flight)
     */
    public static void enableControls(){
        autopilotButton.setEnabled(true);
        altitudeSlider.setEnabled(true);
        speedSlider.setEnabled(true);
        headingSlider.setEnabled(true);
    }

    /**
     * Allows the control panel to access the main and backup 
     * autopilot systems.
     * @param aps
     * @param baps
     */
    public static void addAutoPilotSystem(AutoPilotSystem aps, AutoPilotSystem baps){
        autoPilotSystem = aps;
        backupAutoPilotSystem = baps;
    }

    /**
     * Starts the autopilot systems and disables controls for manual override.
     */
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

    /**
     * Disables the autopilot systems and enables controls for manual override.
     */
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
