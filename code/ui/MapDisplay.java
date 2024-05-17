package code.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import code.src.Waypoint;


/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: (if modified)
 * 
 * MAP SHIT GOES HERE
 */
public class MapDisplay extends JPanel{
    // Coordinates for the current position
    private int currentX;
    private int currentY;
    private List<Waypoint> waypoints;
    private Image mapImage;

    public MapDisplay() {
        // Initialize variables
        currentX = 0;
        currentY = 0;
    }

    public void setCurrentPosition(int x, int y) {
        currentX = x;
        currentY = y;
        repaint(); // Redraw the panel after updating position
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw map image
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }

        
    }

    
}
