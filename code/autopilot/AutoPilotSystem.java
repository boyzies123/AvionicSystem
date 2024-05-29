package code.autopilot;

import code.sensor.SensorData;
import code.src.Waypoint;
import code.ui.FlightPlanManagement;
import code.ui.HazardAlertsDisplay;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 21/05/2024
 */
/**.
 * The autopilot system class, responsible
 * for calculating values to be adjusted by 
 * for the control surfaces and engine control system
 */

public class AutoPilotSystem {
  private ScheduledExecutorService executor;
  private ScheduledExecutorService executor2;
  //Control surface 
  private ControlSurface conSurf;
  //Engine control system
  private EngineControlSystem enConSys;
  private final long coFq = 500; // control frequency in milliseconds
  private final double moeControlSurfaces = 2;
  private final double moeEngineParameters = 5;
  private final int maxRetries = 3;
  //whether autopilot is enaged or not
  private boolean engage = false;
  //Whether the current autopilot is the main one running
  private boolean active;
  private boolean errorInAutoPilot = false;
  private int retries = 0;
  //counts number of times checkExecution has been called.
  private int count = 0;
  private double altitude = 0;
  private List<Waypoint> waypoints = FlightPlanManagement.getWaypoints();
  private double currentLatitude = 0; //y
  private double currentLongitude = 0; //x
  //For testing purposes to simulate incorrect data
  private boolean simulation = false;
  private boolean alertSent = false;
  /**
   * Creates an AutoPilotSystem object with a given control surface.
   *
   * @param coSurf The control surface of this aircraft where autopilot will send 
   control signals to.
   * @param enCoSys The engine control system of this aircraft where autopilot will send 
   control signals to.
   * @param a Whether this autopilotsystem is active or nor
   * @param s Whether currently in simulation mode
   */
  
  public AutoPilotSystem(ControlSurface coSurf, EngineControlSystem enCoSys, boolean a, boolean s) {
    this.active = a;
    this.conSurf = coSurf;
    this.enConSys = enCoSys;
    this.executor = Executors.newSingleThreadScheduledExecutor();
    this.simulation = s;
    TimeUnit time = TimeUnit.MILLISECONDS;
    if (!this.simulation) {
      this.executor.scheduleAtFixedRate(this::sendControlSignal, 0, this.coFq, time);
    }

  }
  /**
   * Starts the autopilot system.
   */
  
  public void start() {
    this.engage = true;
  }
  /**
   * Stops the autopilot system.
   */
  
  public void stop() {
    this.engage = false;
  }
  /**
   * Sets whether autopilot is active or not. For redundancy purposes.
   *
   * @param status the status of autopilot to be set
   */
  
  public void setStatus(boolean status) {
    this.active = status;
  }
  /**.
   * Send control signals to the aircraft’s control
   * surfaces (elevators, ailerons, rudders) and engine control systems
   */
  
  public void sendControlSignal() {
    //Not engaged. Do not send control signals
    if (!this.engage || !this.active) {
      return;
    }

    //Calculate control signal values based on simulated attitude data
    SensorData currentOrientation = this.conSurf.sendSensorData();
    //Calculate the desired adjustments
    double pitch = calculateDesiredPitch();
    double roll = calculateDesiredRoll();
    double yaw = calculateDesiredYaw(currentOrientation.getYaw());
    double thrustOne = calculateThrust();
    double ffOne = calculateFuelFlow();
    double thrustTwo = calculateThrust();
    double ffTwo = calculateFuelFlow();
    this.waypoints.remove(0);
    //Original values of control surfaces. For health detection checks
    double originalAileronPos = this.conSurf.getAileronPosition();
    double originalElevatorPos = this.conSurf.getElevatorPosition();
    double originalRudderPos = this.conSurf.getRudderPosition();
    //Currently temp values for control signal values
    ControlSignal csToControlSurface = new ControlSignal(roll, pitch, yaw);
    ControlSignal csToEngineControlSystem = new ControlSignal(thrustOne, ffOne, thrustTwo, ffTwo);
    this.conSurf.executeControlSignal(csToControlSurface);
    this.enConSys.executeControlSignal(csToEngineControlSystem);
    //Check health of this autopilot system and determine the need for replacement.
    healthCheck(pitch, roll, yaw, originalAileronPos, originalElevatorPos, originalRudderPos);
    this.executor2 = Executors.newSingleThreadScheduledExecutor();
    this.executor2.scheduleAtFixedRate(this::checkExecution, 0, 200, TimeUnit.MILLISECONDS);
  }
  /** 
   * Reads back sensor data and checks if execution was successful.
   * reflects the expected change within a margin of error of 
   * ±2% for the control surfaces and ±5% for engine parameters
   */
  
