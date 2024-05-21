package code.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


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
        manualOverrideButton = new JToggleButton("Manual Override");
        altitudeSlider = new JSlider(JSlider.VERTICAL, -1000, 50000, 0);
        speedSlider = new JSlider(JSlider.VERTICAL, 0, 500, 10);
        headingSlider = new JSlider(JSlider.VERTICAL, -180, 180, 10);
        autopilotStatusLabel = new JLabel("Autopilot: Disengaged");

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

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

    public static void engageAutoPilot(){
        // start autopilot
        

    }

    public static void disengageAutoPilot(){
        
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
