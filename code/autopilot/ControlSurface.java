package code.autopilot;

import java.util.ArrayList;
import java.util.List;
import code.sensor.AttitudeSensor;
import code.sensor.Sensor;
import code.sensor.SensorData;


/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 20/05/2024
 */
/**.
 * The control surface class which helps 
 * manage elevator, aileron, and rudder positions.
 */
public class ControlSurface {
    
  private double elevatorPosition = 0;
  private double aileronPosition = 0;
  private double rudderPosition = 0;
  private Sensor[] sensor = new Sensor[3];
  private List<SensorData> sensorData = new ArrayList<>();
   

  /**.
   * Creates an control surface object
   *
   * @param attitudeSensor1 The first attitude sensor
   * 
   * @param attitudeSensor2 The second attitude sensor
   * 
   * @param attitudeSensor3 The third attitude sensor
   * 
   */
  public ControlSurface(Sensor attitudeSensor1, Sensor attitudeSensor2, Sensor attitudeSensor3) {
    // 2oo3 architecture for sensors.
    this.sensor[0] = attitudeSensor1;
    this.sensor[1] = attitudeSensor2;
    this.sensor[2] = attitudeSensor3;
  }
  /**.
   * Execute control signal sent from autopilot control
   *
   * @param signal The control signal that shall be executed
   * 
   */
  
  public void executeControlSignal(ControlSignal signal) {
    this.elevatorPosition = this.elevatorPosition + signal.getElevatorPosition();
    this.aileronPosition = this.aileronPosition + signal.getAileronPosition();
    this.rudderPosition = this.rudderPosition + signal.getRudderPosition();
    for (int i = 0; i < this.sensor.length; i++) {
      ((AttitudeSensor) this.sensor[i]).setPitch(this.elevatorPosition);
    }
    for (int i = 0; i < this.sensor.length; i++) {
      ((AttitudeSensor) this.sensor[i]).setRoll(this.aileronPosition);
    }
    for (int i = 0; i < this.sensor.length; i++) {
      ((AttitudeSensor) this.sensor[i]).setYaw(this.rudderPosition);
    }
  }
    
  /**.
   * Obtain the sensor data from the sensors
   */
  public void updateFromSensorData() {
    for (int i = 0; i < this.sensor.length; i++) {
      this.sensorData.add(((AttitudeSensor) this.sensor[i]).sendSensorData());
    }
  }
    


  /**.
   * Since sensors operate in 2oo3 architecture, sensor data shall be checked.
   *
   * @return sensor data of majority vote
   * 
   * @throws IllegalStateException if tie breaker
   */
  public SensorData getMajorityVote() {
    SensorData sensorData1 = this.sensorData.get(0);
    SensorData sensorData2 = this.sensorData.get(1);
    SensorData sensorData3 = this.sensorData.get(2);
    if (sensorData1.equals(sensorData2) || sensorData1.equals(sensorData3)) {
      return sensorData1;
    }
    if (sensorData2.equals(sensorData3)) {
      return sensorData2;
    }
        
    throw new IllegalStateException("No majority agreement among the sensors"); //$NON-NLS-1$
    
  }
    
  /**.
   * Send sensor data to autopilot system to verify execution of control signals
   */
  public SensorData sendSensorData() {
    try {
      updateFromSensorData();
      SensorData sd = getMajorityVote();
      return sd;

    } catch (IllegalStateException e) {
      e.printStackTrace();
      return new SensorData(-9999, -9999, -9999);
    }
  }
    
  /**.
   * This method is for testing purpose. Incorrect sensor data will be passed to
   * simulate incorrect behavior, ensuring that requirements are met.
   */
  public SensorData sendSensorData(double yaw, double roll, double pitch) {
    SensorData sd = ((AttitudeSensor) this.sensor[0]).sendSensorData();
    sd.setRoll(roll);
    sd.setYaw(yaw);
    sd.setPitch(pitch);
    return sd;
  }
    

  /**.
   * Gets current aileron position
   *
   * @return the current aileron position
   * 
   */
  public double getAileronPosition() {
    return this.aileronPosition;
  }
    
  /**.
   * Gets current elevator position
   *
   * @return the current elevator position
   * 
   */
  public double getElevatorPosition() {
    return this.elevatorPosition;
  }
    

  /**.
   * Gets current rudder position
   *
   * @return the current rudder position
   * 
   */
  public double getRudderPosition() {
    return this.rudderPosition;
  }
}
