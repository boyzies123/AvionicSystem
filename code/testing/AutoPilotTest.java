package code.testing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import java.util.ArrayList;

import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSignal;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;

import code.ui.AutopilotControlPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AutoPilotTest {

    private AutoPilotSystem autoPilotSystem;
    private AutoPilotSystem backupAutoPilotSystem;
    private ControlSurface controlSurface;
    private EngineControlSystem engineControlSystem;
    List <Sensor> sensors = new ArrayList <Sensor>();
    @BeforeEach
    public void setUp() {
        sensors.add(new AttitudeSensor(50, 50, 50));
        sensors.add(new AttitudeSensor(50, 50, 50));
        sensors.add(new AttitudeSensor(50, 50, 50));
        this.controlSurface = new ControlSurface(sensors.get(0), sensors.get(1), sensors.get(2));
        this.engineControlSystem = new EngineControlSystem(new Engine(400, 40), new Engine(400, 40));
        this.autoPilotSystem = new AutoPilotSystem(this.controlSurface, this.engineControlSystem, true);
        this.backupAutoPilotSystem = new AutoPilotSystem(this.controlSurface, this.engineControlSystem, true);
        AutopilotControlPanel.addAutoPilotSystem(this.autoPilotSystem, this.backupAutoPilotSystem);
        //We dont want it to run continously so set autopilot status to false
        autoPilotSystem.setStatus(false);
        backupAutoPilotSystem.setStatus(false);
    }

    @AfterEach
    public void tearDown() {
        this.autoPilotSystem = null;
    }
    /**
     * Test of start method, of the autopilot system.
     */

    @Test
    public void testStart() {
        this.autoPilotSystem.start();
        assertTrue(this.autoPilotSystem.checkEngaged());
    }
    /**
     * Test of stop method, of the autopilot system.
     */
    @Test
    public void testStop() {
        this.autoPilotSystem.stop();
        assertFalse(this.autoPilotSystem.checkEngaged());
    }
     /**
     * Test the status of autopilot
     */
    @Test
    void testStatusSetting() {
        autoPilotSystem.setStatus(false);
        assertFalse(autoPilotSystem.checkActive());
        autoPilotSystem.setStatus(true);
        assertTrue(autoPilotSystem.checkActive());
    }


 
    /**
     * Test that health checking of autopilot system works
     */
    @Test
    void testHealthCheck() {
        // Set up control surface and sensor data to induce an error
        this.controlSurface.executeControlSignal(new ControlSignal(4, 10, 12)); // Set an incorrect elevator position
        this.autoPilotSystem.healthCheck(1.0, 1.0, 1.0, 0.0, 0.0, 0.0);
        assertTrue(this.autoPilotSystem.errorInAutoPilot());
    }
    
}
