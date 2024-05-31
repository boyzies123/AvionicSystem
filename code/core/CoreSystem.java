package code.core;

import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;
import code.sensor.AirspeedSensor;
import code.sensor.AltitudeSensor;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import code.src.Initialisation;
import code.ui.AutopilotControlPanel;
import code.ui.PilotUserInterface;
import code.ui.SensorDataDisplay;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/*
 * Code made by: Yi Chen
 * Date created: 17/05/2024
 * Date modified: 22/05/2024
 */
/**.
 * Class of core system
 * All components are integrated through this class,
 * this class also manages functionality such as 
 * fault handling for sensors, and checking for auto pilot errors.
 */

public class CoreSystem {
  private ScheduledExecutorService executor;
  private AutoPilotSystem autoPilotSystem;
  private AutoPilotSystem backupAutoPilotSystem;
  private List<Sensor> attitudeSensors;
  private List<Sensor> altitudeSensors;
  private List<Sensor> airspeedSensors;
  private Engine [] engines = new Engine[2];
  private ControlSurface controlSurfaceOne;
  private ControlSurface controlSurfaceTwo;
  private static List<String> faults = new ArrayList<>();
  /**.
   * Creates a core system object
   * This avionic system is based of the Airbus A320.
   */
  
  public CoreSystem() {
    initializeComponents();
  } 
  /**.
   * Initialize components such as the UI and sensors
   * 
   */
  
  @Initialisation
  public void initializeComponents() {
    PilotUserInterface pilotUserInterface = new PilotUserInterface();
    pilotUserInterface.start();
    this.attitudeSensors = new ArrayList<>();
    this.altitudeSensors = new ArrayList<>();
    this.airspeedSensors = new ArrayList<>();
    //A320 has two engines.
    for (int i = 0; i < 2; i++) {
      this.engines[i] = new Engine(0, 0);
    }
    for (int i = 0; i < 3; i++) {
      this.attitudeSensors.add(new AttitudeSensor(0, 0, 0));
      this.airspeedSensors.add(new AirspeedSensor());
      this.altitudeSensors.add(new AltitudeSensor(0, 0));
    }
    // Create control surfaces and autopilot systems
    createControlSurfacesAndAutopilotSystems();
  }
  /**.
   * Create all the autopilot and control surfaces.
   * 
   */
  
  @Initialisation
  public void createControlSurfacesAndAutopilotSystems() {
    Sensor attSensorOne = this.attitudeSensors.get(0);
    Sensor attSensorTwo = this.attitudeSensors.get(1);
    Sensor attSensorThree = this.attitudeSensors.get(2);
    EngineControlSystem enCoSy1 = new EngineControlSystem(this.engines[0], this.engines[1]);
    this.controlSurfaceOne = new ControlSurface(attSensorOne, attSensorTwo, attSensorThree);
    this.controlSurfaceTwo = new ControlSurface(attSensorOne, attSensorTwo, attSensorThree);
    this.autoPilotSystem = new AutoPilotSystem(this.controlSurfaceOne, enCoSy1, true, false);
    EngineControlSystem enCoSy2 = new EngineControlSystem(this.engines[0], this.engines[1]);
    this.backupAutoPilotSystem = new AutoPilotSystem(this.controlSurfaceTwo, enCoSy2, false, false);
    AutopilotControlPanel.addAutoPilotSystem(this.autoPilotSystem, this.backupAutoPilotSystem);
  }
  /**.
   * Starts the whole system, and intializes many of the components.
   */
  
  public void start() {
        
    //Make sure that fault detection and checking error in autopilot will be running
    //and also sensor updates should happen as specified in the handout.
    final long airSensorFreq = this.airspeedSensors.get(0).getUpdateFrequency();
    final long attSensorFreq = this.attitudeSensors.get(0).getUpdateFrequency();
    final long altSensorFreq = this.altitudeSensors.get(0).getUpdateFrequency();
    final TimeUnit time = TimeUnit.MILLISECONDS;
    this.executor = Executors.newSingleThreadScheduledExecutor();
    this.executor.scheduleAtFixedRate(this::run, 0, 1000, time);
    this.executor.scheduleAtFixedRate(this::scheduleAirspeedSensorUpdate, 0, airSensorFreq, time);
    this.executor.scheduleAtFixedRate(this::scheduleAttitudeSensorUpdate, 0, attSensorFreq, time);
    this.executor.scheduleAtFixedRate(this::scheduleAltitudeSensorUpdate, 0, altSensorFreq, time);
    this.executor.scheduleAtFixedRate(this::scheduleEngineParameterUpdate, 0, 1000, time);
  }
  
