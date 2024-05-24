package code.testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import code.sensor.*;

public class SensorTest {
    // tests for AirspeedSensor.java
    // get airspeed
    @Test
    public void testGetAirspeed() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        assertEquals(1000, airspeedSensor.getCurrentAirspeed(), 0.001);
    }

    // set airspeed	
    @Test
    public void testSetAirspeed() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        airspeedSensor.setCurrentAirspeed(200);
        assertEquals(200, airspeedSensor.getCurrentAirspeed(), 0.001);
    }

    // get max speed
    @Test
    public void testGetMaxSpeed() {
        assertEquals(500, AirspeedSensor.getMaxSpeed(), 0.001);
    }

    // get min speed
    @Test
    public void testGetMinSpeed() {
        assertEquals(50, AirspeedSensor.getMinSpeed(), 0.001);
    }

    // send sensor data
    @Test
    public void testAirspeedSendSensorData() {
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        SensorData sensorData = airspeedSensor.sendSensorData();
        assertEquals(airspeedSensor.getCurrentAirspeed(), sensorData.getAirspeed(), 0.001);
    }


    // tests for AltitudeSensor.java
    // get update frequency
    @Test
    public void testGetAltitudeUpdateFrequency() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(500, altitudeSensor.getUpdateFrequency());
    }

    // get current altitude GPS
    @Test
    public void testGetCurrAltitudeGPS() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCurrAltitudeGPS(), 0.001);
    }

    // set current altitude GPS
    @Test
    public void testSetCurrAltitudeGPS() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        altitudeSensor.setCurrAltitudeGPS(20000);
        assertEquals(20000, altitudeSensor.getCurrAltitudeGPS(), 0.001);
    }

    // get current altitude barometric
    @Test
    public void testGetCurrAltitudeBarometric() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCurrAltitudeBarometric(), 0.001);
    }

    // set current altitude barometric
    @Test
    public void testSetCurrAltitudeBarometric() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        altitudeSensor.setCurrAltitudeBarometric(20000);
        assertEquals(20000, altitudeSensor.getCurrAltitudeBarometric(), 0.001);
    }

    // get combined altitude
    @Test
    public void testGetCombinedAltitude() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        assertEquals(10000, altitudeSensor.getCombinedAltitude(), 0.01);
    }

    // get max altitude
    @Test
    public void testGetMaxAltitude() {
        assertEquals(50000, AltitudeSensor.getMaxAltitude(), 0.001);
    }

    // get min altitude
    @Test
    public void testGetMinAltitude() {
        assertEquals(-1000, AltitudeSensor.getMinAltitude(), 0.001);
    }

    // send sensor data
    @Test
    public void testAltitudeSendSensorData() {
        AltitudeSensor altitudeSensor = new AltitudeSensor(10000, 10000);
        SensorData sensorData = altitudeSensor.sendSensorData();
        assertEquals(altitudeSensor.getCurrAltitudeGPS(), sensorData.getAltitudeGPS(), 0.001);
        assertEquals(altitudeSensor.getCurrAltitudeBarometric(), sensorData.getAltitudeBarometric(), 0.001);
    }


    // tests for AttitudeSensor.java
    // get current pitch
    @Test
    public void testGetCurrPitch() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrPitch(), 0.001);
    }
    
    // set current pitch
    @Test
    public void testSetCurrPitch() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setPitch(20);
        assertEquals(20, attitudeSensor.getCurrPitch(), 0.001);
    }

    // get current roll
    @Test
    public void testGetCurrRoll() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrRoll(), 0.001);
    }

    // set current roll
    @Test
    public void testSetCurrRoll() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setRoll(20);
        assertEquals(20, attitudeSensor.getCurrRoll(), 0.001);
    }

    // get current yaw
    @Test
    public void testGetCurrYaw() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        assertEquals(0, attitudeSensor.getCurrYaw(), 0.001);
    }

    // set current yaw
    @Test
    public void testSetCurrYaw() {
        AttitudeSensor attitudeSensor = new AttitudeSensor(0.0, 0.0, 0.0);
        attitudeSensor.setYaw(20);
        assertEquals(20, attitudeSensor.getCurrYaw(), 0.001);
    }

    // get max pitch
    @Test
    public void testGetMaxPitch() {
        assertEquals(30, AttitudeSensor.getMaxPitch(), 0.001);
    }

    // get min pitch
    @Test
    public void testGetMinPitch() {
        assertEquals(-30, AttitudeSensor.getMinPitch(), 0.001);
    }

    // get max roll
    @Test
    public void testGetMaxRoll() {
        assertEquals(60, AttitudeSensor.getMaxRoll(), 0.001);
    }

    // get min roll
    @Test
    public void testGetMinRoll() {
        assertEquals(-60, AttitudeSensor.getMinRoll(), 0.001);
    }

    // get max yaw
    @Test
    public void testGetMaxYaw() {
        assertEquals(180, AttitudeSensor.getMaxYaw(), 0.001);
    }

    // get min yaw
    @Test
    public void testGetMinYaw() {
        assertEquals(-180, AttitudeSensor.getMinYaw(), 0.001);
    }


    // tests for Engine.java
    // get fuel flow
    @Test
    public void testGetFuelFlow() {
        Engine engine = new Engine(1000, 1000);
        assertEquals(1000, engine.getFuelFlow(), 0.001);
    }

    // set fuel flow
    @Test
    public void testSetFuelFlow() {
        Engine engine = new Engine(1000, 1000);
        engine.setFuelFlow(2000);
        assertEquals(2000, engine.getFuelFlow(), 0.001);
    }

    // invalid tests
    @Test
    public void testSetFuelFlowInvalid() {
        Engine engine = new Engine(1000, 1000);
        engine.setFuelFlow(5000);
        assertEquals(5000, engine.getFuelFlow(), 0.001);
    }
    @Test
    public void testSetFuelFlowInvalid2() {
        Engine engine = new Engine(1000, 1000);
        engine.setFuelFlow(-1000);
        assertEquals(-1000, engine.getFuelFlow(), 0.001);
    }

    // get current thrust
    @Test
    public void testGetThrust() {
        Engine engine = new Engine(1000, 1000);
        assertEquals(1000, engine.getCurrThrust(), 0.001);
    }

    // set current thrust
    @Test
    public void testSetThrust() {
        Engine engine = new Engine(1000, 1000);
        engine.setCurrThrust(2000);
        assertEquals(2000, engine.getCurrThrust(), 0.001);
    }

    // tests for Sensor.java
    // get update frequency
    @Test
    public void testGetSensorUpdateFrequency() {
        Sensor sensor = new Sensor(1000);
        assertEquals(1000, sensor.getUpdateFrequency());
    }

    // tests for SensorData.java
    // get pitch
    @Test
    public void testGetPitch() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getPitch(), 0.001);
    }

    // set pitch
    @Test
    public void testSetPitch() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setPitch(20);
        assertEquals(20, sensorData.getPitch(), 0.001);
    }

    // get roll
    @Test
    public void testGetRoll() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getRoll(), 0.001);
    }

    // set roll
    @Test
    public void testSetRoll() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setRoll(20);
        assertEquals(20, sensorData.getRoll(), 0.001);
    }

    // get yaw
    @Test
    public void testGetYaw() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getYaw(), 0.001);
    }

    // set yaw
    @Test
    public void testSetYaw() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setYaw(20);
        assertEquals(20, sensorData.getYaw(), 0.001);
    }

    // get altitude
    @Test
    public void testGetAltitude() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeGPS(), 0.001);
    }

    // set altitude
    @Test
    public void testSetAltitude() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeGPS(20000);
        assertEquals(20000, sensorData.getAltitudeGPS(), 0.001);
    }

    // get altitude GPS
    @Test
    public void testGetAltitudeGPS() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeGPS(), 0.001);
    }

    // set altitude GPS
    @Test
    public void testSetAltitudeGPS() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeGPS(20000);
        assertEquals(20000, sensorData.getAltitudeGPS(), 0.001);
    }

    // get altitude barometric
    @Test
    public void testGetAltitudeBarometric() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAltitudeBarometric(), 0.001);
    }

    // set altitude barometric
    @Test
    public void testSetAltitudeBarometric() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAltitudeBarometric(20000);
        assertEquals(20000, sensorData.getAltitudeBarometric(), 0.001);
    }

    // get airspeed
    @Test
    public void testSensorDataGetAirspeed() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getAirspeed(), 0.001);
    }

    // set airspeed
    @Test
    public void testSensorDataSetAirspeed() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setAirspeed(200);
        assertEquals(200, sensorData.getAirspeed(), 0.001);
    }

    // get fuel flow 1
    @Test
    public void testGetFuelFlow1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getFuelFlow1(), 0.001);
    }

    // set fuel flow 1
    @Test
    public void testSetFuelFlow1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setfuelFlow1(200);
        assertEquals(200, sensorData.getFuelFlow1(), 0.001);
    }

    // get fuel flow 2
    @Test
    public void testGetFuelFlow2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getFuelFlow2(), 0.001);
    }

    // set fuel flow 2
    @Test
    public void testSetFuelFlow2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setFuelFlow2(200);
        assertEquals(200, sensorData.getFuelFlow2(), 0.001);
    }

    // get thrust 1
    @Test
    public void testGetThrust1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getThrust1(), 0.001);
    }

    // set thrust 1
    @Test
    public void testSetThrust1() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setThrust1(200);
        assertEquals(200, sensorData.getThrust1(), 0.001);
    }

    // get thrust 2
    @Test
    public void testGetThrust2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        assertEquals(0, sensorData.getThrust2(), 0.001);
    }

    // set thrust 2
    @Test
    public void testSetThrust2() {
        SensorData sensorData = new SensorData(0.0, 0.0, 0.0);
        sensorData.setThrust2(200);
        assertEquals(200, sensorData.getThrust2(), 0.001);
    }
}
