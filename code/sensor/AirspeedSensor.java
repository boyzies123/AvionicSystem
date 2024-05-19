/*
 * Code made by: Harry Booth-Beach, with modifications by Yi Chen
 * Date created: 17/05/2024
 * Date modified: 19/05/2024
 */
package code.sensor;

public class AirspeedSensor {
    private int updateFreq;
    private double currAirspeed;
    private static double MIN_SPEED = 50;
    private static double MAX_SPEED = 500;
    private double airspeedSensorID;

    /**
     * Sends data to SensorData class
     * @param sD
     */
    public void sendSensorData(SensorData sD) {
        sD.setAirspeed(currAirspeed);
    }

   

    /**
     * Setter method for current airspeed
     * @param speed
     */
    public void setCurrAirspeed(double speed) {
        this.currAirspeed = speed;
    }

    /**
     * Getter method for current airspeed
     * @return
     */
    public double getCurrAirspeed() {
        return currAirspeed;
    }
    /**
     * Getter method for the max speed 
     * @return
     */
    public static double getMaxSpeed(){
        return MAX_SPEED;
    }
    /**
     * Getter method for the min speed
     * @return
     */
    public static double getMinSpeed(){
        return MIN_SPEED;
    }


}