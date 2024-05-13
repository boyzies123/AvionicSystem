package code.ui;

import javax.swing.*;
import java.awt.*;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */
public class PilotUserInterface extends JFrame{

    
    // Visual Display Map
    private JPanel mapPanel;
    
    public PilotUserInterface() {
        setTitle("Aircraft Pilot Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components

        initializeVisualDisplayMap();
        
        // Add components to the frame
        add(new FlightPlanManagement(), BorderLayout.NORTH);
        add(new AutopilotControlPanel(), BorderLayout.WEST);
        add(new SensorDataDisplay(), BorderLayout.CENTER);
        add(new HazardAlertsDisplay(), BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }

    
    private void initializeVisualDisplayMap() {
        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Visual Display Map"));
        // Add map drawing functionality
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PilotUserInterface::new);
    }
}
