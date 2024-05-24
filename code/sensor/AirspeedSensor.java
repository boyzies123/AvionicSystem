package code.sensor;
/*
 * Code made by: Harry Booth-Beach, with modifications by Yi Chen
 * Date created: 13/05/2024
 * Date modified: (if modified)
 */

public class AirspeedSensor extends Sensor{
    private double currentAirspeed;
    private static double MIN_SPEED = 50;
    private static double MAX_SPEED = 500;
    public AirspeedSensor() {
    	super(1000);
        this.currentAirspeed = MIN_SPEED;
    }

    public double getCurrentAirspeed(){
        return this.currentAirspeed;
    }
    public static double getMaxSpeed(){
        return MAX_SPEED;
    }
    public static double getMinSpeed(){
        return MIN_SPEED;
    }
    public void setCurrentAirspeed(double currentAirspeed){
        this.currentAirspeed = currentAirspeed;
    }
   
    public SensorData sendSensorData(){
        SensorData s = new SensorData();
        s.setAirspeed(this.currentAirspeed);
        return s;
    }
}
