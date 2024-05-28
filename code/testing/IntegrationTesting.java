package code.testing;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.ArrayList;

import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSignal;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;
import code.core.CoreSystem;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import code.src.Waypoint;
import code.ui.AutopilotControlPanel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
/*
 * Tests made by: Yi Chen
 * Date created: 25/05/2024
 * Date modified: 27/05/2024
 */
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
        this.autoPilotSystem = new AutoPilotSystem(this.controlSurface, this.engineControlSystem, true, true);
        this.backupAutoPilotSystem = new AutoPilotSystem(this.controlSurface, this.engineControlSystem, true, true);
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
        double oldAileronPosition = controlSurface.getAileronPosition();
        double oldRudderPosition = controlSurface.getRudderPosition();
        double oldElevatorPosition = controlSurface.getElevatorPosition();
        double oldThrustOnePosition = engineControlSystem.getThrustOne();
        double oldThrustTwoPosition = engineControlSystem.getThrustTwo();
        double oldFuelFlowOnePosition = engineControlSystem.getFuelFlowOne();
        double oldFuelFlowTwoPosition = engineControlSystem.getFuelFlowTwo();
        System.out.println(oldAileronPosition);
        this.controlSurface.executeControlSignal(csToControlSurface);
        this.engineControlSystem.executeControlSignal(csToEngineControlSystem);
        double newAileronPosition = controlSurface.getAileronPosition();
        System.out.println(newAileronPosition);
        double newRudderPosition = controlSurface.getRudderPosition();
        double newElevatorPosition = controlSurface.getElevatorPosition();
        double newThrustOnePosition = engineControlSystem.getThrustOne();
        double newThrustTwoPosition = engineControlSystem.getThrustOne();
        double newFuelFlowOnePosition = engineControlSystem.getFuelFlowOne();
        double newFuelFlowTwoPosition = engineControlSystem.getFuelFlowTwo();
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
        //assertNotEquals(((AttitudeSensor)sensors.get(1)).getCurrRoll(), ((AttitudeSensor)sensors.get(2)).getCurrRoll());
        assertEquals(this.engineControlSystem.sendSensorData().getThrust1(), this.engineControlSystem.getThrustOne());
        // Verify execution
        assertNotEquals(Math.abs(this.controlSurface.sendSensorData().getRoll() - this.controlSurface.getAileronPosition()) / (this.controlSurface.getAileronPosition()) * 100, 0);
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
        ((AttitudeSensor)sensors.get(0)).setRoll(500);
        ((AttitudeSensor)sensors.get(1)).setRoll(5000);
        ((AttitudeSensor)sensors.get(2)).setRoll(50000);
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
        assertFalse(coreSystem.getAutoPilotSystem().checkActive());
        assertTrue(coreSystem.getbackUpAutoPilotSystem().checkActive());
        
    }
    /**
     * Test that the alerting pilot works after failing three times.
     * ID8
     */
    @Test
    public void testAlertPilot() throws InterruptedException {
        List<Waypoint> waypoints = new ArrayList<Waypoint>();

        waypoints.add(new Waypoint(1, 2, 3, 4, 5));
        waypoints.add(new Waypoint(5, 6, 3, 6, 5));
        waypoints.add(new Waypoint(5, 6, 3, 6, 5));
        AutoPilotSystem autoPilotSystem1 = new AutoPilotSystem(this.controlSurface, this.engineControlSystem, true, true);
        autoPilotSystem1.setWaypoints(waypoints);
        autoPilotSystem1.start();
        autoPilotSystem1.sendControlSignal();
        
        // Allow time for retries
        Thread.sleep(5000); // Wait to ensure retries happen

        // Verify that alert has been sent 
        assertEquals(true, autoPilotSystem1.getAlertSent());
        //autoPilotSystem.setSimulation(false);
    }
    
}
