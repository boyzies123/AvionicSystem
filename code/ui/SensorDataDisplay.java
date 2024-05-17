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

    private JLabel airspeedLabel;
    private JLabel altitudeLabel;
    private JLabel pitchLabel;
    private JLabel rollLabel;
    private JLabel yawLabel;
    private JLabel engineLabel;

    public SensorDataDisplay(){
        super(new GridLayout(6,1));
        airspeedLabel = new JLabel("Airspeed: ");
        altitudeLabel = new JLabel("Altitude: ");
        pitchLabel = new JLabel("Pitch: ");
        rollLabel = new JLabel("Roll: ");
        yawLabel = new JLabel("Yaw: ");
        engineLabel = new JLabel("Thrust: ");

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

        setBorder(BorderFactory.createTitledBorder("Sensor Data Display"));
        add(airspeedLabel);
        add(altitudeLabel);
        add(pitchLabel);
        add(rollLabel);
        add(yawLabel);
        add(engineLabel);
    }

    public void setUpdateFrequency(){

    }

    public void updateAirspeed(double airspeed){
        
    }

    public void updateAltitude(double altitude){
        
    }

    public void updatePitch(double pitch){
        
    }
    public void updateRoll(double roll){
        
    }

    public void updateYaw(double yaw){
        
    }

    public void updateThrust(double thrust){
        
    }

    public void displayAirspeed(){
        
    }

    public void displayAltitude(){
        
    }

    public void displayPitch(){
        
    }
    public void displayRoll(){
        
    }

    public void displayYaw(){
        
    }

    public void displayThrust(){
        
    }

    public void displayIndicator(){
        
    }

    public void colorChange(String color){

    }

    
}
