/*
 * Code made by: Harry Booth-Beach
 * Date created: 17/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public class AltitudeSensor {
    private int updateFreq;
    private boolean isGPS;
    private boolean isBarometric;
    private double currAltitudeGPS;
    private double currAltitudeBarometric;
    private double MAX_ALTITUDE_GPS;
    private double MIN_ALTITUDE_GPS;
    private double MAX_ALTITUDE_BAROMETRIC;
    private double MIN_ALTITUDE_BAROMETRIC;
    private double altitudeSensorID;

    /**
     * Returns true if altitude being measured in GPS
     * @return
     */
    public boolean isGPS() {
        return isGPS;
    }

    /**
     * Returns true if altitude is being measured in barometric
     * @return
     */
    public boolean isBarometric() {
        return isBarometric;
    }

    /**
     * Sets altitude measurement to GPS
     */
    public void setModeGPS() {
        isGPS = true;
        isBarometric = false;
    }

    /**
     * Sets altitude mode to Barometric
     */
    public void setModeBarometric() {
        isGPS = false;
        isBarometric = true;
    }

    /**
     * Sends the sensor data to the SensorData class
     * Value is GPS if isGPS is true, barometric otherwise
     * @param sD
     */
    public void sendSensorData(SensorData sD) {
        if (this.isGPS()) {
            sD.setAltitude(currAltitudeGPS);
        }
        sD.setAltitude(currAltitudeBarometric);
    }

    /**
     * Returns true if altitude is less or equal to max
     * and more than or equal to min
     * @return
     */
    public boolean checkAltitudeGPS() {
        return this.currAltitudeGPS <= MAX_ALTITUDE_GPS && this.currAltitudeGPS >= MIN_ALTITUDE_GPS;
    }

    /**
     * Returns true if altitude is less or equal to max
     * and more than or equal to min
     * @return
     */
    public boolean checkAltitudeBarometric() {
        return this.currAltitudeBarometric <= MAX_ALTITUDE_BAROMETRIC && this.currAltitudeBarometric >= MIN_ALTITUDE_BAROMETRIC;
    }

    /* Setter method section START */

    /**
     * Setter method for current altitude GPS
     * @param altitude
     */
    public void setCurrAltitudeGPS(double altitude) {
        this.currAltitudeGPS = altitude;
    }

    /**
     * Setter method for current altitude barometric
     * @param altitude
     */
    public void setCurrAltitudeBarometric(double altitude) {
        this.currAltitudeBarometric = altitude;
    }

    /* Setter method section END */

    /* Getter method section START */

    /**
     * Getter method for current altitude GPS
     * @return
     */
    public double getCurrAltitudeGPS() {
        return currAltitudeGPS;
    }

    /**
     * Getter method for current altitude barometric
     * @return
     */
    public double getCurrAltitudeBarometric() {
        return currAltitudeBarometric;
    }

    /* Getter method section END */
}