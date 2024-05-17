/*
 * Code made by: Harry Booth-Beach, with modifications by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public class Engine {
    private int updateFreq;
    private double currThrust;
    private double MAX_THRUST;
    private double MIN_THRUST;
    private double fuelFlow;
    private int engineID;
    public Engine(double currentThrust, double fuelFlow){
        currThrust = currentThrust;
        this.fuelFlow = fuelFlow;
    }
    /**
     * Sends the sensor data to the SensorData class
     * @param sD
     */
    public void sendSensorData(SensorData sD) {
        sD.setEngineParams(currThrust);
    }

    public boolean checkThrust() {
        return currThrust <= MAX_THRUST && currThrust >= MIN_THRUST;
    }

    /* Setter method section START */

    /**
     * Setter method for fuel flow
     * @param fuelFlow
     */
    public void setFuelFlow(double fuelFlow) {
        this.fuelFlow = fuelFlow;
    }

    /**
     * Setter method for current thrust
     * @param currThrust
     */
    public void setCurrThrust(double currThrust) {
        this.currThrust = currThrust;
    }

    /* Setter method section END */

    /* Getter method section START */

    /**
     * Getter method for current thrust
     * @return
     */
    public double getCurrThrust() {
        return currThrust;
    }

    /**
     * Getter method for fuel flow
     * @return
     */
    public double getFuelFlow() {
        return fuelFlow;
    }

    /* Getter methods section END */
}
