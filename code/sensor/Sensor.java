/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 13/05/2024
 */

package code.sensor;

/**.
 * Class of default sensor, contains the 
 * update frequency, a common attribute 
 * shared amongst sensors
 */
public class Sensor {
  private long updateFreq;
  /**.
   * Constructor of sensor. Takes in the update frequency
   */
  
  public Sensor(long updateFreq) {
    this.updateFreq = updateFreq;
  }
  /**.
   * Getter update frequency of sensor
   *
   * @return the update frequency of this sensor
   */
  
  public long getUpdateFrequency() {
    return this.updateFreq;
  }
}

