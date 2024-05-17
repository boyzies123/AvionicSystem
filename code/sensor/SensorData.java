/*
 * Code made by: Harry Booth-Beach, with modifications by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public class SensorData {
    private double altitudeGPS;
    private double altitudeBarometric;
    private double pitch;
    private double roll;
    private double yaw;
    private double fuelFlow;
    private double thrust;
    /**
     * SensorData constructor for sensor data produced by attitude sensor
     */
    public SensorData(double pitch, double roll, double yaw){
        this.roll = roll;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    /**
     * SensorData constructor for sensor data for engine parameters
     */
    public SensorData(double fuelFlow, double thrust){
        this.fuelFlow = fuelFlow;
        this.thrust = thrust;
    }
    /* Setter method section START */

    /**
     * Setter method for altitude GPS
     * @param altitudeGPS
     */
    public void setAltitudeGPS(double altitudeGPS) {
        this.altitudeGPS = altitudeGPS;
    }

    /**
     * Setter method for altitude barometric
     * @param altitudeBarometric
     */
    public void setAltitudeBarometric(double altitudeBarometric) {
        this.altitudeBarometric = altitudeBarometric;
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
     * @param airspeed
     */
    public void setAirspeed(double airspeed) {
        this.airspeed = airspeed;
    }

    /**
     * Setter method for fuelflow
     * @param fuelFlow
     */
    public void setfuelFlow(double fuelFlow) {
        this.fuelFlow = fuelFlow;
    }
    /**
     * Setter method for fuelflow
     * @param thrust
     */
    public void setThrust(double thrust) {
        this.thrust = thrust;
    }

    /* Setter method section END */

    /* Getter method section START */

    /**
     * Getter method for alutitude GPS
     * @return
     */
    public double getAltitudeGPS() {
        return altitudeGPS;
    }

    /**
     * Getter method for alutitude GPS
     * @return
     */
    public double getAltitudeBarometric() {
        return altitudeBarometric;
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
    public double getAirspeed() {
        return airspeed;
    }

    /**
     * Getter method for thrust
     * @return
     */
    public double getThrust() {
        return thrust;
    }
    /**
     * Getter method for fuel flow
     * @return
     */
    public double getFuelFlow() {
        return fuelFlow;
    }

    /* Getter method section END */
}
