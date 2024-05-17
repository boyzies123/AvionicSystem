/*
 * Code made by: Harry Booth-Beach
 * Date created: 17/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public class AirspeedSensor {
    private int updateFreq;
    private double currAirspeed;
    private double MAX_SPEED;
    private double MIN_SPEED;
    private double airspeedSensorID;

    /**
     * Sends data to SensorData class
     * @param sD
     */
    public void sendSensorData(SensorData sD) {
        sD.setAirspeed(currAirspeed);
    }

    /**
     * Returns true if airspeed is less or equal to max
     * and more than or equal to min
     * @return
     */
    public boolean checkAirspeed() {
        return this.airspeed <= MAX_SPEED && this.airspeed >= MIN_SPEED;
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


}