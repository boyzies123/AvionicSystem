package code.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */
public class SensorDataDisplay extends JPanel{

    private double airspeed;
    private double altitude;
    private double pitch;
    private double roll;
    private double yaw;
    private double thrust;


    private static JPanel panel;
    private static JLabel airspeedLabel;
    private static JLabel altitudeLabel;
    private static JLabel pitchLabel;
    private static JLabel rollLabel;
    private static JLabel yawLabel;
    private static JLabel engineLabel;

    public static void initialize(){
        panel = new JPanel(new GridLayout(6,1));
        airspeedLabel = new JLabel("Airspeed: ");
        altitudeLabel = new JLabel("Altitude: ");
        pitchLabel = new JLabel("Pitch: ");
        rollLabel = new JLabel("Roll: ");
        yawLabel = new JLabel("Yaw: ");
        engineLabel = new JLabel("Thrust: ");

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

        panel.setBorder(BorderFactory.createTitledBorder("Sensor Data Display"));
        panel.add(airspeedLabel);
        panel.add(altitudeLabel);
        panel.add(pitchLabel);
        panel.add(rollLabel);
        panel.add(yawLabel);
        panel.add(engineLabel);
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void setUpdateFrequency(){

    }

    public static void updateAirspeed(double airspeed){
        
    }

    public static void updateAltitude(double altitude){
        
    }

    public static void updatePitch(double pitch){
        
    }
    public static void updateRoll(double roll){
        
    }

    public static void updateYaw(double yaw){
        
    }

    public static void updateThrust(double thrust){
        
    }

    public static void displayAirspeed(){
        
    }

    public static void displayAltitude(){
        
    }

    public static void displayPitch(){
        
    }
    public static void displayRoll(){
        
    }

    public static void displayYaw(){
        
    }

    public static void displayThrust(){
        
    }

    public static void displayIndicator(){
        
    }

    public static void colorChange(String color){

    }

    
}