  public void checkExecution() {
    final int maxCount = 5; // Total iterations
    // Check whether execution was sucessful. (sc means success)
    boolean sc = verifyExecution(this.conSurf.sendSensorData(), this.enConSys.sendSensorData());
    this.count++;
    if (this.simulation) {
      sc = verifyExecution(this.conSurf.sendSensorData(-9, -9, -9), this.enConSys.sendSensorData());
    }
    //if success:
    if (sc) {
      this.retries = 0;
      this.executor2.shutdown();
      this.alertSent = true;
    }
    //check whether execution was sucessful.
    //if not, continue checking up to 3 times
    if (this.count == maxCount) { 
      this.count = 0;
      this.retries++;
      // 3 attempts reached. Alert the pilot via the user interface.
      if (this.retries == this.maxRetries) {
        this.retries = 0;
	this.alertSent = true;
        alertUserInterface();
      }
    } 

  }    

  /** 
   * Verify execution of control signal by checking sensor data and whether
   * change is reflected.
   *
   * @param sensorDataConSurf Sensor data from the control surface.
   * @param sensorDataEngineControl Sensor data from the engine control system.
   * @return true if the execution is successful, false otherwise.
   */
    
  public boolean verifyExecution(SensorData sensorDataConSurf, SensorData sensorDataEngineControl) {
    double sensorDataPitch = sensorDataConSurf.getPitch();
    double sensorDataRoll = sensorDataConSurf.getRoll();
    double sensorDataYaw = sensorDataConSurf.getYaw();
    //sensor data for fuel flow and thrust
    double senDataFfOne = sensorDataEngineControl.getFuelFlow1();
    double senDataThOne = sensorDataEngineControl.getThrust1();
    double senDataFfTwo = sensorDataEngineControl.getFuelFlow2();
    double senDataThTwo = sensorDataEngineControl.getThrust2();
    double elevatorPos = this.conSurf.getElevatorPosition();
    double aileronPos = this.conSurf.getAileronPosition();
    double rudderPos = this.conSurf.getRudderPosition();
    double fuelFlowOne = this.enConSys.getFuelFlowOne();
    double thrustOne = this.enConSys.getThrustOne();
    double fuelFlowTwo = this.enConSys.getFuelFlowTwo();
    double thrustTwo = this.enConSys.getThrustTwo();
    //Compare control surface positions with sensor data
    boolean eleComp = valueCompare(sensorDataPitch, elevatorPos, this.moeControlSurfaces);
    boolean ailComp = valueCompare(sensorDataRoll, aileronPos, this.moeControlSurfaces);
    boolean rudComp = valueCompare(sensorDataYaw, rudderPos, this.moeControlSurfaces);
    boolean ffOneComp = valueCompare(senDataFfOne, fuelFlowOne, this.moeEngineParameters);
    boolean thOneComp = valueCompare(senDataThOne, thrustOne, this.moeEngineParameters);
    boolean ffTwoComp = valueCompare(senDataFfTwo, fuelFlowTwo, this.moeEngineParameters);
    boolean thTwoComp = valueCompare(senDataThTwo, thrustTwo, this.moeEngineParameters);
    //Success if all values are within Margin of error
    boolean sc = eleComp && ailComp && rudComp && ffOneComp && ffTwoComp && thOneComp && thTwoComp;
    if (sc) {
      this.retries = 0;
      return true; // Exit if successful
    }
    return false;
  }
  /**.
   * Compares the value of sensor data and actual position
   *
   * @param sensorData value of the sensor data
   * @param position position of control surface or parameters of engine control system
   * @param moe the margin of error allowed
   * @return whether after comparison the margin of error is larger than allowed
   */
  
  public static boolean valueCompare(double sensorData, double position, double moe) {
    return Math.abs(sensorData - position) / (position) * 100 <= moe
      && Math.abs(sensorData - position) / (position) * 100 >= -moe;
  }
  /**.
   * Calculate the pitch adjustment
   *
   * @return the value of pitch that should be adjusted by
   */
  
  private double calculateDesiredPitch() {
    if (this.waypoints.isEmpty()) {
      return 0; // No change if no waypoints left
    }
    //Assuming waypoints are in order
    Waypoint w = this.waypoints.get(0); // Get the next waypoint
    double altiudeDifference = this.altitude - w.getAltitude();
    return altiudeDifference * 0.05; 
  }
  /**.
   * Calculate the roll adjustment
   *
   * @param currentRoll current roll reported by the attitude sensor
   * @return the value of roll that should be adjusted by
   */
  
