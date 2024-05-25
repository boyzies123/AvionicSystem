package code.autopilot;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import code.sensor.SensorData;
import code.src.Waypoint;
import code.ui.HazardAlertsDisplay;
import code.ui.FlightPlanManagement;
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
    private double altitude = 0;
    private List <Waypoint> waypoints = FlightPlanManagement.getWaypoints();
    private double currentLatitude = 0; //y
    private double currentLongitude = 0; //x
    //For testing purposes to simulate incorrect data
    private boolean simulation = false;
    private boolean alertSent = false;
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
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(this::sendControlSignal, 0, this.CONTROL_FREQUENCY, TimeUnit.MILLISECONDS);

    }
    /**
     * Starts the autopilot system.
     */
    public void start(){
        this.engage = true;
    }
    /**
     * Stops the autopilot system.
     */
    public void stop(){
        this.engage = false;
    }
    /**
     * Sets whether autopilot is active or not. For redundancy purposes.
     * @param status the status of autopilot to be set
     */
    public void setStatus(boolean status){
        this.active = status;
    }
    /**  
     * Send control signals to the aircraft’s control
     * surfaces (elevators, ailerons, rudders) and engine control systems
     */
    public void sendControlSignal(){
        //Not engaged. Do not send control signals
        if (!this.engage || !this.active){
            return;
        }

        //Calculate control signal values based on simulated attitude data
        SensorData currentOrientation = this.controlSurface.sendSensorData();
        //Calculate the desired adjustments
        double desiredPitch = calculateDesiredPitch();
        double desiredRoll = calculateDesiredRoll();
        double desiredYaw = calculateDesiredYaw(currentOrientation.getYaw());
        double desiredThrustOne = calculateThrust();
        double desiredFuelFlowOne = calculateFuelFlow();
        double desiredThrustTwo = calculateThrust();
        double desiredFuelFlowTwo = calculateFuelFlow();
        this.waypoints.remove(0);
        //Original values of control surfaces. For health detection checks
        double originalAileronPosition = this.controlSurface.getAileronPosition();
        double originalElevatorPosition = this.controlSurface.getElevatorPosition();
        double originalRudderPosition = this.controlSurface.getRudderPosition();
        //Currently temp values for control signal values
        ControlSignal csToControlSurface = new ControlSignal(desiredRoll, desiredPitch, desiredYaw);
        ControlSignal csToEngineControlSystem = new ControlSignal(desiredThrustOne, desiredFuelFlowOne, desiredThrustTwo, desiredFuelFlowTwo);
        this.controlSurface.executeControlSignal(csToControlSurface);
        this.engineControlSystem.executeControlSignal(csToEngineControlSystem);
        //Check health of this autopilot system and determine the need for replacement.
        healthCheck(desiredPitch, desiredRoll, desiredYaw, originalAileronPosition, originalElevatorPosition, originalRudderPosition);
        this.executor2 = Executors.newSingleThreadScheduledExecutor();
        this.executor2.scheduleAtFixedRate(this::checkExecution, 0, 200, TimeUnit.MILLISECONDS);
    }
    /** 
     * Reads back sensor data and checks if execution was successful.
     * reflects the expected change within a margin of error of 
     * ±2% for the control surfaces and ±5% for engine parameters
     */
    public void checkExecution(){
        int MAX_COUNT = 5; // Total iterations
        // Call the method you want to run every 200ms here
        boolean success = verifyExecution(this.controlSurface.sendSensorData(), this.engineControlSystem.sendSensorData());
        this.count++;
        if (this.simulation){
            success = verifyExecution(this.controlSurface.sendSensorData(4000, 4000, 4000), this.engineControlSystem.sendSensorData());
        }
        if (success){
            this.retries = 0;
            this.executor2.shutdown();
            this.alertSent = true;
        }
        //check whether execution was sucessful.
        //if not, continue checking up to 3 times
        if (this.count == MAX_COUNT) { 
            this.count = 0;
            this.retries++;
            // 3 attempts reached. Alert the pilot via the user interface.
            if (this.retries == this.MAX_RETRIES){
                this.retries = 0;
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
        boolean elevatorCompare = Math.abs(sensorDataControlSurface.getPitch()-this.controlSurface.getElevatorPosition())<=this.MARGIN_OF_ERROR_CONTROL_SURFACES*this.controlSurface.getElevatorPosition();
        boolean aileronCompare = Math.abs(sensorDataControlSurface.getRoll()-this.controlSurface.getAileronPosition())<=this.MARGIN_OF_ERROR_CONTROL_SURFACES*this.controlSurface.getAileronPosition();
        boolean rudderCompare = Math.abs(sensorDataControlSurface.getYaw()-this.controlSurface.getRudderPosition())<=this.MARGIN_OF_ERROR_CONTROL_SURFACES*this.controlSurface.getRudderPosition();
        boolean fuelFlowOneCompare = Math.abs(sensorDataEngineControl.getFuelFlow1()-this.engineControlSystem.getFuelFlowOne()) <=this.MARGIN_OF_ERROR_ENGINE_PARAMETERS*this.engineControlSystem.getFuelFlowOne();
        boolean thrustOneCompare = Math.abs(sensorDataEngineControl.getThrust1()-this.engineControlSystem.getThrustOne())<=this.MARGIN_OF_ERROR_ENGINE_PARAMETERS*this.engineControlSystem.getThrustOne();
        boolean fuelFlowTwoCompare = Math.abs(sensorDataEngineControl.getFuelFlow2()-this.engineControlSystem.getFuelFlowTwo()) <=this.MARGIN_OF_ERROR_ENGINE_PARAMETERS*this.engineControlSystem.getFuelFlowTwo();
        boolean thrustTwoCompare = Math.abs(sensorDataEngineControl.getThrust2()-this.engineControlSystem.getThrustTwo())<=this.MARGIN_OF_ERROR_ENGINE_PARAMETERS*this.engineControlSystem.getThrustTwo();
        boolean success = elevatorCompare && aileronCompare && rudderCompare && fuelFlowOneCompare && thrustOneCompare && fuelFlowTwoCompare && thrustTwoCompare;
        if (success) {
            this.retries = 0;
            return true; // Exit if successful
        }
        return false;
    }
    /**
     * Calculate the pitch adjustment
     * @return the value of pitch that should be adjusted by
     */
    private double calculateDesiredPitch() {
        if (this.waypoints.isEmpty()) {
            return 0; // No change if no waypoints left
        }
        //Assuming waypoints are in order
        Waypoint w = this.waypoints.get(0); // Get the next waypoint
        double altiudeDifference = this.altitude-w.getAltitude();
        return altiudeDifference * 0.05; 
    }
    /**
     * Calculate the roll adjustment
     * @param currentRoll current roll reported by the attitude sensor
     * @return the value of roll that should be adjusted by
     */
    private double calculateDesiredRoll() {
        if (this.waypoints.isEmpty()) {
            return 0; // No change if no waypoints left
        }
        double desiredYaw = calculateDesiredYaw(this.controlSurface.sendSensorData().getYaw());
        return desiredYaw * 0.1; 
    }
    /**
     * Calculate the yaw adjustment
     * @param currentYaw current yaw reported by the attitude sensor
     * @return the value of yaw that should be adjusted by
     */
    private double calculateDesiredYaw(double currentYaw) {
        // Assuming waypoints are in order
        if (this.waypoints.isEmpty()) {
            return 0; // No change if no waypoints left
        }
        Waypoint w = this.waypoints.get(0); // Get the next waypoint
        double deltaLongitude = w.getxPos() - this.currentLongitude;
        double deltaLatitude = w.getyPos() - this.currentLatitude;

        // Calculate the desired heading
        double desiredHeading = Math.toDegrees(Math.atan2(deltaLongitude, deltaLatitude));

        // Normalize desired heading to [0, 360) degrees
        if (desiredHeading < 0) {
            desiredHeading += 360;
        }

        // Calculate the yaw adjustment needed to align with desired heading
        double desiredYaw = desiredHeading - currentYaw;

        // Normalize yaw adjustment to [-180, 180) degrees
        if (desiredYaw > 180) {
            desiredYaw -= 360;
        } else if (desiredYaw < -180) {
            desiredYaw += 360;
        }  

        return desiredYaw;
    }
    /**
     * Calculate the fuel flow adjustment
     * @return the value of thust that should be adjusted by
     */
    private double calculateThrust() {
        if (this.waypoints.isEmpty()) {
            return 0; // No change if no waypoints left
        }
        //Assuming waypoints are in order
        Waypoint w = this.waypoints.remove(0);
        return w.getSpeedRestriction();

    }
    /**
     * Calculate the fuel flow adjustment
     * @return the value of fuel flow that should be adjusted by
     */
    private double calculateFuelFlow() {
        if (this.waypoints.isEmpty()) {
            return 0; // No change if no waypoints left
        }
        double desiredThrust = calculateThrust();
        return desiredThrust * 0.1; 
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
        if (this.controlSurface.getElevatorPosition() - (desiredPitch + originalElevatorPosition) > tolerance ||
        this.controlSurface.getAileronPosition() - (desiredRoll + originalAileronPosition) > tolerance ||
        this.controlSurface.getRudderPosition() - (desiredYaw + originalRudderPosition) > tolerance){
            this.errorInAutoPilot = true;

        }
    }
    /**
     * Checks status of error existing in auto pilot.
     * @return error status
     */
    public boolean errorInAutoPilot(){
        return this.errorInAutoPilot;
    }
    /**
     * Checks whether this autopilot is active
     * @return active status
     */
    public boolean checkActive(){
        return this.active;
    }
    /**
     * Alerts the user interface in case of control signal failure.
     */
    public void alertUserInterface(){
        HazardAlertsDisplay.issueHazardAlert();
        System.out.println("Control signal failure"); //$NON-NLS-1$
    }
    /**
     * Checks whether autopilot is currently engaged
     * @return whether autopilot is engaged or not.
     */
    public boolean checkEngaged(){
        return this.engage;
    }
    /**
     * Receives the current altitude, the average of GPS and Barometric altitude
     * @param altitude1 the current altitude
     */
    public void receiveAltitude(double altitude1){
        this.altitude = altitude1;
    }
    /**
     * Gets whether alert has been sent
     * @return whether alert has been sent
     */
    public boolean getAlertSent(){
        return this.alertSent;
    }
}
