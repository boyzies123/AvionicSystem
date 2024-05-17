/*
 * Code made by: Harry Booth-Beach
 * Date created: 17/05/2024
 * Date modified: 17/05/2024
 */
package code.sensor;

public AttitudeSensor {
    private int updateFreq;
    private double currPitch;
    private double currRoll;
    private double currYaw;
    private double MAX_PITCH;
    private double MIN_PITCH;
    private double MAX_ROLL;
    private double MIN_ROLL;
    private double MAX_YAW;
    private double MIN_YAW;
    private int attitudeSensorID;

    /**
     * Sends data to SensorData class
     * @param sD
     */
    public void sendSensorData(SensorData sD) {
        sD.setPitch(currPitch);
        sD.setRoll(currRoll);
        sD.setYaw(currYaw);
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

    /**
     * Returns true if yaw is valid
     * @return
     */
    public boolean checkYaw() {
        return currYaw <= MAX_YAW && currYaw >= MIN_YAW;
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

    /* Getter method section END */
}