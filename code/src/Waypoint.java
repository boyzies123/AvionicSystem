package code.src;

/*
 * Waypoint class.
 * Represents an individual Waypoint object,
 * containing the waypoint's coordinates,
 * it's speed restrictions (if any),
 * and its estimated time of arrival (ETA).
 * 
 * Code made by: James McKenzie
 * Date created: 17/05/2024
 * Date modified: 27/05/2024
 */
public class Waypoint {
    
    // Instance variables for a waypoint
    private double xPos;
    private double yPos;
    private double speedRestriction;
    private double eta;
    private double altitude;
    
    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    public Waypoint(double x, double y, double speedRes, double eta, double altitude){
        this.xPos = x;
        this.yPos = y;
        this.speedRestriction = speedRes;
        this.eta = eta;
        this.altitude = altitude;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getSpeedRestriction() {
        return speedRestriction;
    }

    public void setSpeedRestriction(double speedRestriction) {
        this.speedRestriction = speedRestriction;
    }
    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    

}
