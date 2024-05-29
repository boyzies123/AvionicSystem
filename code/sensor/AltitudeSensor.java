/*
 * Code made by: Harry Booth-Beach, with modifications by Yi Chen
 * Date created: 17/05/2024
 * Date modified: 17/05/2024
 */

package code.sensor;

/**.
 * Class of altitude sensor, managing altitude
 */

public class AltitudeSensor extends Sensor {
  private double currAltitudeGps;
  private double currAltitudeBarometric;
  private static double MIN_ALTITUDE = -1000;
  private static double MAX_ALTITUDE = 50000;
  /**.
   * Creates altitude sensor object
   */
  
  public AltitudeSensor(double currAltitudeGps, double currAltitudeBarometric) {
    super(500);
    this.currAltitudeBarometric = currAltitudeBarometric;
    this.currAltitudeGps = currAltitudeGps;
  }
  /**.
   * Sends the sensor data 
   *
   * @return sensordata Sensor data produced by sensor
   */
  
  public SensorData sendSensorData() {
    SensorData sensorData = new SensorData();
    sensorData.setAltitudeGps(this.currAltitudeGps);
    sensorData.setAltitudeBarometric(this.currAltitudeBarometric);
    return sensorData;
  }
  /* Setter method section START */

  /**.
   * Setter method for current altitude GPS
   *
   * @param altitude current GPS altitude
   */
  public void setCurrAltitudeGps(double altitude) {
    this.currAltitudeGps = altitude;
  }

  /**.
   * Setter method for current altitude barometric
   *
   * @param altitude current barometric altitude
   */
  public void setCurrAltitudeBarometric(double altitude) {
    this.currAltitudeBarometric = altitude;
  }

  /* Setter method section END */

  /* Getter method section START */

  /**.
   * Getter method for current altitude GPS
   *
   * @return the current altitude in GPS
   */
  public double getCurrAltitudeGps() {
    return this.currAltitudeGps;
  }

  /**.
   * Getter method for average of the two altitudes
   *
   * @return the average altitude of barometric and GPS
   */
  public double getCombinedAltitude() {
    return (this.currAltitudeBarometric + this.currAltitudeGps) / 2;
  }
  /**.
   * Getter method for current altitude barometric
   *
   * @return the current altitude of barometric
   */
  
  public double getCurrAltitudeBarometric() {
    return this.currAltitudeBarometric;
  }
  /**.
   * Getter method for maximum altitude
   *
   * @return the maximum altitude
   */
  
  public static double getMaxAltitude() {
    return MAX_ALTITUDE;
  }
  /**.
   * Getter method for minimum altitude
   *
   * @return the minimum altitude
   */
  
  public static double getMinAltitude() {
    return MIN_ALTITUDE;
  }
  /* Getter method section END */
}
