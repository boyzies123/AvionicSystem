/*
 * Code made by: Harry Booth-Beach
 * Date created: 13/05/2024
 * Date modified: 13/05/2024
 */
package code.sensor;

public interface Sensor {
    SensorData sendSensorData();
    void monitor();
    void reportError(String err);
}
