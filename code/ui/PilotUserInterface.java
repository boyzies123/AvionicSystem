package code.ui;

import javax.swing.*;
import java.awt.*;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 20/05/2024
 */
public class PilotUserInterface extends JFrame{

    
    // Visual Display Map
    private JPanel mapPanel;
    
    public PilotUserInterface() {
        setTitle("Aircraft Pilot Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components

        //initializeVisualDisplayMap();
        //this.mapPanel = new MapDisplay();
        
        // Add components to the frame
        add(new FlightPlanManagement(this), BorderLayout.NORTH);
        add(new AutopilotControlPanel(), BorderLayout.WEST);
        add(new MapDisplay(), BorderLayout.CENTER);
        add(new HazardAlertsDisplay(), BorderLayout.SOUTH);
        add(new SensorDataDisplay(), BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }

    
    private void initializeVisualDisplayMap() {
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
