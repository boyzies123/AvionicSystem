package code.sensor;

public class Engine {
    private double currThrust;
    private double fuelFlow;
    public Engine(double currentThrust, double fuelFlow){
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
