package code.ui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.src.Waypoint;

/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */
public class FlightPlanManagement extends JPanel{

    private List<Waypoint> waypoints;
    private Map<Waypoint, Double> speedRestrictions;
    //private Waypoint[] waypoints;
    private JTextField waypointXField;
    private JTextField waypointYField;
    private JTextField speedField;
    private JTextField etaField;
    private JButton submitButton;
    private JButton setFlightPlanButton;

    public FlightPlanManagement(){
        super(new FlowLayout());
        waypointXField = new JTextField(10);
        waypointYField = new JTextField(10);
        speedField = new JTextField(5);
        etaField = new JTextField(10);
        submitButton = new JButton("Submit");
        setFlightPlanButton = new JButton("Set Flight Plan");

        // Add action listeners for buttons
        submitButton.addActionListener(e -> submitDetails());
        // setFlightPlanButton.addActionListener(e -> loadFlightPlan());

        setBorder(BorderFactory.createTitledBorder("Flight Plan Management"));
        add(new JLabel("Waypoint - X-position"));
        add(waypointXField);
        add(new JLabel("Waypoint - Y-position"));
        add(waypointYField);
        add(new JLabel("Speed:"));
        add(speedField);
        add(new JLabel("ETA:"));
        add(etaField);
        add(submitButton);
        add(setFlightPlanButton);

        waypoints = new ArrayList<Waypoint>();
    }

    public void submitDetails(){
        double xPos = Double.parseDouble(waypointXField.getText());
        double yPos = Double.parseDouble(waypointYField.getText());
        double speedRes = Double.parseDouble(speedField.getText());
        double eta = Double.parseDouble(etaField.getText());
        Waypoint waypoint = new Waypoint(xPos, yPos, speedRes, eta);
        waypoints.add(waypoint);
    }

    public void loadFlightPlan(){

    }
}