  /**.
   * Continuously runs the detectfault method
   */
  
  public void run() {
    checkErrorInAutoPilot();
    detectFault(); 
    double altitude = ((AltitudeSensor) this.altitudeSensors.get(0)).getCombinedAltitude();
    this.autoPilotSystem.receiveAltitude(altitude);
  }
  /**.
   * Checks for error in autopilot and switch autopilot if error does exist.
   * 
   */
  
  public void checkErrorInAutoPilot() {
    if (this.autoPilotSystem.checkActive()) {
      if (this.autoPilotSystem.errorInAutoPilot()) {
        this.autoPilotSystem.setStatus(false);
        this.backupAutoPilotSystem.setStatus(true);
      }
    } else if (this.backupAutoPilotSystem.checkActive()) {
      if (this.backupAutoPilotSystem.errorInAutoPilot()) {
        this.backupAutoPilotSystem.setStatus(false);
        this.autoPilotSystem.setStatus(true);
      }
    }
    
  }
  /**.
    * Since sensors operate in 2oo3, a voting shall be done which helps detect fault
    *
    * @param v1 the value of first sensor
    * @param v2 the value of second sensor
    * @param v3 the value of third sensor
    * @return value of majority vote. If all three returns different vote, -9999 is returned
    */
  
  public static double twoOutOfThreeVote(double v1, double v2, double v3) {
    if (v1 == v2 || v1 == v3) {
      return v1;
    } else if (v2 == v3) {
      return v2;
    } else {
      return -9999;
    }
    
  }
    
  /**.
   * Detects faults of sensors by doing 2oo3 vote from getSensorValues,
   * checking also its in valid range.
   */
  public void detectFault() {
    double speedAirSpSen1 = ((AirspeedSensor) this.airspeedSensors.get(0)).getCurrentAirspeed();
    double speedAirSpSen2 = ((AirspeedSensor) this.airspeedSensors.get(1)).getCurrentAirspeed();
    double speedAirSpSen3 = ((AirspeedSensor) this.airspeedSensors.get(2)).getCurrentAirspeed();
    double altAltSensor1 = ((AltitudeSensor) this.altitudeSensors.get(0)).getCombinedAltitude();
    double altAltSensor2 = ((AltitudeSensor) this.altitudeSensors.get(1)).getCombinedAltitude();
    double altAltSensor3 = ((AltitudeSensor) this.altitudeSensors.get(2)).getCombinedAltitude();
    double pitchAttSensor1 = ((AttitudeSensor) this.attitudeSensors.get(0)).getCurrPitch();
    double pitchAttSensor2 = ((AttitudeSensor) this.attitudeSensors.get(1)).getCurrPitch();
    double pitchAttSensor3 = ((AttitudeSensor) this.attitudeSensors.get(2)).getCurrPitch();
    double rollAttSensor1 = ((AttitudeSensor) this.attitudeSensors.get(0)).getCurrRoll();
    double rollAttSensor2 = ((AttitudeSensor) this.attitudeSensors.get(1)).getCurrRoll();
    double rollAttSensor3 = ((AttitudeSensor) this.attitudeSensors.get(2)).getCurrRoll();
    double yawAttSensor1 = ((AttitudeSensor) this.attitudeSensors.get(0)).getCurrYaw();
    double yawAttSensor2 = ((AttitudeSensor) this.attitudeSensors.get(1)).getCurrYaw();
    double yawAttSensor3 = ((AttitudeSensor) this.attitudeSensors.get(2)).getCurrYaw();
    double airspeed = twoOutOfThreeVote(speedAirSpSen1, speedAirSpSen2, speedAirSpSen3);
    double altitude = twoOutOfThreeVote(altAltSensor1, altAltSensor2, altAltSensor3);
    double pitch = twoOutOfThreeVote(pitchAttSensor1, pitchAttSensor2, pitchAttSensor3);
    double roll = twoOutOfThreeVote(rollAttSensor1, rollAttSensor2, rollAttSensor3);
    double yaw = twoOutOfThreeVote(yawAttSensor1, yawAttSensor2, yawAttSensor3);
    double minSpeed = AirspeedSensor.getMinSpeed();
    double maxSpeed = AirspeedSensor.getMaxSpeed();
    double minAltitude = AirspeedSensor.getMinSpeed();
    double maxAltitude = AirspeedSensor.getMaxSpeed();
    double minPitch = AttitudeSensor.getMinPitch();
    double maxPitch = AttitudeSensor.getMaxPitch();
    double minYaw = AttitudeSensor.getMinYaw();
    double maxYaw = AttitudeSensor.getMaxYaw();
    double minRoll = AttitudeSensor.getMinRoll();
    double maxRoll = AttitudeSensor.getMaxRoll();
    boolean airspeedFault = airspeed == -9999 || airspeed < minSpeed || airspeed > maxSpeed;
    boolean altitudeFault = altitude == -9999 || altitude < minAltitude || altitude > maxAltitude;
    boolean pitchFault = pitch == -9999 || pitch < minPitch || pitch > maxPitch;
    boolean rollFault = roll == -9999 || roll < minRoll || roll > maxRoll;
    boolean yawFault = yaw == -9999 || yaw < minYaw || yaw > maxYaw;
        
    if (airspeedFault || altitudeFault || pitchFault || rollFault || yawFault) {
      handleFaults(airspeedFault, altitudeFault, pitchFault, rollFault, yawFault);
    } 
    displaySensorValues(airspeed, altitude, pitch, roll, yaw);
  }
  /**.
   * Check all faults and notify operator and active fail safe mode
   *
   * @param aiF true if airspeed fault
   * @param alF true if altitude fault
   * @param piF true if pitch fault
   * @param roF true if roll fault
   * @param yaF true if yaw fault
   */
    
