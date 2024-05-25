package code.sensor;
/*
 * Code made by: Harry Booth-Beach, with modifications by Yi Chen
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */

public class AirspeedSensor extends Sensor{
    private double currentAirspeed;
    private static double MIN_SPEED = 50;
    private static double MAX_SPEED = 500;
    public AirspeedSensor() {
        /**
         * Creates airspeed sensor object
         */
    	super(1000);
        this.currentAirspeed = MIN_SPEED;
    }
    /**
     * Gets the current airspeed
     * @return the current airspeed
     */
    public double getCurrentAirspeed(){
        return this.currentAirspeed;
    }
    /**
     * Gets the maximum airspeed
     * @return the maximum airspeed allowed
     */
    public static double getMaxSpeed(){
        return MAX_SPEED;
    }
    /**
     * Gets the minimum airspeed
     * @return the minimum airspeed allowed
     */
    public static double getMinSpeed(){
        return MIN_SPEED;
    }
    /**
     * Sets current airspeed
     * @param currentAirspeed the current airspeed
     */
    public void setCurrentAirspeed(double currentAirspeed){
        this.currentAirspeed = currentAirspeed;
    }
    /**
     * Sends the sensor data based on attributes of airspeed sesnsor
     * @return sensordata Sensor data produced by sensor
     */
    public SensorData sendSensorData(){
        SensorData s = new SensorData();
        s.setAirspeed(this.currentAirspeed);
        return s;
    }
}
