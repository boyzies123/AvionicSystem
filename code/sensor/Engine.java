package code.sensor;

public class Engine extends Sensor {
    private double currThrust;
    private double fuelFlow;
    public Engine(double currentThrust, double fuelFlow){
        super(1000);
        this.currThrust = currentThrust;
        this.fuelFlow = fuelFlow;
    }
    /**
     * Getter method for fuel flow
     * @return
     */
    public double getFuelFlow(){
        return this.fuelFlow;
    }
    public double getCurrThrust(){
        return this.currThrust;
    }
    public void setFuelFlow(double fuelFlow){
        this.fuelFlow = fuelFlow;
    }
    public void setCurrThrust(double thrust){
        this.currThrust = thrust;
    }
}