  private double calculateDesiredRoll() {
    if (this.waypoints.isEmpty()) {
      return 0; // No change if no waypoints left
    }
    double desiredYaw = calculateDesiredYaw(this.conSurf.sendSensorData().getYaw());
    return desiredYaw * 0.1; 
  }
  /**.
   * Calculate the yaw adjustment
   *
   * @param currentYaw current yaw reported by the attitude sensor
   * @return the value of yaw that should be adjusted by
   */
  
  private double calculateDesiredYaw(double currentYaw) {
    // Assuming waypoints are in order
    if (this.waypoints.isEmpty()) {
      return 0; // No change if no waypoints left
    }
    Waypoint w = this.waypoints.get(0); // Get the next waypoint
    double deltaLongitude = w.getxPos() - this.currentLongitude;
    double deltaLatitude = w.getyPos() - this.currentLatitude;

    // Calculate the desired heading
    double desiredHeading = Math.toDegrees(Math.atan2(deltaLongitude, deltaLatitude));

    // Normalize desired heading to [0, 360) degrees
    if (desiredHeading < 0) {
      desiredHeading += 360;
    }

    // Calculate the yaw adjustment needed to align with desired heading
    double desiredYaw = desiredHeading - currentYaw;

    // Normalize yaw adjustment to [-180, 180) degrees
    if (desiredYaw > 180) {
      desiredYaw -= 360;
    } else if (desiredYaw < -180) {
      desiredYaw += 360;
    }  

    return desiredYaw;
  }
  /**.
   * Calculate the fuel flow adjustment
   *
   * @return the value of thrust that should be adjusted by
   */
  
  private double calculateThrust() {
    if (this.waypoints.isEmpty()) {
      return 0; // No change if no waypoints left
    }
    //Assuming waypoints are in order
    Waypoint w = this.waypoints.get(0);
    return w.getSpeedRestriction();

  }
  /**.
   * Calculate the fuel flow adjustment
   *
   * @return the value of fuel flow that should be adjusted by
   */
  
  private double calculateFuelFlow() {
    if (this.waypoints.isEmpty()) {
      return 0; // No change if no waypoints left
    }
    double desiredThrust = calculateThrust();
    return desiredThrust * 0.1; 
  }
  /**
   * Checks health of autopilot system and determine whether it needs to be switched
   * to redundant autopilot.
   * For simplicity, the criteria for switching will be when expected change 
   * sent from control signals to control surface is not changed.
   *
   * @param dp is the desired pitch. The amount elevator should be adjusted by
   *
   * @param dr is the desiredRoll. The amount aileron should be adjusted by
   *
   * @param dy is the desiredYaw the amount rudder should be adjusted by
   * @param a is the original position of aileron
   * @param e is the original position of elevator
   * @param r is the original position of rudder
   *
   */
  
  public void healthCheck(double dp, double dr, double dy, double a, double e, double r) {
    double tol = 0.1; //Tolerance
    if (this.conSurf.getElevatorPosition() - (dp + e) > tol
        || this.conSurf.getAileronPosition() - (dr + a) > tol 
        || this.conSurf.getRudderPosition() - (dy + r) > tol) {
      this.errorInAutoPilot = true;
    }
  }
  /**
   * Checks status of error existing in auto pilot.
   *
   * @return error status
   * 
   */
  
  public boolean errorInAutoPilot() {
    return this.errorInAutoPilot;
  }
  /**.
   * Checks whether this autopilot is active
   *
   * @return active status
   */
   
  public boolean checkActive() {
    return this.active;
  }
  /**
   * Alerts the user interface in case of control signal failure.
   */
  
  public static void alertUserInterface() {
    HazardAlertsDisplay.issueHazardAlert();
    System.out.println("Control signal failure"); //$NON-NLS-1$
  }
  /**.
   * Checks whether autopilot is currently engaged
   *
   * @return whether autopilot is engaged or not.
   */
  
  public boolean checkEngaged() {
    return this.engage;
  }
  /**.
   * Receives the current altitude, the average of GPS and Barometric altitude
   *
   * @param altitude1 the current altitude
   */
  
  public void receiveAltitude(double altitude1) {
    this.altitude = altitude1;
  }
  /**.
   * Gets whether alert has been sent
   *
   * @return whether alert has been sent
   * 
   */
  
  public boolean getAlertSent() {
    return this.alertSent;
  }
 
  /**.
   * Sets waypoints. For testing purposes
   *
   * @param w the list of waypoints to be added
   * 
   */
  public void setWaypoints(List<Waypoint> w) {
    this.waypoints.addAll(w);
  }

}
