/*
 * Code made by: Harry Booth-Beach, with modifications by: Yi Chen
 * Date created: 17/05/2024
 * Date modified: 19/05/2024
 */
package code.sensor;

public AttitudeSensor implements Sensor{
    private int updateFreq;
    private double currPitch;
    private double currRoll;
    private double currYaw;
    private static double MAX_PITCH = 30;
    private static double MIN_PITCH = -30;
    private static double MAX_ROLL = 60;
    private static double MIN_ROLL = -60;
    private static double MAX_YAW = 180;
    private static double MIN_YAW = -180;
    private int attitudeSensorID;

    public AttitudeSensor(double currentPitch, double currentYaw, double currentRoll){
        currPitch = currentPitch;
        currYaw = currentYaw;
        currRoll = currentRoll;
    }
    /**
     * Sends data to SensorData class
     */
    @Override
    public SensorData sendSensorData(){
        return new SensorData(currPitch, currRoll, currYaw);
    }

    /**
     * Returns true if pitch is valid
     * @return
     */
    public boolean checkPitch() {
        return currPitch <= MAX_PITCH && currPitch >= MIN_PITCH;
    }

    /**
     * Returns true if roll is valid
     * @return
     */
    public boolean checkRoll() {
        return currRoll <= MAX_ROLL && currRoll >= MIN_ROLL;
    }

    

    /* Setter method section START */

    /**
     * Setter method for current pitch
     * @param pitch
     */
    public void setCurrPitch(double pitch) {
        this.currPitch = pitch;
    }

    /**
     * Setter method for current roll
     * @param roll
     */
    public void setCurrRoll(double roll) {
        this.currRoll = roll;
    }

    /**
     * Setter method for current yaw
     * @param yaw
     */
    public void setCurrYaw(double yaw) {
        this.currYaw = yaw;
    }

    /* Setter method section END */

    /* Getter method section START */

    /**
     * Getter method for current pitch
     * @return
     */
    public double getCurrPitch() {
        return currPitch;
    }

    /**
     * Getter method for current roll
     * @return
     */
    public double getCurrRoll() {
        return currRoll;
    }

    /**
     * Getter method for current yaw
     * @return
     */
    public double getCurrYaw() {
        return currYaw;
    }
    /**
     * Getter method for the max yaw
     * @return
     */
    public static double getMaxYaw(){
        return MAX_YAW;
    }
    /**
     * Getter method for the max roll
     * @return
     */
    public static double getMaxRoll(){
        return MAX_YAW;
    }
    /**
     * Getter method for the max pitch
     * @return
     */
    public static double getMaxPitch(){
        return MAX_PITCH;
    }
    /**
     * Getter method for the min yaw
     * @return
     */
    public static double getMinYaw(){
        return MIN_YAW;
    }
    /**
     * Getter method for the min roll
     * @return
     */
    public static double getMinRoll(){
        return MIN_ROLL;
    }
    /**
     * Getter method for the min pitch
     * @return
     */
    public static double getMinPitch(){
        return MIN_PITCH;
    }

    /* Getter method section END */
}