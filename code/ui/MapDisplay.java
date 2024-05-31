package code.ui;

import java.util.ArrayList;
import java.util.List;

import code.src.Waypoint;


/*
 * MapDisplay class.
 * "Wrapper" class that contains a special JPanel type that will graphically represent
 * the flight plan entered by the pilot.
 * 
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 22/05/2024
 */
public class MapDisplay{
    
    // The JPanel containing the actual map display itself.
    private static MapPanel panel;

    public static final int STARTX = 100;
    public static final int STARTY = 100;

    // Coordinates for the current position
    private static int currentX;
    private static int currentY;

    private static List<Waypoint> waypoints;

    /**
     * Initialize the fields and create the Map Panel.
     */
    public static void initialize() {
        // Initialize variables
        currentX = STARTX;
        currentY = STARTY;
        waypoints = new ArrayList<Waypoint>();
        panel = new MapPanel(currentX, currentY, waypoints);

    }

    /**
     * Allows other classes to access the Map Panel itself,
     * and therefore make changes to the appearance of the 
     * map.
     * @return The map
     */
    public static MapPanel getPanel() {
        return panel;
    }

    /**
     * For debugging purposes
     */
    public static void displayWaypoints(){
        if (waypoints.size() != 0){
            System.out.println(waypoints);
        }
        else{
            System.out.println("No waypoints");
        }
    }

    /**
     * Set the plane's current position on the map
     * @param x
     * @param y
     */
    public static void setCurrentPosition(int x, int y) {
        currentX = x;
        currentY = y;
        panel.setCurrentPosition(x, y);
    }

    /**
     * Update the current list of waypoints.
     * @param w
     */
    public static void setWaypoints(List<Waypoint> w) {
        waypoints = w;
        panel.setWaypoints(w);
    }

    
}
