package autopilot;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sensor.SensorData;
import ui.HazardAlertsDisplay;
/*
 * Code made by: Yi Chen
 * Date created: 13/05/2024
 * Date modified: 21/05/2024
 */
public class AutoPilotSystem {
    private ScheduledExecutorService executor;
    private ScheduledExecutorService executor2;
    private ControlSurface controlSurface;
    private EngineControlSystem engineControlSystem;
    private final long CONTROL_FREQUENCY = 500; // milliseconds
    private final double MARGIN_OF_ERROR_CONTROL_SURFACES = 0.02;
    private final double MARGIN_OF_ERROR_ENGINE_PARAMETERS = 0.05;
    private final int MAX_RETRIES = 3;
    //whether autopilot is enaged or not
    private boolean engage = false;
    //Whether the current autopilot is the main one running
    private boolean active;
    private boolean errorInAutoPilot = false;
    private int retries = 0;
    //counts number of times checkExecution has been called.
    private int count = 0;
    /**
     * Creates an AutoPilotSystem object with a given control surface.
     * 
     * @param controlSurface The control surface of this aircraft where autopilot will send 
     * control signals to.
     */
    public AutoPilotSystem(ControlSurface controlSurface, EngineControlSystem engineControlSystem, boolean active){
        this.active = active;
        this.controlSurface = controlSurface;
        this.engineControlSystem = engineControlSystem;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::sendControlSignal, 0, CONTROL_FREQUENCY, TimeUnit.MILLISECONDS);
    }
    /**
     * Starts the autopilot system.
     */
    public void start(){
        engage = true;
    }
    /**
     * Stops the autopilot system.
     */
    public void stop(){
        engage = false;
    }
    /**
     * Sets whether autopilot is active or not. For redundancy purposes.
     * @param status the status of autopilot to be set
     */
    public void setStatus(boolean status){
        active = status;
    }
    /**  
     * Send control signals to the aircraft’s control
     * surfaces (elevators, ailerons, rudders) and engine control systems
     */
    public void sendControlSignal(){
        //Not engaged. Do not send control signals
        if (!engage || !active){
            return;
        }
        //Calculate control signal values based on simulated attitude data
        SensorData currentOrientation = controlSurface.sendSensorData();
        //Calculate the desired adjustments
        double desiredPitch = calculateDesiredPitch(currentOrientation.getPitch());
        double desiredRoll = calculateDesiredRoll(currentOrientation.getRoll());
        double desiredYaw = calculateDesiredYaw(currentOrientation.getYaw());
        double desiredThrustOne = calculateThrust(currentOrientation.getPitch());
        double desiredFuelFlowOne = calculateFuelFlow(currentOrientation.getPitch());
        double desiredThrustTwo = calculateThrust(currentOrientation.getPitch());
        double desiredFuelFlowTwo = calculateFuelFlow(currentOrientation.getPitch());
        //Original values of control surfaces. For health detection checks
        double originalAileronPosition = controlSurface.getAileronPosition();
        double originalElevatorPosition = controlSurface.getElevatorPosition();
        double originalRudderPosition = controlSurface.getRudderPosition();
        //Currently temp values for control signal values
        ControlSignal csToControlSurface = new ControlSignal(desiredRoll, desiredPitch, desiredYaw);
        ControlSignal csToEngineControlSystem = new ControlSignal(desiredThrustOne, desiredFuelFlowOne, desiredThrustTwo, desiredFuelFlowTwo);
        controlSurface.executeControlSignal(csToControlSurface);
        engineControlSystem.executeControlSignal(csToEngineControlSystem);
        //Check health of this autopilot system and determine the need for replacement.
        healthCheck(desiredPitch, desiredRoll, desiredYaw, originalAileronPosition, originalElevatorPosition, originalRudderPosition);
        executor2 = Executors.newSingleThreadScheduledExecutor();
        executor2.scheduleAtFixedRate(this::checkExecution, 0, 200, TimeUnit.MILLISECONDS);
    }
    /** 
     * Reads back sensor data and checks if execution was successful.
     * reflects the expected change within a margin of error of 
     * ±2% for the control surfaces and ±5% for engine parameters
     */
    public void checkExecution(){
        int MAX_COUNT = 5; // Total iterations
        // Call the method you want to run every 200ms here
        boolean success = verifyExecution(controlSurface.sendSensorData(), engineControlSystem.sendSensorData());
        count++;
        if (success){
            retries = 0;
            executor2.shutdown();
        }
        //check whether execution was sucessful.
        //if not, continue checking up to 3 times
        if (count == MAX_COUNT) { 
            count = 0;
            retries++;
            // 3 attempts reached. Alert the pilot via the user interface.
            if (retries == MAX_RETRIES){
                retries = 0;
                alertUserInterface();
            }
        } 

    }    

    /** 
     * Verify execution of control signal by checking sensor data and whether
     * change is reflected.
     * @param sensorDataControlSurface Sensor data from the control surface.
     * @param sensorDataEngineControl Sensor data from the engine control system.
     * @return true if the execution is successful, false otherwise.
     */
    
    public boolean verifyExecution(SensorData sensorDataControlSurface, SensorData sensorDataEngineControl){
        boolean elevatorCompare = Math.abs(sensorDataControlSurface.getPitch()-controlSurface.getElevatorPosition())<=MARGIN_OF_ERROR_CONTROL_SURFACES*controlSurface.getElevatorPosition();
        boolean aileronCompare = Math.abs(sensorDataControlSurface.getRoll()-controlSurface.getAileronPosition())<=MARGIN_OF_ERROR_CONTROL_SURFACES*controlSurface.getAileronPosition();
        boolean rudderCompare = Math.abs(sensorDataControlSurface.getYaw()-controlSurface.getRudderPosition())<=MARGIN_OF_ERROR_CONTROL_SURFACES*controlSurface.getRudderPosition();
        boolean fuelFlowOneCompare = Math.abs(sensorDataEngineControl.getFuelFlow1()-engineControlSystem.getFuelFlowOne()) <=MARGIN_OF_ERROR_ENGINE_PARAMETERS*engineControlSystem.getFuelFlowOne();
        boolean thrustOneCompare = Math.abs(sensorDataEngineControl.getThrust1()-engineControlSystem.getThrustOne())<=MARGIN_OF_ERROR_ENGINE_PARAMETERS*engineControlSystem.getThrustOne();
        boolean fuelFlowTwoCompare = Math.abs(sensorDataEngineControl.getFuelFlow2()-engineControlSystem.getFuelFlowTwo()) <=MARGIN_OF_ERROR_ENGINE_PARAMETERS*engineControlSystem.getFuelFlowTwo();
        boolean thrustTwoCompare = Math.abs(sensorDataEngineControl.getThrust2()-engineControlSystem.getThrustTwo())<=MARGIN_OF_ERROR_ENGINE_PARAMETERS*engineControlSystem.getThrustTwo();
        boolean success = elevatorCompare && aileronCompare && rudderCompare && fuelFlowOneCompare && thrustOneCompare && fuelFlowTwoCompare && thrustTwoCompare;
        if (success) {
            retries = 0;
            return true; // Exit if successful
        }
        return false;
    }
    /**
     * Calculate the pitch adjustment
     * @param currentPitch current pitch reported by the attitude sensor
     * @return the value of pitch that should be adjusted by
     */
    private double calculateDesiredPitch(double currentPitch) {
        // Example logic to determine the desired pitch adjustment
        return currentPitch * 0.1; 
    }
    /**
     * Calculate the roll adjustment
     * @param currentRoll current roll reported by the attitude sensor
     * @return the value of roll that should be adjusted by
     */
    private double calculateDesiredRoll(double currentRoll) {
        // Example logic to determine the desired roll adjustment
        return currentRoll * 0.05; 
    }
    /**
     * Calculate the yaw adjustment
     * @param currentYaw current yaw reported by the attitude sensor
     * @return the value of yaw that should be adjusted by
     */
    private double calculateDesiredYaw(double currentYaw) {
        // Example logic to determine the desired yaw adjustment
        return currentYaw * 0.02; 
    }
    /**
     * Calculate the fuel flow adjustment
     * @param currentPitch current pitch reported by the attitude sensor
     * @return the value of thust that should be adjusted by
     */
    private double calculateThrust(double currentPitch) {
        // Example logic to calculate thrust
        return currentPitch * 0.1;
    }
    /**
     * Calculate the fuel flow adjustment
     * @param currentPitch current pitch reported by the attitude sensor
     * @return the value of fuel flow that should be adjusted by
     */
    private double calculateFuelFlow(double currentPitch) {
        // Example logic to calculate fuel flow
        return currentPitch * 0.05;
    }
    /**
     * Checks health of autopilot system and determine whether it needs to be switched
     * to redundant autopilot.
     * For simplicity, the criteria for switching will be when expected change 
     * sent from control signals to control surface is not changed.
     * @param desiredPitch the amount elevator should be adjusted by
     * @param desiredRoll the amount aileron should be adjusted by
     * @param desiredYaw the amount rudder should be adjusted b
     * @param originalAileronPosition the original position of aileron
     * @param originalElevatorPosition the originl position of elevator
     * @param originalRudderPosition the original position of rudder
     * @return the value of fuel flow that should be adjusted by
     */
    public void healthCheck(double desiredPitch, double desiredRoll, double desiredYaw, double originalAileronPosition, double originalElevatorPosition, double originalRudderPosition){
        double tolerance = 0.1;
        if (controlSurface.getElevatorPosition() - (desiredPitch + originalElevatorPosition) > tolerance ||
        controlSurface.getAileronPosition() - (desiredRoll + originalAileronPosition) > tolerance ||
        controlSurface.getRudderPosition() - (desiredYaw + originalRudderPosition) > tolerance){
            errorInAutoPilot = true;

        }
    }
    /**
     * Checks status of error existing in auto pilot.
     * @return error status
     */
    public boolean errorInAutoPilot(){
        return errorInAutoPilot;
    }
    /**
     * Checks whether this autopilot is active
     * @return active status
     */
    public boolean checkActive(){
        return active;
    }
    /**
     * Alerts the user interface in case of control signal failure.
     */
    public void alertUserInterface(){
        HazardAlertsDisplay.issueHazardAlert();
        System.out.println("Control signal failure");
    }
    /**
     * Checks whether autopilot is currently engaged
     * @return whether autopilot is engaged or not.
     */
    public boolean checkEngaged(){
        return engage;
    }
}
