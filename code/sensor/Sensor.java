/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 13/05/2024
 */
package sensor;

public class Sensor {
	private long updateFreq;
	public Sensor(long updateFreq) {
		this.updateFreq = updateFreq;
	}
	/**
     * Getter update frequency of sensor
     * @return
     */
    public long getUpdateFrequency() {
        return this.updateFreq;
    }
}

