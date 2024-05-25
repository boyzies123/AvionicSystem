package code.sensor;
/*
 * Code made by: Harry Booth-Beach, with modifications by Yi Chen
 * Date created: 16/05/2024
 * Date modified: (if modified)
 */
public class AttitudeSensor extends Sensor{
    private double currPitch;
    private double currRoll;
    private double currYaw;
    private static double MAX_PITCH = 30;
    private static double MIN_PITCH = -30;
    private static double MAX_ROLL = 60;
    private static double MIN_ROLL = -60;
    private static double MAX_YAW = 180;
    private static double MIN_YAW = -180;

    public AttitudeSensor(double currentPitch, double currentYaw, double currentRoll){
    	super(500);
    	this.currPitch = currentPitch;
        this.currYaw = currentYaw;
        this.currRoll = currentRoll;
        
    }
    /**
     * Sends the sensor data based on attributes of airspeed sesnsor
     * @return sensordata Sensor data produced by sensor
     */
    public SensorData sendSensorData(){
        return new SensorData(this.currPitch, this.currRoll, this.currYaw);
    }
    /**
     * Gets the current pitch reported by the attitude sesnor
     * @return the current pitch
     */
    public double getCurrPitch(){
        return this.currPitch;
    }
    /**
     * Gets the current roll reported by the attitude sesnor
     * @return the current roll
     */
    public double getCurrRoll(){
        return this.currRoll;
    }
    /**
     * Gets the current yaw reported by the attitude sesnor
     * @return the current yaw
     */
    public double getCurrYaw(){
        return this.currYaw;
    }
    /**
     * Gets the maximum yaw reported by the attitude sesnor
     * @return the maximum yaw
     */
    public static double getMaxYaw(){
        return MAX_YAW;
    }
    /**
     * Gets the maximum roll reported by the attitude sesnor
     * @return the maximum roll
     */
    public static double getMaxRoll(){
        return MAX_ROLL;
    }
    /**
     * Gets the maximum pitch reported by the attitude sesnor
     * @return the maximum pitch
     */
    public static double getMaxPitch(){
        return MAX_PITCH;
    }
    /**
     * Gets the minimum yaw reported by the attitude sesnor
     * @return the minimum yaw
     */
    public static double getMinYaw(){
        return MIN_YAW;
    }
    /**
     * Gets the minimum roll reported by the attitude sesnor
     * @return the minimum roll
     */
    public static double getMinRoll(){
        return MIN_ROLL;
    }
    /**
     * Gets the minimum pitch reported by the attitude sesnor
     * @return the minimum pitch
     */
    public static double getMinPitch(){
        return MIN_PITCH;
    }
    /**
     * Sets the pitch
     * @param pitch set the pitch for this sensor
     */
    public void setPitch(double pitch){
        this.currPitch = pitch;
    }
    /**
     * Sets the roll
     * @param roll set the roll for this sensor
     */
    public void setRoll(double roll){
        this.currRoll = roll;
    }
    /**
     * Sets the yaw
     * @param yaw set the yaw for this sensor
     */
    public void setYaw(double yaw){
        this.currYaw = yaw;
    }
    
}
