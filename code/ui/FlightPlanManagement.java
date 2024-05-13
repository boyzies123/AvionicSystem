package code.ui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */
public class FlightPlanManagement extends JPanel{

    //private Waypoint[] waypoints;
    //private Map<Waypoint, Double> speedRestrictions;
    //private Waypoint[] waypoints;
    private JTextField waypointField;
    private JTextField speedField;
    private JTextField etaField;
    private JButton submitButton;
    private JButton setFlightPlanButton;

    public FlightPlanManagement(){
        super(new FlowLayout());
        waypointField = new JTextField(10);
        speedField = new JTextField(5);
        etaField = new JTextField(10);
        submitButton = new JButton("Submit");
        setFlightPlanButton = new JButton("Set Flight Plan");

        // Add action listeners for buttons
        // submitButton.addActionListener(e -> submitDetails());
        // setFlightPlanButton.addActionListener(e -> loadFlightPlan());

        setBorder(BorderFactory.createTitledBorder("Flight Plan Management"));
        add(new JLabel("Waypoint:"));
        add(waypointField);
        add(new JLabel("Speed:"));
        add(speedField);
        add(new JLabel("ETA:"));
        add(etaField);
        add(submitButton);
        add(setFlightPlanButton);
    }

    public void submitDetails(){

    }

    public void loadFlightPlan(){

    }
}
