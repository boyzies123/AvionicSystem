package code.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import code.src.Waypoint;

/*
 * Code made by: James McKenzie
 * Date created: 22/05/2024
 * Date modified: 22/05/2024
 */
public class MapPanel extends JPanel{
    private int currentX;
    private int currentY;
    private List<Waypoint> waypoints;
    private BufferedImage mapImage;

    public MapPanel(int x, int y, List<Waypoint> w) {
        // Initialize variables
        currentX = x;
        currentY = y;
        waypoints = w;
        try {                
            mapImage = ImageIO.read(new File("\\C:\\Users\\jjmck\\avionics-system\\code\\ui\\mapImage.PNG\""));
         } catch (IOException ex) {
              System.err.println("File reading failed");
         }

    }

    public void setCurrentPosition(int x, int y) {
        currentX = x;
        currentY = y;
        repaint(); // Redraw the panel after updating position
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
        System.out.println(waypoints.size());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw map image
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw current position
        g.setColor(Color.RED);
        g.fillOval(currentX - 5, currentY - 5, 10, 10);

        // Draw planned route (if waypoints are added)
        if (waypoints.size() > 1) {
            g.setColor(Color.RED);
            for (int i = 0; i < waypoints.size() - 1; i++) {
                g.drawLine((int)waypoints.get(i).getxPos(), (int)waypoints.get(i).getyPos(), (int)waypoints.get(i+1).getxPos(), (int)waypoints.get(i+1).getyPos());
            }
        }
        
    }
}
