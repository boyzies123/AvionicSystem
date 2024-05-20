package code.src;

/*
 * Code made by: James McKenzie
 * Date created: 17/05/2024
 * Date modified: (if modified)
 */
public class Waypoint {
    
    private double xPos;
    private double yPos;
    private double speedRestriction;
    private double eta;

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    public Waypoint(double x, double y, double speedRes, double eta){
        this.xPos = x;
        this.yPos = y;
        this.speedRestriction = speedRes;
        this.eta = eta;
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

    

}
