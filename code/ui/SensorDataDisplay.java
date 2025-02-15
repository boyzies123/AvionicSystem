package code.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * SensorDataDisplay class
 * Contains all of the UI components that will display incoming sensor
 * data to the pilot.
 * 
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 27/05/2024
 */
public class SensorDataDisplay{

    /**private double airspeed;
    private double altitude;
    private double pitch;
    private double roll;
    private double yaw;
    private double thrust;**/

    // Fields
    private static JPanel panel;
    private static JLabel airspeedLabel;
    private static JLabel altitudeLabel;
    private static JLabel pitchLabel;
    private static JLabel rollLabel;
    private static JLabel yawLabel;
    private static JLabel engineLabel1;
    private static JLabel engineLabel2;
    private static JLabel fuelLabel1;
    private static JLabel fuelLabel2;

    /**
     * Create and initialize all components of the panel.
     */
    public static void initialize(){
        panel = new JPanel(new GridLayout(9,1));
        airspeedLabel = new JLabel("Airspeed: ");
        altitudeLabel = new JLabel("Altitude: ");
        pitchLabel = new JLabel("Pitch: ");
        rollLabel = new JLabel("Roll: ");
        yawLabel = new JLabel("Yaw: ");
        engineLabel1 = new JLabel("Engine 1 Thrust: ");
        engineLabel2 = new JLabel("Engine 2 Thrust: ");
        fuelLabel1 = new JLabel("Engine 1 Fuel Flow: ");
        fuelLabel2 = new JLabel("Engine 2 Fuel Flow: ");

        panel.setBorder(BorderFactory.createTitledBorder("Sensor Data Display"));
        panel.add(airspeedLabel);
        panel.add(altitudeLabel);
        panel.add(pitchLabel);
        panel.add(rollLabel);
        panel.add(yawLabel);
        panel.add(engineLabel1);
        panel.add(engineLabel2);
        panel.add(fuelLabel1);
        panel.add(fuelLabel2);
    }

    /**
     * Allows other classes to access the JPanel itself,
     * and therefore display the data on the panel.
     * @return The panel
     */
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

    public static void updateThrust1(double thrust){
        
    }

    public static void updateThrust2(double thrust){
        
    }

    public static void updateFuel1(double fuelFlow){
        
    }

    public static void updateFuel2(double fuelFlow){
        
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
