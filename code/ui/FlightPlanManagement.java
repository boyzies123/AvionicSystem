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
 * Date modified: 22/05/2024
 */
public class FlightPlanManagement extends JPanel{

    private static List<Waypoint> waypoints;
    private Map<Waypoint, Double> speedRestrictions;
    //private Waypoint[] waypoints;
    private static JPanel panel;


    private static JTextField waypointXField;
    private static JTextField waypointYField;
    private static JTextField speedField;
    private static JTextField etaField;
    private static JButton submitButton;
    private static JButton setFlightPlanButton;
    private final static int MIN_WAYPOINTS = 2;
    private final static int MAX_WAYPOINTS = 8;
    private static int attempts = 0;

    public static void initialize(){
        panel = new JPanel(new FlowLayout());
        waypointXField = new JTextField(10);
        waypointYField = new JTextField(10);
        speedField = new JTextField(5);
        etaField = new JTextField(10);
        submitButton = new JButton("Submit");
        setFlightPlanButton = new JButton("Set Flight Plan");

        // Add action listeners for buttons
        submitButton.addActionListener(e -> submitDetails());
        setFlightPlanButton.addActionListener(e -> loadFlightPlan());

        panel.setBorder(BorderFactory.createTitledBorder("Flight Plan Management"));
        panel.add(new JLabel("Waypoint - X-position"));
        panel.add(waypointXField);
        panel.add(new JLabel("Waypoint - Y-position"));
        panel.add(waypointYField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("ETA:"));
        panel.add(etaField);
        panel.add(submitButton);
        panel.add(setFlightPlanButton);

        waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(MapDisplay.STARTX, MapDisplay.STARTY, 0, 0));
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void submitDetails(){
        if (attempts <= MAX_WAYPOINTS){
            double xPos = Double.parseDouble(waypointXField.getText());
            double yPos = Double.parseDouble(waypointYField.getText());
            double speedRes = Double.parseDouble(speedField.getText());
            double eta = Double.parseDouble(etaField.getText());
            Waypoint waypoint = new Waypoint(xPos, yPos, speedRes, eta);
            waypoints.add(waypoint);
            MapDisplay.setWaypoints(waypoints);
            MapDisplay.displayWaypoints();

            waypointXField.setText("");
            waypointYField.setText("");
            speedField.setText("");
            etaField.setText("");
        }
        attempts++;
    }

    public static void loadFlightPlan(){
        if (waypoints.size() >= MIN_WAYPOINTS){
            //parentFrame.getMapDisplay().setWaypoints(waypoints);
            submitButton.setEnabled(false);
            setFlightPlanButton.setEnabled(false);
        }

    }
}