  public static void handleFaults(boolean aiF, boolean alF, boolean piF, boolean roF, boolean yaF) {
    faults = new ArrayList<>();
    if (aiF) {
      faults.add("Airspeed sensor fault detected."); //$NON-NLS-1$
    }
    if (alF) {
      faults.add("Altitude sensor fault detected."); //$NON-NLS-1$
    }
    if (piF || roF || yaF) {
      faults.add("Attitude sensor fault detected."); //$NON-NLS-1$
    }
        
    notifyPilot(faults);
  }
  /**.
   * Notify pilot of the error 
   */
    
  public static void notifyPilot(List<String> faults) {
    for (String fault : faults) {
      System.out.println(fault);
    }
    //Notify pilot via hazardalertsdisplay.
    //Won't do anting because issuehazardalert did not end up getting implemented.
    //and fault wont be able to be passed to pilot
        
  }
  //Note all values are currently 0. When we start simulation, proper values are provided.
  /**.
   * Schedule updates of altitude sensor
   */
    
  public void scheduleAltitudeSensorUpdate() {
    for (Sensor sensor : this.altitudeSensors) {
      ((AltitudeSensor) sensor).setCurrAltitudeBarometric(0);
      ((AltitudeSensor) sensor).setCurrAltitudeGps(0);
    }
  }
  /**.
   * Schedule updates of attitude sensor
   */
     
  public void scheduleAttitudeSensorUpdate() {
    for (Sensor sensor : this.attitudeSensors) {
      ((AttitudeSensor) sensor).setYaw(0);
      ((AttitudeSensor) sensor).setRoll(0);
      ((AttitudeSensor) sensor).setRoll(0);
    }
  }
  /**.
   * Schedule updates of airspeed sensor
   */
    
  public void scheduleAirspeedSensorUpdate() {
    for (Sensor sensor : this.airspeedSensors) {
      ((AirspeedSensor) sensor).setCurrentAirspeed(0);
    }
  }
  /**.
  * Schedule updates of engine parameters
  */
  
  public void scheduleEngineParameterUpdate() {
    for (Engine e : this.engines) { 
      e.setCurrThrust(0);
      e.setFuelFlow(0);
    }
  }
    
  /**.
   * Display the sensor based on the current sensor value to the UI
   *
   * @param airs the current airspeed
   * @param alt the current altitude
   * @param pitch the current pitch
   * @param roll the current roll
   * @param yaw the current yaw
   */
    
