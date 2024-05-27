/*
 * Code made by: Harry Booth-Beach, Yi Chen
 * Date created: 20/05/2024
 * Date modified: 27/05/2024
 */
package code.sensor;

public class Engine extends Sensor {
    private double currThrust;
    private double fuelFlow;
    public Engine(double currentThrust, double fuelFlow){
        super(1000);
        this.currThrust = currentThrust;
        this.fuelFlow = fuelFlow;
    }
    /**
     * Getter method for fuel flow
     * @return
     */
    public double getFuelFlow(){
        return this.fuelFlow;
    }

    /**
     * Gets current thrust
     * @return
     */
    public double getCurrThrust(){
        return this.currThrust;
    }

    /**
     * Sets fuel flow
     * @param fuelFlow
     */
    public void setFuelFlow(double fuelFlow){
        this.fuelFlow = fuelFlow;
    }

    /**
     * Sets current thrust
     * @param thrust
     */
    public void setCurrThrust(double thrust){
        this.currThrust = thrust;
    }
}
