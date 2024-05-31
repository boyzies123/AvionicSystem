package code.testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import code.sensor.*;
import code.core.*;
import code.autopilot.*;

public class CoreTest {

    // Test the Getter and Setter methods
    /**
     * Test of getAltitudeSensors method
     */
    @Test
    public void testGetAltitudeSensors() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> altitudeSensors = new ArrayList<>();
        altitudeSensors.add(new AltitudeSensor(50, 50));
        altitudeSensors.add(new AltitudeSensor(50, 50));
        altitudeSensors.add(new AltitudeSensor(50, 50));
        coreSystem.setAltitudeSensors(altitudeSensors);
        assertEquals(altitudeSensors, coreSystem.getAltitudeSensors());
    }

    /**
     * Test of getAttitudeSensors method
     */
    @Test
    public void testGetAttitudeSensors() {
        CoreSystem coreSystem = new CoreSystem();
        List<Sensor> attitudeSensors = new ArrayList<>();
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        coreSystem.setAttitudeSensors(attitudeSensors);        
        assertEquals(attitudeSensors, coreSystem.getAttitudeSensors());
    }
    /**
     * Test of getAirspeedSensors method
     */
    @Test
    public void testGetAirspeedSensors() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> airspeedSensors = new ArrayList<>();
        airspeedSensors.add(new AirspeedSensor());
        airspeedSensors.add(new AirspeedSensor());
        airspeedSensors.add(new AirspeedSensor());
        coreSystem.setAirspeedSensors(airspeedSensors);
        assertEquals(airspeedSensors, coreSystem.getAirspeedSensors());
    }

    /**
     * Test of getAutoPilotSystem method
     */
    @Test
    public void testGetAutoPilotSystem() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> attitudeSensors = new ArrayList<>();
        Engine[] engines = new Engine[2];
        engines[0] = new Engine(100, 100);
        engines[1] = new Engine(100, 100);
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        ControlSurface controlSurface = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        AutoPilotSystem autoPilotSystem = new AutoPilotSystem(controlSurface, new EngineControlSystem(engines[0], engines[1]), true, false);
        coreSystem.setAutoPilotSystem(autoPilotSystem);
        assertEquals(autoPilotSystem, coreSystem.getAutoPilotSystem());
    }

    /**
     * Test of getBackupAutoPilotSystem method
     */
    @Test
    public void testGetBackupAutoPilotSystem() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> attitudeSensors = new ArrayList<>();
        Engine[] engines = new Engine[2];
        engines[0] = new Engine(100, 100);
        engines[1] = new Engine(100, 100);
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        ControlSurface controlSurface = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        AutoPilotSystem backupAutoPilotSystem = new AutoPilotSystem(controlSurface, new EngineControlSystem(engines[0], engines[1]), true, false);
        coreSystem.setBackupAutoPilotSystem(backupAutoPilotSystem);
        assertEquals(backupAutoPilotSystem, coreSystem.getBackUpAutoPilotSystem());
    }

    /**
     * Test of getEngines method
     */
    @Test
    public void testGetEngines() {
        CoreSystem coreSystem = new CoreSystem();
        Engine[] engines = new Engine[2];
        engines[0] = new Engine(100, 100);
        engines[1] = new Engine(100, 100);
        coreSystem.setEngines(engines);
        assertArrayEquals(engines, coreSystem.getEngines());
    }
    
    /**
     * Test of 2oo3 voting system 
     * Should pass as all sensors are the same
     */
    @Test
    public void test2oo3Voting1() {
        CoreSystem coreSystem = new CoreSystem();
        AltitudeSensor sensor1 = new AltitudeSensor(50, 50);
        double num = coreSystem.twoOutOfThreeVote(sensor1.getCombinedAltitude(), sensor1.getCombinedAltitude(), sensor1.getCombinedAltitude());
        assertEquals(50, num, 0.0);
    }

    /**
     * Test of 2oo3 voting system
     * Should fail as two sensors is different
     */
    @Test
    public void test2oo3Voting2() {
        CoreSystem coreSystem = new CoreSystem();
        AltitudeSensor sensor1 = new AltitudeSensor(50, 50);
        AltitudeSensor sensor2 = new AltitudeSensor(100, 100);
        AltitudeSensor sensor3 = new AltitudeSensor(200, 200);
        double num = coreSystem.twoOutOfThreeVote(sensor1.getCombinedAltitude(), sensor2.getCombinedAltitude(), sensor3.getCombinedAltitude());
        assertEquals(-9999, num, 0.0);
    }

    /**
     * Test of 2oo3 voting system
     * Should pass as two sensors are the same (sensor 1 and sensor 2)
     */
    @Test
    public void test2oo3Voting3() {
        CoreSystem coreSystem = new CoreSystem();
        AltitudeSensor sensor1 = new AltitudeSensor(50, 50);
        AltitudeSensor sensor2 = new AltitudeSensor(50, 50);
        AltitudeSensor sensor3 = new AltitudeSensor(200, 200);
        double num = coreSystem.twoOutOfThreeVote(sensor1.getCombinedAltitude(), sensor2.getCombinedAltitude(), sensor3.getCombinedAltitude());
        assertEquals(50, num, 0.0);
    }

    /**
     * Test of 2oo3 voting system
     * Should pass as two sensors are the same (sensor 1 and sensor 3)
     */
    @Test
    public void test2oo3Voting4() {
        CoreSystem coreSystem = new CoreSystem();
        AltitudeSensor sensor1 = new AltitudeSensor(50, 50);
        AltitudeSensor sensor2 = new AltitudeSensor(100, 100);
        AltitudeSensor sensor3 = new AltitudeSensor(50, 50);
        double num = coreSystem.twoOutOfThreeVote(sensor1.getCombinedAltitude(), sensor2.getCombinedAltitude(), sensor3.getCombinedAltitude());
        assertEquals(50, num, 0.0);
    }

    /**
     * Test of 2oo3 voting system
     * Should pass as two sensors are the same (sensor 2 and sensor 3)
     */
    @Test
    public void test2oo3Voting5() {
        CoreSystem coreSystem = new CoreSystem();
        AltitudeSensor sensor1 = new AltitudeSensor(50, 50);
        AltitudeSensor sensor2 = new AltitudeSensor(100, 100);
        AltitudeSensor sensor3 = new AltitudeSensor(100, 100);
        double num = coreSystem.twoOutOfThreeVote(sensor1.getCombinedAltitude(), sensor2.getCombinedAltitude(), sensor3.getCombinedAltitude());
        assertEquals(100, num, 0.0);
    }

    // Test the detection of faults
    /**
     * Test of fault detection
     * detects all sensors are broken
     */
    @Test
    public void testFaultDetection() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> altitudeSensors = new ArrayList<>();
        List <Sensor> attitudeSensors = new ArrayList<>();
        List <Sensor> airspeedSensors = new ArrayList<>();
        altitudeSensors.add(new AltitudeSensor(50, 50));
        altitudeSensors.add(new AltitudeSensor(100, 100));
        altitudeSensors.add(new AltitudeSensor(200, 200));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        attitudeSensors.add(new AttitudeSensor(15, 15, 15));
        AirspeedSensor airSensor1 = new AirspeedSensor();
        airSensor1.setCurrentAirspeed(100);
        AirspeedSensor airSensor2 = new AirspeedSensor();
        airSensor2.setCurrentAirspeed(200);
        AirspeedSensor airSensor3 = new AirspeedSensor();
        airSensor3.setCurrentAirspeed(300);
        airspeedSensors.add(airSensor1);
        airspeedSensors.add(airSensor2);
        airspeedSensors.add(airSensor3);
        coreSystem.setAirspeedSensors(airspeedSensors);
        coreSystem.setAttitudeSensors(attitudeSensors);
        coreSystem.setAltitudeSensors(altitudeSensors);
        coreSystem.detectFault();
        assertEquals(3, coreSystem.getFaults().size(), 0.0);
        assertEquals("Airspeed sensor fault detected.", coreSystem.getFaults().get(0));
        assertEquals("Altitude sensor fault detected.", coreSystem.getFaults().get(1));
        assertEquals("Attitude sensor fault detected.", coreSystem.getFaults().get(2));
    }
    /**
     * Test of fault detection
     * detects all sensors are working
     */
    @Test
    public void testFaultDetectionPass() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> altitudeSensors = new ArrayList<>();
        List <Sensor> attitudeSensors = new ArrayList<>();
        List <Sensor> airspeedSensors = new ArrayList<>();
        altitudeSensors.add(new AltitudeSensor(50, 50));
        altitudeSensors.add(new AltitudeSensor(100, 100));
        altitudeSensors.add(new AltitudeSensor(100, 100));
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        AirspeedSensor airSensor1 = new AirspeedSensor();
        airSensor1.setCurrentAirspeed(100);
        AirspeedSensor airSensor2 = new AirspeedSensor();
        airSensor1.setCurrentAirspeed(100);
        AirspeedSensor airSensor3 = new AirspeedSensor();
        airSensor1.setCurrentAirspeed(300);
        airspeedSensors.add(airSensor1);
        airspeedSensors.add(airSensor2);
        airspeedSensors.add(airSensor3);
        coreSystem.setAirspeedSensors(airspeedSensors);
        coreSystem.setAttitudeSensors(attitudeSensors);
        coreSystem.setAltitudeSensors(altitudeSensors);
        coreSystem.detectFault();
        assertEquals(0, coreSystem.getFaults().size(), 0.0);
    }

    // Test the detection of errors in autopilot
    /**
     * Test of error detection in autopilot
     * detects autoPilotSystem is broken
     */
    @Test
    public void testErrorDetection() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> attitudeSensors = new ArrayList<>();
        Engine[] engines = new Engine[2];
        engines[0] = new Engine(100, 100);
        engines[1] = new Engine(100, 100);
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        attitudeSensors.add(new AttitudeSensor(15, 15, 15));
        ControlSurface controlSurfaceOne = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        attitudeSensors.remove(2);
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        ControlSurface controlSurfaceTwo = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        AutoPilotSystem autoPilotSystem = new AutoPilotSystem(controlSurfaceOne, new EngineControlSystem(engines[0], engines[1]), true, false);
        AutoPilotSystem backupAutoPilotSystem = new AutoPilotSystem(controlSurfaceTwo, new EngineControlSystem(engines[0], engines[1]),false, false);
        coreSystem.setAutoPilotSystem(autoPilotSystem);
        coreSystem.setBackupAutoPilotSystem(backupAutoPilotSystem);
        coreSystem.checkErrorInAutoPilot();
        assertTrue("No error detected in autopilot", autoPilotSystem.checkActive());
        assertFalse("Error detected in backup autopilot", backupAutoPilotSystem.checkActive());
    }

    /**
     * Test of error detection in autopilot
     * detects backup autoPilotSystem is broken
     */
    @Test
    public void testErrorDetection2() {
        CoreSystem coreSystem = new CoreSystem();
        List <Sensor> attitudeSensors = new ArrayList<>();
        Engine[] engines = new Engine[2];
        engines[0] = new Engine(100, 100);
        engines[1] = new Engine(100, 100);
        attitudeSensors.add(new AttitudeSensor(5, 5, 5));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        attitudeSensors.add(new AttitudeSensor(10, 10, 10));
        ControlSurface controlSurfaceOne = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        attitudeSensors.remove(2);
        attitudeSensors.add(new AttitudeSensor(15, 15, 15));
        ControlSurface controlSurfaceTwo = new ControlSurface(attitudeSensors.get(0), attitudeSensors.get(1), attitudeSensors.get(2));
        AutoPilotSystem autoPilotSystem = new AutoPilotSystem(controlSurfaceOne, new EngineControlSystem(engines[0], engines[1]), false, false);
        AutoPilotSystem backupAutoPilotSystem = new AutoPilotSystem(controlSurfaceTwo, new EngineControlSystem(engines[0], engines[1]),true, false);
        coreSystem.setAutoPilotSystem(autoPilotSystem);
        coreSystem.setBackupAutoPilotSystem(backupAutoPilotSystem);
        coreSystem.checkErrorInAutoPilot();
        assertFalse("Error detected in autopilot", autoPilotSystem.checkActive());
        assertTrue("No error detected in backup autopilot", backupAutoPilotSystem.checkActive());
    }
}
