package code.ui;

import javax.swing.*;
import java.awt.*;


/*
 * PilotUserInterface
 * This is the main frame that contains all of the individual components
 * of the system's user interface.
 * 
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 20/05/2024
 */
public class PilotUserInterface extends JFrame{

    
    // Visual Display Map
    //private JPanel mapPanel;
    
    public PilotUserInterface() {
        //start();
    }
    /**
    * Creates and intializes all necessary components
    */
    public void start() {
        setTitle("Aircraft Pilot Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components

        //initializeVisualDisplayMap();
        //this.mapPanel = new MapDisplay();

        FlightPlanManagement.initialize();
        AutopilotControlPanel.initialize();
        MapDisplay.initialize();
        HazardAlertsDisplay.initialize();
        SensorDataDisplay.initialize();
        
        // Add components to the frame
        add(FlightPlanManagement.getPanel(), BorderLayout.NORTH);
        add(AutopilotControlPanel.getPanel(), BorderLayout.WEST);
        add(MapDisplay.getPanel(), BorderLayout.CENTER);
        add(HazardAlertsDisplay.getPanel(), BorderLayout.SOUTH);
        add(SensorDataDisplay.getPanel(), BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }
    
    /**private void initializeVisualDisplayMap() {
        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Visual Display Map"));
        // Add map drawing functionality
    }

    /**public MapDisplay getMapDisplay(){
        return this.mapPanel;
    }**/
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PilotUserInterface::new);
    }
}