  public void displaySensorValues(double airs, double alt, double pitch, double roll, double yaw) {
    SensorDataDisplay.updateAirspeed(airs);
    SensorDataDisplay.updateAltitude(alt);
    SensorDataDisplay.updatePitch(pitch);
    SensorDataDisplay.updateRoll(roll);
    SensorDataDisplay.updateYaw(yaw);
    SensorDataDisplay.updateThrust1(this.engines[0].getCurrThrust());
    SensorDataDisplay.updateThrust2(this.engines[1].getCurrThrust());
    SensorDataDisplay.updateFuel1(this.engines[0].getFuelFlow());
    SensorDataDisplay.updateFuel2(this.engines[1].getFuelFlow());

  }
  /**.
   * Gets all altitude sensors of this system
   *
   * @return list of altitude sensors
   */
    
  public List<Sensor> getAltitudeSensors() {
    return this.altitudeSensors;
  }
  /**.
   * Gets all airspeed sensors of this system
   *
   * @return list of airspeed sensors
   */
    
  public List<Sensor> getAirspeedSensors() {
    return this.airspeedSensors;
  }
  /**.
   * Gets all airspeed sensors of this system
   *
   * @return list of attitude sensors
   */
  
  public List<Sensor> getAttitudeSensors() {
    return this.attitudeSensors;
  }
  /**.
   * Gets all engines of this system
   *
   * @return Engine[] list of engines
   */
  
  public Engine[] getEngines() {
    return this.engines;
  }
  /**.
   * Gets the aircrafts autopilot system 
   *
   * @return autopilot system
   */
    
  public AutoPilotSystem getAutoPilotSystem() {
    return this.autoPilotSystem;
  }
  /**.
   * Gets the aircrafts backup autopilot system 
   *
   * @return backup autopilot system
   */
    
  public AutoPilotSystem getBackUpAutoPilotSystem() {
    return this.backupAutoPilotSystem;
  }
  /**.
   * Gets the faults of the system
   *
   * @return the faults
   */
  
  public List<String> getFaults() {
    return faults;
  }

  /**.
   * Gets the control surfaces of this system
   *
   * @return the control surface
   */
  public List<ControlSurface> getControlSurfaces() {
    List<ControlSurface> controlSurfaces = new ArrayList<>();
    controlSurfaces.add(this.controlSurfaceOne);
    controlSurfaces.add(this.controlSurfaceTwo);
    return controlSurfaces;
  }

  /**.
   * Sets the altitude sensors of this system
   *
   * @param altitudeSensors the altitude sensors
   */
  public void setAltitudeSensors(List<Sensor> altitudeSensors) {
    this.altitudeSensors = altitudeSensors;
  }

  /**.
   * Sets the airspeed sensors of this system
   *
   * @param airspeedSensors the airspeed sensors
   */
  public void setAirspeedSensors(List<Sensor> airspeedSensors) {
    this.airspeedSensors = airspeedSensors;
  }

  /**.
   * Sets the attitude sensors of this system
   *
   * @param attitudeSensors the attitude sensors
   */
  public void setAttitudeSensors(List<Sensor> attitudeSensors) {
    this.attitudeSensors = attitudeSensors;
  }

  /**.
   * Sets the engines of this system
   *
   * @param engines the engines
   */
  public void setEngines(Engine[] engines) {
    this.engines = engines;
  }

  /**.
   * Sets the autopilot system of this system
   *
   * @param autoPilotSystem the autopilot system
   */
  public void setAutoPilotSystem(AutoPilotSystem autoPilotSystem) {
    this.autoPilotSystem = autoPilotSystem;
  }

  /**.
   * Sets the backup autopilot system of this system
   *
   * @param backupAutoPilotSystem the backup autopilot system
   */
  public void setBackupAutoPilotSystem(AutoPilotSystem backupAutoPilotSystem) {
    this.backupAutoPilotSystem = backupAutoPilotSystem;
  }
  /**.
   * Starting point of the program
   *
   * @param args Command line argument passed
   */
  
  public static void main(String [] args) {
    CoreSystem coreSystem = new CoreSystem();
    coreSystem.start();
  }
}
