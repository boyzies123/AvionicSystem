package code.testing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSignal;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;
import code.core.CoreSystem;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import code.sensor.SensorData;
import code.src.Waypoint;
import code.ui.AutopilotControlPanel;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Tests made by: Yi Chen
 * Date created: 25/05/2024
 * Date modified: 27/05/2024
 */
/**
 * These are the tests for testing fucntionality of the avionic system after 
 * components are integrated.
 */

public class IntegrationTesting {
  private CoreSystem coreSystem;
  private AutoPilotSystem autoPilotSystem;
  private AutoPilotSystem backupAutoPilotSystem;
  private ControlSurface conSurf;
  private EngineControlSystem engConSys;
  List<Sensor> sensors = new ArrayList<>();
  /**.
   * Sets up all necessary components for testing
   */
  
  @BeforeEach
  public void setUp() {
    this.coreSystem = new CoreSystem();
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    Sensor sensor1 = this.sensors.get(0);
    Sensor sensor2 = this.sensors.get(1);
    Sensor sensor3 = this.sensors.get(2);
    this.conSurf = new ControlSurface(sensor1, sensor2, sensor3);
    this.engConSys = new EngineControlSystem(new Engine(400, 40), new Engine(400, 40));
    this.autoPilotSystem = new AutoPilotSystem(this.conSurf, this.engConSys, true, true);
    this.backupAutoPilotSystem = new AutoPilotSystem(this.conSurf, this.engConSys, true, true);
    AutopilotControlPanel.addAutoPilotSystem(this.autoPilotSystem, this.backupAutoPilotSystem);
    //We dont want it to run continously so set autopilot status to false
    this.autoPilotSystem.setStatus(false);
    this.backupAutoPilotSystem.setStatus(false);
  }


  /**.
   * Test that control signal can be sent to control surface and engine control system
   * ID4
   */
  @Test
  void testSendingControlSignal() {
    ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
    ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
    double oldAileronPosition = this.conSurf.getAileronPosition();
    final double oldRudderPosition = this.conSurf.getRudderPosition();
    final double oldElevatorPosition = this.conSurf.getElevatorPosition();
    final double oldThrustOnePosition = this.engConSys.getThrustOne();
    final double oldThrustTwoPosition = this.engConSys.getThrustTwo();
    final double oldFuelFlowOnePosition = this.engConSys.getFuelFlowOne();
    final double oldFuelFlowTwoPosition = this.engConSys.getFuelFlowTwo();
    this.conSurf.executeControlSignal(csToControlSurface);
    this.engConSys.executeControlSignal(csToEngineControlSystem);
    double newAileronPosition = this.conSurf.getAileronPosition();
    double newRudderPosition = this.conSurf.getRudderPosition();
    double newElevatorPosition = this.conSurf.getElevatorPosition();
    final double newThrustOnePosition = this.engConSys.getThrustOne();
    final double newThrustTwoPosition = this.engConSys.getThrustOne();
    final double newFuelFlowOnePosition = this.engConSys.getFuelFlowOne();
    final double newFuelFlowTwoPosition = this.engConSys.getFuelFlowTwo();
    assertTrue(oldAileronPosition != newAileronPosition);
    assertTrue(oldRudderPosition != newRudderPosition);
    assertTrue(oldElevatorPosition != newElevatorPosition);
    assertTrue(oldThrustOnePosition != newThrustOnePosition);
    assertTrue(oldThrustTwoPosition != newThrustTwoPosition);
    assertTrue(oldFuelFlowOnePosition != newFuelFlowOnePosition);
    assertTrue(oldFuelFlowTwoPosition != newFuelFlowTwoPosition);
  }

  /**.
   * Verify the execution of control signal
   * ID5
   */
  @Test
  public void testVerifyExecution_Success() {
        
    ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
    ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
    this.conSurf.executeControlSignal(csToControlSurface);
    this.engConSys.executeControlSignal(csToEngineControlSystem);
    // Verify execution
    SensorData sensorData1 = this.conSurf.sendSensorData();
    SensorData sensorData2 = this.engConSys.sendSensorData();
    assertTrue(this.autoPilotSystem.verifyExecution(sensorData1, sensorData2));
  }
  /**.
   * Verify the failure of executing control signal
   * ID6
   */
  
  @Test
  public void testVerifyExecution_Failure() {
        
    ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
    ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
    this.conSurf.executeControlSignal(csToControlSurface);
    this.engConSys.executeControlSignal(csToEngineControlSystem);
    //Alter values of sensor so that it isn't same 
    ((AttitudeSensor) this.sensors.get(0)).setRoll(500);
    ((AttitudeSensor) this.sensors.get(1)).setRoll(5000);
    ((AttitudeSensor) this.sensors.get(2)).setRoll(50000);
    // Verify execution
    SensorData s1 = this.conSurf.sendSensorData();
    SensorData s2 = this.engConSys.sendSensorData();
    assertFalse(this.autoPilotSystem.verifyExecution(s1, s2));
  }
  /**.
   * Test that health checking of autopilot system works
   * ID7
   */
  
  @Test
  public void redundancyAutoPilot() {
    // Set up control surface and sensor data to induce an error
    // Set an incorrect elevator position
    this.coreSystem.getControlSurfaces().get(0).executeControlSignal(new ControlSignal(4, 10, 12));
    this.coreSystem.getAutoPilotSystem().healthCheck(1.0, 1.0, 1.0, 0.0, 0.0, 0.0);
    assertTrue(this.coreSystem.getAutoPilotSystem().errorInAutoPilot());
    this.coreSystem.checkErrorInAutoPilot();
    assertFalse(this.coreSystem.getAutoPilotSystem().checkActive());
    assertTrue(this.coreSystem.getbackUpAutoPilotSystem().checkActive());
        
  }
  /**.
   * Test that the alerting pilot works after failing three times.
   * ID8
   */
  
  @Test
  public void testAlertPilot() throws InterruptedException {
    List<Waypoint> waypoints = new ArrayList<>();

    waypoints.add(new Waypoint(1, 2, 3, 4, 5));
    waypoints.add(new Waypoint(5, 6, 3, 6, 5));
    waypoints.add(new Waypoint(5, 6, 3, 6, 5));
    AutoPilotSystem autoPilSystem1 = new AutoPilotSystem(this.conSurf, this.engConSys, true, true);
    autoPilSystem1.setWaypoints(waypoints);
    autoPilSystem1.start();
    autoPilSystem1.sendControlSignal();
        
    // Allow time for retries
    Thread.sleep(5000); // Wait to ensure retries happen

    // Verify that alert has been sent 
    assertTrue(autoPilSystem1.getAlertSent());
  }
    
}
