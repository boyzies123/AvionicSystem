package ui;
import javax.swing.*;

import sensor.Sensor;

import java.awt.*;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */
public class PilotUserInterface extends JFrame{

    private AutopilotControlPanel autopilotControlPanel = new AutopilotControlPanel();
    private FlightPlanManagement flightPlanManagement = new FlightPlanManagement();
    private SensorDataDisplay sensorDataDisplay = new SensorDataDisplay();
    private HazardAlertsDisplay hazardAlertsDisplay = new HazardAlertsDisplay();
    // Visual Display Map
    private JPanel mapPanel;
    
    public PilotUserInterface() {
    	
    }
    /**.
     * Creates and initializes all necessary components
     * 
     */
    public void start() {
    	setTitle("Aircraft Pilot Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components

        initializeVisualDisplayMap();
        // Add components to the frame
        add(flightPlanManagement, BorderLayout.NORTH);
        add(autopilotControlPanel, BorderLayout.WEST);
        add(sensorDataDisplay, BorderLayout.CENTER);
        add(hazardAlertsDisplay, BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }
    
    private void initializeVisualDisplayMap() {
        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Visual Display Map"));
        // Add map drawing functionality
    }
    public AutopilotControlPanel getAutopilotControlPanel(){
        return autopilotControlPanel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PilotUserInterface::new);
    }
    
}