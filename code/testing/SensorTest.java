package code.testing;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import code.sensor.*;

public class SensorTest {
    // tests for AirspeedSensor.java
    /**
     * Test of getAirspeed method, of class AirspeedSensor.
     */
    @Test
    public void testGetAirspeed() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        assertEquals(50, airspeedSensor.getCurrentAirspeed(), 0.001);
    }

    /**
     * Test of setAirspeed method, of class AirspeedSensor.
     */
    @Test
    public void testSetAirspeed() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        airspeedSensor.setCurrentAirspeed(200);
        assertEquals(200, airspeedSensor.getCurrentAirspeed(), 0.001);
    }

    /**
     * Test of getUpdateFrequency method, of class AirspeedSensor.
     */
    @Test
    public void testGetMaxSpeed() {
        assertEquals(500, AirspeedSensor.getMaxSpeed(), 0.001);
    }

    /**
     * Test of getMinSpeed method, of class AirspeedSensor.
     */
    @Test
    public void testGetMinSpeed() {
        assertEquals(50, AirspeedSensor.getMinSpeed(), 0.001);
    }

    /**
     * Test of sendSensorData method, of class AirspeedSensor.
     */
    @Test
    public void testAirspeedSendSensorData() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        SensorData sensorData = airspeedSensor.sendSensorData();
        assertEquals(airspeedSensor.getCurrentAirspeed(), sensorData.getAirspeed(), 0.001);
    }


    // tests for AltitudeSensor.java
    /**
     * Test of getAltitudeUpdateFrequency method, of class AltitudeSensor.
     */
    @Test
    public void testGetAltitudeUpdateFrequency() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(500, altitudeSensor.getUpdateFrequency());
    }

    /**
     * Test of setAltitudeUpdateFrequency method, of class AltitudeSensor.
     */
    @Test
    public void testGetCurrAltitudeGPS() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCurrAltitudeGPS(), 0.001);
    }

    /**
     * Test of setCurrAltitudeGPS method, of class AltitudeSensor.
     */
    @Test
    public void testSetCurrAltitudeGPS() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        altitudeSensor.setCurrAltitudeGPS(20000);
        assertEquals(20000, altitudeSensor.getCurrAltitudeGPS(), 0.001);
    }

    /**
     * Test of getCurrAltitudeBarometric method, of class AltitudeSensor.
     */
    @Test
    public void testGetCurrAltitudeBarometric() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCurrAltitudeBarometric(), 0.001);
    }

    /**
     * Test of setCurrAltitudeBarometric method, of class AltitudeSensor.
     */
    @Test
    public void testSetCurrAltitudeBarometric() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        altitudeSensor.setCurrAltitudeBarometric(20000);
        assertEquals(20000, altitudeSensor.getCurrAltitudeBarometric(), 0.001);
    }

    /**
     * Test of getCombinedAltitude method, of class AltitudeSensor.
     */
    @Test
    public void testGetCombinedAltitude() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCombinedAltitude(), 0.01);
    }

    /**
     * Test of setCombinedAltitude method, of class AltitudeSensor.
     */
    @Test
    public void testGetMaxAltitude() {
        assertEquals(50000, AltitudeSensor.getMaxAltitude(), 0.001);
    }

    /**
     * Test of getMinAltitude method, of class AltitudeSensor.
     */
    @Test
    public void testGetMinAltitude() {
        assertEquals(-1000, AltitudeSensor.getMinAltitude(), 0.001);
    }

    /**
     * Test of sendSensorData method, of class AltitudeSensor.
     */
    @Test
    public void testAltitudeSendSensorData() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        SensorData sensorData = altitudeSensor.sendSensorData();
        assertEquals(altitudeSensor.getCurrAltitudeGPS(), sensorData.getAltitudeGPS(), 0.001);
        assertEquals(altitudeSensor.getCurrAltitudeBarometric(), sensorData.getAltitudeBarometric(), 0.001);
    }


    // tests for AttitudeSensor.java
    /**
     * Test of getPitch method, of class AttitudeSensor.
     */
    @Test
    public void testGetCurrPitch() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrPitch(), 0.001);
    }
    
    /**
     * Test of setPitch method, of class AttitudeSensor.
     */
    @Test
    public void testSetCurrPitch() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setPitch(20);
        assertEquals(20, attitudeSensor.getCurrPitch(), 0.001);
    }

    /**
     * Test of getRoll method, of class AttitudeSensor.
     */
    @Test
    public void testGetCurrRoll() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrRoll(), 0.001);
    }

    /**
     * Test of setRoll method, of class AttitudeSensor.
     */
    @Test
    public void testSetCurrRoll() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setRoll(20);
        assertEquals(20, attitudeSensor.getCurrRoll(), 0.001);
    }

    /**
     * Test of getYaw method, of class AttitudeSensor.
     */
    @Test
    public void testGetCurrYaw() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrYaw(), 0.001);
    }

    /**
     * Test of setYaw method, of class AttitudeSensor.
     */
    @Test
    public void testSetCurrYaw() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setYaw(20);
        assertEquals(20, attitudeSensor.getCurrYaw(), 0.001);
    }

    /**
     * Test of sendSensorData method, of class AttitudeSensor.
     */
    @Test
    public void testGetMaxPitch() {
        assertEquals(30, AttitudeSensor.getMaxPitch(), 0.001);
    }

    /**
     * Test of getMinPitch method, of class AttitudeSensor.
     */
    @Test
    public void testGetMinPitch() {
        assertEquals(-30, AttitudeSensor.getMinPitch(), 0.001);
    }

    /**
     * Test of sendSensorData method, of class AttitudeSensor.
     */
    @Test
    public void testGetMaxRoll() {
        assertEquals(60, AttitudeSensor.getMaxRoll(), 0.001);
    }

    /**
     * Test of getMinRoll method, of class AttitudeSensor.
     */
    @Test
    public void testGetMinRoll() {
        assertEquals(-60, AttitudeSensor.getMinRoll(), 0.001);
    }

    /**
     * Test of sendSensorData method, of class AttitudeSensor.
     */
    @Test
    public void testGetMaxYaw() {
        assertEquals(180, AttitudeSensor.getMaxYaw(), 0.001);
    }

    /**
     * Test of getMinYaw method, of class AttitudeSensor.
     */
    @Test
    public void testGetMinYaw() {
        assertEquals(-180, AttitudeSensor.getMinYaw(), 0.001);
    }


    // tests for Engine.java
    /**
     * Test of getFuelFlow method, of class Engine.
     */
    @Test
    public void testGetFuelFlow() {
        Engine engine = new Engine(1000, 1000);
        assertEquals(1000, engine.getFuelFlow(), 0.001);
    }

    /**
     * Test of setFuelFlow method, of class Engine.
     */
    @Test
    public void testSetFuelFlow() {
        Engine engine = new Engine(1000, 1000);
        engine.setFuelFlow(2000);
        assertEquals(2000, engine.getFuelFlow(), 0.001);
    }

    /**
     * Test of getThrust method, of class Engine.
     */
    @Test
    public void testGetThrust() {
        Engine engine = new Engine(1000, 1000);
        assertEquals(1000, engine.getCurrThrust(), 0.001);
    }

    /**
     * Test of setThrust method, of class Engine.
     */
    @Test
    public void testSetThrust() {
        Engine engine = new Engine(1000, 1000);
        engine.setCurrThrust(2000);
        assertEquals(2000, engine.getCurrThrust(), 0.001);
    }

    // tests for Sensor.java
    /**
     * Test of getUpdateFrequency method, of class Sensor.
     */
    @Test
    public void testGetSensorUpdateFrequency() {
        Sensor sensor = new Sensor(1000);
        assertEquals(1000, sensor.getUpdateFrequency());
    }

    // tests for SensorData.java
    /**
     * Test of getPitch method, of class SensorData.
     */
    @Test
    public void testGetPitch() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getPitch(), 0.001);
    }

    /**
     * Test of setPitch method, of class SensorData.
     */
    @Test
    public void testSetPitch() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setPitch(20);
        assertEquals(20, sensorData.getPitch(), 0.001);
    }

    /**
     * Test of getRoll method, of class SensorData.
     */
    @Test
    public void testGetRoll() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getRoll(), 0.001);
    }

    /**
     * Test of setRoll method, of class SensorData.
     */
    @Test
    public void testSetRoll() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setRoll(20);
        assertEquals(20, sensorData.getRoll(), 0.001);
    }

    /**
     * Test of getYaw method, of class SensorData.
     */
    @Test
    public void testGetYaw() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getYaw(), 0.001);
    }

    /**
     * Test of setYaw method, of class SensorData.
     */
    @Test
    public void testSetYaw() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setYaw(20);
        assertEquals(20, sensorData.getYaw(), 0.001);
    }

    /**
     * Test of getAltitude method, of class SensorData.
     */
    @Test
    public void testGetAltitude() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeGPS(), 0.001);
    }

    /**
     * Test of setAltitude method, of class SensorData.
     */
    @Test
    public void testSetAltitude() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeGPS(20000);
        assertEquals(20000, sensorData.getAltitudeGPS(), 0.001);
    }

    /**
     * Test of getAirspeed method, of class SensorData.
     */
    @Test
    public void testGetAltitudeGPS() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeGPS(), 0.001);
    }

    /**
     * Test of setAirspeed method, of class SensorData.
     */
    @Test
    public void testSetAltitudeGPS() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeGPS(20000);
        assertEquals(20000, sensorData.getAltitudeGPS(), 0.001);
    }

    /**
     * Test of getAltitudeBarometric method, of class SensorData.
     */
    @Test
    public void testGetAltitudeBarometric() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeBarometric(), 0.001);
    }

    /**
     * Test of setAltitudeBarometric method, of class SensorData.
     */
    @Test
    public void testSetAltitudeBarometric() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeBarometric(20000);
        assertEquals(20000, sensorData.getAltitudeBarometric(), 0.001);
    }

    /**
     * Test of getAirspeed method, of class SensorData.
     */
    @Test
    public void testSensorDataGetAirspeed() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAirspeed(), 0.001);
    }

    /**
     * Test of setAirspeed method, of class SensorData.
     */
    @Test
    public void testSensorDataSetAirspeed() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAirspeed(200);
        assertEquals(200, sensorData.getAirspeed(), 0.001);
    }

    /**
     * Test of getFuelFlow1 method, of class SensorData.
     */
    @Test
    public void testGetFuelFlow1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getFuelFlow1(), 0.001);
    }

    /**
     * Test of setFuelFlow1 method, of class SensorData.
     */
    @Test
    public void testSetFuelFlow1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setfuelFlow1(200);
        assertEquals(200, sensorData.getFuelFlow1(), 0.001);
    }

    /**
     * Test of getFuelFlow2 method, of class SensorData.
     */
    @Test
    public void testGetFuelFlow2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getFuelFlow2(), 0.001);
    }

    /**
     * Test of setFuelFlow2 method, of class SensorData.
     */
    @Test
    public void testSetFuelFlow2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setFuelFlow2(200);
        assertEquals(200, sensorData.getFuelFlow2(), 0.001);
    }

    /**
     * Test of getThrust1 method, of class SensorData.
     */
    @Test
    public void testGetThrust1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getThrust1(), 0.001);
    }

    /**
     * Test of setThrust1 method, of class SensorData.
     */
    @Test
    public void testSetThrust1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setThrust1(200);
        assertEquals(200, sensorData.getThrust1(), 0.001);
    }

    /**
     * Test of getThrust2 method, of class SensorData.
     */
    @Test
    public void testGetThrust2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getThrust2(), 0.001);
    }

    /**
     * Test of setThrust2 method, of class SensorData.
     */
    @Test
    public void testSetThrust2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setThrust2(200);
        assertEquals(200, sensorData.getThrust2(), 0.001);
    }
}
