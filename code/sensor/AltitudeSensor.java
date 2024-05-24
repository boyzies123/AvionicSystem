/*
 * Code made by: Harry Booth-Beach
 * Date created: 17/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public class AltitudeSensor extends Sensor{
    private long updateFreq;
    private double currAltitudeGPS;
    private double currAltitudeBarometric;
    private static double MIN_ALTITUDE = -1000;
    private static double MAX_ALTITUDE = 50000;
    public AltitudeSensor(double currAltitudeGPS, double currAltitudeBarometric) {
    	super(500);
    	this.currAltitudeBarometric = currAltitudeBarometric;
    	this.currAltitudeGPS = currAltitudeGPS;
    }
    /**
     * Sends the sensor data to the SensorData class
     * Value is GPS if isGPS is true, barometric otherwise
     * @param sD
     */
    public SensorData sendSensorData() {
        SensorData sensorData = new SensorData();
        sensorData.setAltitudeGPS(this.currAltitudeGPS);
        sensorData.setAltitudeBarometric(this.currAltitudeBarometric);
        return sensorData;
        
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
        return this.currAltitudeGPS;
    }

    /**
     * Getter method for average of the two altitudes
     * @return
     */
    public double getCombinedAltitude() {
        return (this.currAltitudeBarometric + this.currAltitudeGPS) / 2;
    }
    /**
     * Getter method for current altitude barometric
     * @return
     */
    public double getCurrAltitudeBarometric() {
        return this.currAltitudeBarometric;
    }
    
    /**
     * Getter method for maximum altitude
     * @return
     */
    public static double getMaxAltitude() {
        return MAX_ALTITUDE;
    }
    /**
     * Getter method for minimum altitude
     * @return
     */
    public static double getMinAltitude() {
        return MIN_ALTITUDE;
    }
    /* Getter method section END */
}
