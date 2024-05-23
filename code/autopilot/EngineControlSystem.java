package autopilot;


import sensor.SensorData;
import sensor.Engine;
/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 23/05/2024
 */
public class EngineControlSystem {
    //thrust and fuel flow of engine one
    private double thrustOne;
    private double fuelFlowOne;
    //thrust and fuel flow of engine two
    private double thrustTwo;
    private double fuelFlowTwo;
    private Engine [] engines;
    private SensorData sensorData;
    /**
    * Creates an engine control system object
    */
    public EngineControlSystem(Engine e1, Engine e2){
        this.engines[0] = e1;
        this.engines[1] = e2;
    }
    /**
    * Execute control signal sent from autopilot control
    * @param signal The control signal that shall be executed
    */
    public void executeControlSignal(ControlSignal signal){
        this.thrustOne = this.thrustOne + signal.getThrustOnePosition();
        this.fuelFlowOne = this.fuelFlowOne + signal.getFuelFlowOne();
        this.thrustTwo = this.thrustTwo + signal.getThrustTwoPosition();
        this.fuelFlowTwo = this.fuelFlowTwo + signal.getFuelFlowTwo();
        this.engines[0].setCurrThrust(this.thrustOne);
        this.engines[0].setFuelFlow(this.fuelFlowOne);
        this.engines[1].setCurrThrust(this.thrustTwo);
        this.engines[1].setFuelFlow(this.fuelFlowTwo);
    }
    /**
     * Obtain sensor data from engine
     */
    public void updateFromSensorData(){
        this.sensorData = new SensorData(this.engines[0].getCurrThrust(), this.engines[0].getFuelFlow(), this.engines[1].getCurrThrust(), this.engines[1].getFuelFlow());
        
    }
    /**
     * Send sensor data to autopilot system to verify execution of control signals
     * @return SensorData obtained from the engines.
     */
    public SensorData sendSensorData(){
        updateFromSensorData();
        return this.sensorData;
    }
    /**
     * Get current thrust of engine one
     * @return current thrust of engine one
     */
    public double getThrustOne(){
        return this.thrustOne;
    }
    /**
     * Get fuel flow of engine one 
     * @return current fuel flow of engine one
     */
    public double getFuelFlowOne(){
        return this.fuelFlowOne;
    }
    /**
     * Get current thrust of engine two
     * @return current thrust of engine two
     */
    public double getThrustTwo(){
        return this.thrustTwo;
    }
    /**
     * Get fuel flow of engine two
     * @return current fuel flow of engine two
     */
    public double getFuelFlowTwo(){
        return this.fuelFlowTwo;
    }
}
