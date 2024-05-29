/*
 * Code made by: Harry Booth-Beach, with modifications by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 16/05/2024
 */

package code.sensor;

/**.
 * Class of sensor data, contains relevant information of sensors
 */
public class SensorData {
  private double altitude;
  private double pitch;
  private double roll;
  private double yaw;
  private double airspeed;
  private double fuelFlow1;
  private double fuelFlow2;
  private double thrust1;
  private double thrust2;
  private double altitudeGps;
  private double altitudeBarometric;

  public SensorData() {
  }
  /**.
   * SensorData constructor for sensor data produced by attitude sensor
   */
  
  public SensorData(double pitch, double roll, double yaw) {
    this.roll = roll;
    this.yaw = yaw;
    this.pitch = pitch;
  }
  /**.
   * SensorData constructor for sensor data for engine parameters
   */
  
  public SensorData(double fuelFlow1, double thrust1, double fuelFlow2, double thrust2) {
    this.fuelFlow1 = fuelFlow1;
    this.thrust1 = thrust1;
    this.fuelFlow2 = fuelFlow2;
    this.thrust2 = thrust2;
  }
    
  /* Setter method section START */

  /**.
   * Setter method for altitude
   *
   * @param altitude the altitude to be set
   */
  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }

  /**.
   * Setter method for pitch
   *
   * @param pitch the pitch to be set
   */
  public void setPitch(double pitch) {
    this.pitch = pitch;
  }

  /**.
   * Setter method for roll
   *
   * @param roll the roll to be set
   */
  public void setRoll(double roll) {
    this.roll = roll;
  }

  /**.
   * Setter method for yaw
   *
   * @param yaw the yaw to be set
   */
  public void setYaw(double yaw) {
    this.yaw = yaw;
  }

  /**.
   * Setter method for air speed
   *
   * @param airspeed the airspeed to be set
   */
  public void setAirspeed(double airspeed) {
    this.airspeed = airspeed;
  }

  /**.
   * Setter method for fuel flow of engine 1
   *
   * @param fuelFlow1 the fuel flow to be set for engine 1
   */
  public void setfuelFlow1(double fuelFlow1) {
    this.fuelFlow1 = fuelFlow1;
  }
  /**.
   * Setter method for thrust of engine 1
   *
   * @param thrust1 the trust to be set for engine 1
   */
  
  public void setThrust1(double thrust1) {
    this.thrust1 = thrust1;
  }
  
  /**.
   * Setter method for fuel flow of engine 2
   *
   * @param fuelFlow2 the fuel flow to be set for engine 2
   */
  public void setFuelFlow2(double fuelFlow2) {
    this.fuelFlow2 = fuelFlow2;
  }

  /**.
   * Setter method for thrust of engine 2
   *
   * @param thrust2 the thrust to be set for engine 2
   */
  public void setThrust2(double thrust2) {
    this.thrust2 = thrust2;
  }
  /**.
   * Setter method for altitude GPS
   *
   * @param altitudeGps the altitude GPS to be set 
   */
  
  public void setAltitudeGps(double altitudeGps) {
    this.altitudeGps = altitudeGps;
  }

  /**.
   * Setter method for altitude barometric
   *
   * @param altitudeBarometric the altitude in barometric to be set
   */
  public void setAltitudeBarometric(double altitudeBarometric) {
    this.altitudeBarometric = altitudeBarometric;
  }

  /* Setter method section END */

  /* Getter method section START */

  /**.
   * Getter method for alutitude
   *
   * @return the altitude reported by sensor
   */
  
  public double getAltitude() {
    return this.altitude;
  }

  /**.
   * Getter method for pitch
   *
   * @return the pitch reported by sensor
   */
  public double getPitch() {
    return this.pitch;
  }

  /**.
   * Getter method for yaw
   *
   * @return the yaw reported by sensor
   */
  public double getYaw() {
    return this.yaw;
  }

  /**.
   * Getter method for roll
   *
   * @return the roll reported by sensor
   */
  
  public double getRoll() {
    return this.roll;
  }

  /**.
   * Getter method for air speed
   *
   * @return the airspeed reported by sensor
   */
  public double getAirspeed() {
    return this.airspeed;
  }

  /**.
   * Getter method for thrust of engine 1
   *
   * @return the thrust of first engine reported by engine
   */
  public double getThrust1() {
    return this.thrust1;
  }
  
  /**.
   * Getter method for thrust of engine 2
   *
   * @return the thrust of second engine reported by engine
   */
  
  public double getThrust2() {
    return this.thrust2;
  }
  
  /**.
   * Getter method for fuel flow of engine 1
   *
   * @return the fuel flow of first engine reported by engine
   */
  
  public double getFuelFlow1() {
    return this.fuelFlow1;
  }
  /**.
   * Getter method for fuel flow of engine 2
   *
   * @return the fuel flow of second engine reported by engine
   */
  
  public double getFuelFlow2() {
    return this.fuelFlow2;
  }
  
  /**.
   * Getter method for altitude GPS
   *
   * @return the altitude in GPS
   */
  public double getAltitudeGps() {
    return this.altitudeGps;
  }

  /**.
   * Getter method for altitude in barometric
   *
   * @return the altitude in barometric
   */
  public double getAltitudeBarometric() {
    return this.altitudeBarometric;
  }


  /* Getter method section END */
}
