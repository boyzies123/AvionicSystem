package testing;
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
import code.CoreSystem;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import code.sensor.SensorData;
import code.ui.AutopilotControlPanel;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
/**
 * These are the unit tests for testing fucntionality of the avionic system after 
 * components are integrated.
 */

public class IntegrationTesting {

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
     * Test that control signal can be sent to control surface and engine control system
     * ID4
     */
    @Test
    void testSendingControlSignal(){
        ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
        ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
        double oldAileronPosition = csToControlSurface.getAileronPosition();
        double oldRudderPosition = csToControlSurface.getRudderPosition();
        double oldElevatorPosition = csToControlSurface.getElevatorPosition();
        double oldThrustOnePosition = csToEngineControlSystem.getThrustOnePosition();
        double oldThrustTwoPosition = csToEngineControlSystem.getThrustOnePosition();
        double oldFuelFlowOnePosition = csToEngineControlSystem.getFuelFlowOne();
        double oldFuelFlowTwoPosition = csToEngineControlSystem.getFuelFlowTwo();
        this.controlSurface.executeControlSignal(csToControlSurface);
        this.engineControlSystem.executeControlSignal(csToEngineControlSystem);
        double newAileronPosition = csToControlSurface.getAileronPosition();
        double newRudderPosition = csToControlSurface.getRudderPosition();
        double newElevatorPosition = csToControlSurface.getElevatorPosition();
        double newThrustOnePosition = csToEngineControlSystem.getThrustOnePosition();
        double newThrustTwoPosition = csToEngineControlSystem.getThrustOnePosition();
        double newFuelFlowOnePosition = csToEngineControlSystem.getFuelFlowOne();
        double newFuelFlowTwoPosition = csToEngineControlSystem.getFuelFlowTwo();
        assertTrue(oldAileronPosition != newAileronPosition);
        assertTrue(oldRudderPosition != newRudderPosition);
        assertTrue(oldElevatorPosition != newElevatorPosition);
        assertTrue(oldThrustOnePosition != newThrustOnePosition);
        assertTrue(oldThrustTwoPosition != newThrustTwoPosition);
        assertTrue(oldFuelFlowOnePosition != newFuelFlowOnePosition);
        assertTrue(oldFuelFlowTwoPosition != newFuelFlowTwoPosition);
    }

    /**
     * Verify the execution of control signal
     * ID5
     */
    @Test
    public void testVerifyExecution_Success() {
        
        ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
        ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
        this.controlSurface.executeControlSignal(csToControlSurface);
        this.engineControlSystem.executeControlSignal(csToEngineControlSystem);

        // Verify execution
        assertTrue(this.autoPilotSystem.verifyExecution(this.controlSurface.sendSensorData(), this.engineControlSystem.sendSensorData()));
    }
    /**
     * Verify the failure of executing control signal
     * ID6
     */
    @Test
    public void testVerifyExecution_Failure() {
        
        ControlSignal csToControlSurface = new ControlSignal(9.8, 19.8, 29.8);
        ControlSignal csToEngineControlSystem = new ControlSignal(100, 120, 100, 120);
        this.controlSurface.executeControlSignal(csToControlSurface);
        this.engineControlSystem.executeControlSignal(csToEngineControlSystem);
        //Alter values of sensor so that it isn't same 
        ((AttitudeSensor)sensors.get(0)).setRoll(50000);
        ((AttitudeSensor)sensors.get(1)).setRoll(50000);
        ((AttitudeSensor)sensors.get(1)).setRoll(50000);
        // Verify execution
        assertFalse(this.autoPilotSystem.verifyExecution(this.controlSurface.sendSensorData(), this.engineControlSystem.sendSensorData()));
    }
    /**
     * Test that health checking of autopilot system works
     * ID7
     */
    @Test
    public void redundancyAutoPilot(){
        CoreSystem coreSystem = new CoreSystem();
        // Set up control surface and sensor data to induce an error
        coreSystem.getControlSurfaces().get(0).executeControlSignal(new ControlSignal(4, 10, 12)); // Set an incorrect elevator position
        coreSystem.getAutoPilotSystem().healthCheck(1.0, 1.0, 1.0, 0.0, 0.0, 0.0);
        assertTrue(coreSystem.getAutoPilotSystem().errorInAutoPilot());
        coreSystem.checkErrorInAutoPilot();
        assertTrue(coreSystem.getAutoPilotSystem.getStatus());
        assertFalse(coreSystem.getBackUpAutoPilotSystem.getStatus())
        
    }
    /**
     * Test that the alerting pilot works after failing three times.
     * ID8
     */
    @Test
    public void testAlertPilot() throws InterruptedException {
        autoPilotSystem.setSimulation(true);
        autoPilotSystem.start();
        autoPilotSystem.sendControlSignal();
        
        // Allow time for retries
        Thread.sleep(2000); // Wait to ensure retries happen

        // Verify that alert has been sent 
        assertEquals(true, autoPilotSystem.getAlertSent());
        autoPilotSystem.setSimulation(false);
    }
    
}
