package code.autopilot;
/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 23/05/2024
 */
/**.
 * The control signal class which contains
 * information that autopilot will send to the 
 * control surfaces and engine control systems.
 */
public class ControlSignal {
  private double newElevatorPosition;
  private double newAileronPosition;
  private double newRudderPosition;
  private double newThrustOnePosition;
  private double newFuelFlowOne;
  private double newThrustTwoPosition;
  private double newFuelFlowTwo;
  /**.
   * Control signal constructor consisting of 
   * the adjustment of aileron, rudder and elevator
   */
  
  public ControlSignal(double newAileronPos, double newRudderPos, double newElevatorPos) {
    this.newAileronPosition = newAileronPos;
    this.newRudderPosition = newRudderPos;
    this.newElevatorPosition = newElevatorPos;
  }
  /**.
   * Control signal constructor consisting of 
   * the adjustment of thrust and fuel flow
   */
  
  public ControlSignal(double newThrOne, double newFuFlOne, double newThrTwo, double newFuFlTwo) {
    this.newThrustOnePosition = newThrOne; 
    this.newFuelFlowOne = newFuFlOne;
    this.newThrustTwoPosition = newThrTwo;
    this.newFuelFlowTwo = newFuFlTwo;

  }

  /**.
   * Gets how much elevator should be adjusted by
   *
   * @return adjustment value of elevator
   * 
   */
  public double getElevatorPosition() {
    return this.newElevatorPosition;
  }

  /**.
   * Gets how much aileron should be adjusted by
   *
   * @return adjustment value of aileron
   * 
   */
  public double getAileronPosition() {
    return this.newAileronPosition;
  }

  /**.
   * Gets how much rudder should be adjusted by
   *
   * @return adjustment value of rudder
   * 
   */
  public double getRudderPosition() {
    return this.newRudderPosition;
  }

  /**.
   * Gets how much thrust should be adjusted by for the first engine
   *
   * @return adjustment value of thrust for engine one
   * 
   */
  public double getThrustOnePosition() {
    return this.newThrustOnePosition;
  }

  /**.
   * Gets how much fuel flow should be adjusted by for the first engine
   *
   * @return adjustment value of fuel flow for engine one
   * 
   */
  public double getFuelFlowOne() {
    return this.newFuelFlowOne;
  }

  /**.
   * Gets how much thrust should be adjusted by for the second engine
   *
   * @return adjustment value of thrust for engine two
   * 
   */
  public double getThrustTwoPosition() {
    return this.newThrustTwoPosition;
  }

  /**.
   * how much fuel flow should be adjusted by for the second engine
   *
   * @return adjustment value of fuel flow for engine two
   * 
   */
  
  public double getFuelFlowTwo() {
    return this.newFuelFlowTwo;
  }
}
