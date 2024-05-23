package sensor;
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
    public SensorData sendSensorData(){
        return new SensorData(this.currPitch, this.currRoll, this.currYaw);
    }
    // @Override
    // public void monitor() {
    //     throw new UnsupportedOperationException("Unimplemented method 'monitor'");
    // }
    // @Override
    // public void reportError(String err) {
    //     throw new UnsupportedOperationException("Unimplemented method 'reportError'");
    // }
   

   

    public double getCurrPitch(){
        return this.currPitch;
    }
    public double getCurrRoll(){
        return this.currRoll;
    }
    public double getCurrYaw(){
        return this.currYaw;
    }
    public static double getMaxYaw(){
        return MAX_YAW;
    }
    public static double getMaxRoll(){
        return MAX_ROLL;
    }
    public static double getMaxPitch(){
        return MAX_PITCH;
    }
    public static double getMinYaw(){
        return MIN_YAW;
    }
    public static double getMinRoll(){
        return MIN_ROLL;
    }
    public static double getMinPitch(){
        return MIN_PITCH;
    }
    
    public void setPitch(double pitch){
        this.currPitch = pitch;
    }
    public void setRoll(double roll){
        this.currRoll = roll;
    }
    public void setYaw(double yaw){
        this.currYaw = yaw;
    }
    
}
