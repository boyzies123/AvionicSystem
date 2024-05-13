/*
 * Code made by: Harry Booth-Beach
 * Date created: 13/05/2024
 * Date modified: 13/05/2024
 */
package code.sensor;

public class SensorData {
    private double altitude;
    private double pitch;
    private double roll;
    private double yaw;
    private double airSpeed;
    private double engineParams;

    /* Setter method section START */

    /**
     * Setter method for altitude
     * @param altitude
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Setter method for pitch
     * @param pitch
     */
    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    /**
     * Setter method for roll
     * @param roll
     */
    public void setRoll(double roll) {
        this.roll = roll;
    }

    /**
     * Setter method for yaw
     * @param yaw
     */
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    /**
     * Setter method for air speed
     * @param airSpeed
     */
    public void setAirSpeed(double airSpeed) {
        this.airSpeed = airSpeed;
    }

    /**
     * Setter method for engine parameters
     * @param engineParams
     */
    public void setEngineParams(double engineParams) {
        this.engineParams = engineParams;
    }

    /* Setter method section END */

    /* Getter method section START */

    /**
     * Getter method for alutitude
     * @return
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Getter method for pitch
     * @return
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Getter method for yaw
     * @return
     */
    public double getYaw() {
        return yaw;
    }

    /**
     * Getter method for roll
     * @return
     */
    public double getRoll() {
        return roll;
    }

    /**
     * Getter method for air speed
     * @return
     */
    public double getAirSpeed() {
        return airSpeed;
    }

    /**
     * Getter method for engine parameters (thrust)
     * @return
     */
    public double getEngineParams() {
        return engineParams;
    }
    
    /* Getter method section END */
}
