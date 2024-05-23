package autopilot;
/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 23/05/2024
 */
public class ControlSignal {
    private double newElevatorPosition;
    private double newAileronPosition;
    private double newRudderPosition;
    private double newThrustOnePosition;
    private double newFuelFlowOne;
    private double newThrustTwoPosition;
    private double newFuelFlowTwo;
    public ControlSignal(double newAileronPosition, double newRudderPosition, double newElevatorPosition){
        this.newAileronPosition = newAileronPosition;
        this.newRudderPosition = newRudderPosition;
        this.newElevatorPosition = newElevatorPosition;
    }
    public ControlSignal(double newThrustOne, double newFuelFlowOne, double newThrustTwo, double newFuelFlowTwo){
        this.newThrustOnePosition = newThrustOne;
        this.newFuelFlowOne = newFuelFlowOne;
        this.newThrustTwoPosition = newThrustTwo;
        this.newFuelFlowTwo = newFuelFlowTwo;

    }
    /**
    * Gets how much elevator should be adjusted by
    * @return adjustment value of elevator
    */
    public double getElevatorPosition(){
        return this.newElevatorPosition;
    }
    /**
    * Gets how much aileron should be adjusted by
    * @return adjustment value of aileron
    */
    public double getAileronPosition(){
        return this.newAileronPosition;
    }
    /**
    * Gets how much rudder should be adjusted by
    * @return adjustment value of rudder
    */
    public double getRudderPosition(){
        return this.newRudderPosition;
    }
    /**
    * Gets how much thrust should be adjusted by for the first engine
    * @return adjustment value of thrust for engine one
    */
    public double getThrustOnePosition(){
        return this.newThrustOnePosition;
    }
    /**
    * Gets how much fuel flow should be adjusted by for the first engine
    * @return adjustment value of fuel flow for engine one
    */
    public double getFuelFlowOne(){
        return this.newFuelFlowOne;
    }
    /**
    * Gets how much thrust should be adjusted by for the second engine
    * @return adjustment value of thrust for engine two
    */
    public double getThrustTwoPosition(){
        return this.newThrustTwoPosition;
    }
    /**
    * Gets how much fuel flow should be adjusted by for the second engine
    * @return adjustment value of fuel flow for engine two
    */
    public double getFuelFlowTwo(){
        return this.newFuelFlowTwo;
    }
}
