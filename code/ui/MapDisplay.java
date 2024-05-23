package code.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import code.src.Waypoint;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 22/05/2024
 */
public class MapDisplay{
    // Coordinates for the current position
    private static MapPanel panel;

    public static final int STARTX = 100;
    public static final int STARTY = 100;

    private static int currentX;
    private static int currentY;
    private static List<Waypoint> waypoints;

    public static void initialize() {
        // Initialize variables
        currentX = STARTX;
        currentY = STARTY;
        waypoints = new ArrayList<Waypoint>();
        panel = new MapPanel(currentX, currentY, waypoints);

    }

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

    public static void setCurrentPosition(int x, int y) {
        currentX = x;
        currentY = y;
        panel.setCurrentPosition(x, y);
    }

    public static void setWaypoints(List<Waypoint> w) {
        waypoints = w;
        panel.setWaypoints(w);
    }

    
}
