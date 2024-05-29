package code.testing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSignal;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import code.ui.AutopilotControlPanel;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**.
 * Unit tests for autopilot module
 */
public class AutoPilotTest {

  private AutoPilotSystem autoPilotSystem;
  private AutoPilotSystem backupAutoPilotSystem;
  private ControlSurface conSurf;
  private EngineControlSystem engConSystem;
  List<Sensor> sensors = new ArrayList<>();
  /**.
   * Sets up all necessary components for testing
   */
  
  @BeforeEach
  public void setUp() {
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    this.sensors.add(new AttitudeSensor(50, 50, 50));
    Sensor sensor1 = this.sensors.get(0);
    Sensor sensor2 = this.sensors.get(1);
    Sensor sensor3 = this.sensors.get(2);
    this.conSurf = new ControlSurface(sensor1, sensor2, sensor3);
    this.engConSystem = new EngineControlSystem(new Engine(400, 40), new Engine(400, 40));
    this.autoPilotSystem = new AutoPilotSystem(this.conSurf, this.engConSystem, true, true);
    this.backupAutoPilotSystem = new AutoPilotSystem(this.conSurf, this.engConSystem, true, true);
    AutopilotControlPanel.addAutoPilotSystem(this.autoPilotSystem, this.backupAutoPilotSystem);
    //We dont want it to run continously so set autopilot status to false
    this.autoPilotSystem.setStatus(false);
    this.backupAutoPilotSystem.setStatus(false);
  }

  /**.
   * Test of start method, of the autopilot system.
   */

  @Test
  public void testStart() {
    this.autoPilotSystem.start();
    assertTrue(this.autoPilotSystem.checkEngaged());
  }
  /**.
   * Test of stop method, of the autopilot system.
   */
  
  @Test
  public void testStop() {
    this.autoPilotSystem.stop();
    assertFalse(this.autoPilotSystem.checkEngaged());
  }
  /**.
   * Test the status of autopilot
   */
  
  @Test
  public void testStatusSetting() {
    this.autoPilotSystem.setStatus(false);
    assertFalse(this.autoPilotSystem.checkActive());
    this.autoPilotSystem.setStatus(true);
    assertTrue(this.autoPilotSystem.checkActive());
  }


 
  /**.
   * Test that health checking of autopilot system works
   */
  @Test
  public void testHealthCheck() {
    // Set up control surface and sensor data to induce an error
    // Set an incorrect elevator position
    this.conSurf.executeControlSignal(new ControlSignal(4, 10, 12)); 
    this.autoPilotSystem.healthCheck(1.0, 1.0, 1.0, 0.0, 0.0, 0.0);
    assertTrue(this.autoPilotSystem.errorInAutoPilot());
  }
    
}
