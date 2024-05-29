/*
 * Code made by: Harry Booth-Beach, Yi Chen
 * Date created: 20/05/2024
 * Date modified: 27/05/2024
 */

package code.sensor;

/**.
 * Class of engine, containing information about engine parameters
 */
public class Engine extends Sensor {
  private double currThrust;
  private double fuelFlow;
  /**.
   * Constructor of engine which takes in the thrust and fuel
   * flow of engine
   */
  
  public Engine(double currentThrust, double fuelFlow) {
    super(1000);
    this.currThrust = currentThrust;
    this.fuelFlow = fuelFlow;
  }

  /**.
   * Getter method for fuel flow
   *
   * @return fuelFlow the current fuelFlow
   */
  
  public double getFuelFlow() {
    return this.fuelFlow;
  }
  /**.
   * Getter method for current thrust
   *
   * @return currThrust the current thrust
   */
  
  public double getCurrThrust() {
    return this.currThrust;
  }
  /**.
   * Setter method for current fuel flow
   *
   * @param fuelFlow the fuel flow to be set
   */
  
  public void setFuelFlow(double fuelFlow) {
    this.fuelFlow = fuelFlow;
  }
  /**.
   * Setter method for current thrust
   *
   * @param thrust the thrust to be set
   */
  
  public void setCurrThrust(double thrust) {
    this.currThrust = thrust;
  }
}
