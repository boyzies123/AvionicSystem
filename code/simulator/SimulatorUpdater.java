/*
 * Code made by: Harry Booth-Beach
 * Date created: 27/05/2024
 * Date modified: 27/05/2024
 */
package code.simulator;

import java.util.List;

import code.core.CoreSystem;
import code.sensor.AirspeedSensor;
import code.sensor.AltitudeSensor;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;

public class SimulatorUpdater implements Runnable {
    private Sensor sensor;
    private List<Instance> instances;
    private CoreSystem cS;

    public SimulatorUpdater(List<Instance> instances, Sensor sensor, CoreSystem cS) {
        this.instances = instances;
        this.sensor = sensor;
        this.cS = cS;
    }

    @Override
    public void run() {
        for (Instance instance : instances) {
            try {
                Thread.sleep(sensor.getUpdateFrequency());
                // Check type of sensor and then update it accordingly
                if (sensor instanceof AirspeedSensor) {
                    ((AirspeedSensor)sensor).setCurrentAirspeed(instance.getValues().get(instance.getFieldNames().get(0)));
                } 
                else if (sensor instanceof AltitudeSensor) {
                    ((AltitudeSensor)sensor).setCurrAltitudeGps(instance.getValues().get(instance.getFieldNames().get(0)));
                    ((AltitudeSensor)sensor).setCurrAltitudeBarometric(instance.getValues().get(instance.getFieldNames().get(1)));
                }
                else if (sensor instanceof AttitudeSensor) {
                    ((AttitudeSensor)sensor).setPitch(instance.getValues().get(instance.getFieldNames().get(0)));
                    ((AttitudeSensor)sensor).setRoll(instance.getValues().get(instance.getFieldNames().get(1)));
                    ((AttitudeSensor)sensor).setYaw(instance.getValues().get(instance.getFieldNames().get(2)));
                }
                else if (sensor instanceof Engine) {
                    ((Engine)sensor).setCurrThrust(instance.getValues().get(instance.getFieldNames().get(0)));
                }
                // Use core system to check errors
                cS.run();
            } catch (InterruptedException e) {
            }
        }
    }
}
