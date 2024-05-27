package code.core;
import code.sensor.AirspeedSensor;
import code.sensor.AltitudeSensor;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;
import code.sensor.Sensor;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import code.autopilot.AutoPilotSystem;
import code.autopilot.ControlSurface;
import code.autopilot.EngineControlSystem;

import java.util.ArrayList;

import code.ui.AutopilotControlPanel;

import code.ui.PilotUserInterface;
import code.ui.SensorDataDisplay;
/*
 * Code made by: Yi Chen
 * Date created: 17/05/2024
 * Date modified: 25/05/2024
 */
public class CoreSystem {
    private ScheduledExecutorService executor;
    private AutoPilotSystem autoPilotSystem;
    private AutoPilotSystem backupAutoPilotSystem;
    private List <Sensor> attitudeSensors;
    private List <Sensor> altitudeSensors;
    private List <Sensor> airspeedSensors;
    private Engine [] engines = new Engine[2];
    private ControlSurface controlSurfaceOne;
    private ControlSurface controlSurfaceTwo;
    private static List <String> faults = new ArrayList <>();
   /**
    * Creates a core system object
    * This avionic system is based of the Airbus A320.
    */
    public CoreSystem(){
        //Note: this intialization will result in an error and doesn't
        //comply with rule 9 of the power of 10 rules
        //This is beacuse of the way pilotuserinterface was designed, as 
        //all components of the pilotuserinterface is intialized in the constructor.
    	new PilotUserInterface();
        this.attitudeSensors = new ArrayList <>();
        this.altitudeSensors = new ArrayList <>();
        this.airspeedSensors = new ArrayList <>();
        //A320 has two engines.
        for (int i = 0; i < 2; i++){
            this.engines[i] = new Engine(0, 0);
        }
        for (int i = 0; i < 3; i++){

            this.attitudeSensors.add(new AttitudeSensor(0, 0, 0));
            this.airspeedSensors.add(new AirspeedSensor());
            this.altitudeSensors.add(new AltitudeSensor(0, 0));
        }
        this.controlSurfaceOne = new ControlSurface(this.attitudeSensors.get(0), this.attitudeSensors.get(1), this.attitudeSensors.get(2));
        this.controlSurfaceTwo = new ControlSurface(this.attitudeSensors.get(0), this.attitudeSensors.get(1), this.attitudeSensors.get(2));
        this.autoPilotSystem = new AutoPilotSystem(this.controlSurfaceOne, new EngineControlSystem(this.engines[0], this.engines[1]), true);
        this.backupAutoPilotSystem = new AutoPilotSystem(this.controlSurfaceTwo, new EngineControlSystem(this.engines[0], this.engines[1]),false);
        AutopilotControlPanel.addAutoPilotSystem(this.autoPilotSystem, this.backupAutoPilotSystem);
    }   
    /**
     * Starts the whole system, and intializes many of the components.
     */
    public void start() {
        
        //Make sure that fault detection and checking error in autopilot will be running
        //and also sensor updates should happen as specified in the handout.
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(this::run, 0, 1000, TimeUnit.MILLISECONDS);
        this.executor.scheduleAtFixedRate(this::scheduleAirspeedSensorUpdate, 0, this.airspeedSensors.get(0).getUpdateFrequency(), TimeUnit.MILLISECONDS);
        this.executor.scheduleAtFixedRate(this::scheduleAttitudeSensorUpdate, 0, this.attitudeSensors.get(0).getUpdateFrequency(), TimeUnit.MILLISECONDS);
        this.executor.scheduleAtFixedRate(this::scheduleAltitudeSensorUpdate, 0, this.altitudeSensors.get(0).getUpdateFrequency(), TimeUnit.MILLISECONDS);
        this.executor.scheduleAtFixedRate(this::scheduleEngineParameterUpdate, 0, 1000, TimeUnit.MILLISECONDS);
    }
   /**
    * Continously runs the detectfault method
    */
    public void run(){
        checkErrorInAutoPilot();
        detectFault(); 
        this.autoPilotSystem.receiveAltitude(((AltitudeSensor)this.altitudeSensors.get(0)).getCombinedAltitude());

        
    }
    /**
    * Checks for error in autopilot and switch autopilot if error does exist.
    */
    public void checkErrorInAutoPilot(){
        if (this.autoPilotSystem.checkActive()){
            if (this.autoPilotSystem.errorInAutoPilot()){
                this.autoPilotSystem.setStatus(false);
                this.backupAutoPilotSystem.setStatus(true);
            }
        }
        else if (this.backupAutoPilotSystem.checkActive()){
            if (this.backupAutoPilotSystem.errorInAutoPilot()){
                this.backupAutoPilotSystem.setStatus(false);
                this.autoPilotSystem.setStatus(true);
            }
        }
    }
   /**
    * Since sensors operate in 2oo3, a voting shall be done which helps detect fault
    * @param v1 the value of first sensor
    * @param v2 the value of second sensor
    * @param v3 the value of third sensor
    * @return value of majority vote. If all three returns different vote, -9999 is returned
    */
    public static double twoOutOfThreeVote(double v1, double v2, double v3){
        if (v1 == v2 || v1 == v3){
            return v1;
        }
        else if (v2 == v3){
            return v2;
        }
        else{
            return -9999;
        }
    }
    
   /**
    * Detects faults of sensors by doing 2oo3 vote from getSensorValues,
    * checking also its in valid range.
    */
    public void detectFault(){
        double airspeed = twoOutOfThreeVote(((AirspeedSensor)this.airspeedSensors.get(0)).getCurrentAirspeed(), ((AirspeedSensor)this.airspeedSensors.get(1)).getCurrentAirspeed(), ((AirspeedSensor)this.airspeedSensors.get(2)).getCurrentAirspeed());
        double altitude = twoOutOfThreeVote(((AltitudeSensor)this.attitudeSensors.get(0)).getCombinedAltitude(), ((AltitudeSensor)this.attitudeSensors.get(1)).getCombinedAltitude(), ((AltitudeSensor)this.attitudeSensors.get(2)).getCombinedAltitude());
        double pitch = twoOutOfThreeVote(((AttitudeSensor)this.attitudeSensors.get(0)).getCurrPitch(), ((AttitudeSensor)this.attitudeSensors.get(1)).getCurrPitch(), ((AttitudeSensor)this.attitudeSensors.get(2)).getCurrPitch());
        double roll = twoOutOfThreeVote(((AttitudeSensor)this.attitudeSensors.get(0)).getCurrRoll(), ((AttitudeSensor)this.attitudeSensors.get(1)).getCurrRoll(), ((AttitudeSensor)this.attitudeSensors.get(2)).getCurrRoll());
        double yaw = twoOutOfThreeVote(((AttitudeSensor)this.attitudeSensors.get(0)).getCurrYaw(), ((AttitudeSensor)this.attitudeSensors.get(1)).getCurrYaw(), ((AttitudeSensor)this.attitudeSensors.get(2)).getCurrYaw());
        boolean airspeedFault = airspeed == -9999 || airspeed < AirspeedSensor.getMinSpeed() || airspeed > AirspeedSensor.getMaxSpeed();
        boolean altitudeFault = altitude == -9999 || altitude < AltitudeSensor.getMinAltitude() || altitude > AltitudeSensor.getMaxAltitude();
        boolean pitchFault = pitch == -9999 || pitch < AttitudeSensor.getMinPitch() || pitch > AttitudeSensor.getMaxPitch();
        boolean rollFault = roll == -9999 || roll < AttitudeSensor.getMinRoll() || roll > AttitudeSensor.getMaxRoll();
        boolean yawFault = yaw == -9999 || yaw < AttitudeSensor.getMinYaw() || yaw > AttitudeSensor.getMaxYaw();
        
        if (airspeedFault || altitudeFault || pitchFault || rollFault || yawFault) {
            handleSensorFaults(airspeedFault, altitudeFault, pitchFault, rollFault, yawFault);
        } 
        displaySensorValues(airspeed, altitude, pitch, roll, yaw);
    }
   /**
    * Check all faults and notify operator and active fail safe mode
    * @param airspeedFault true if airspeed fault
    * @param altitudeFault true if altitude fault
    * @param pitchFault true if pitch fault
    * @param rollFault true if roll fault
    * @param yawFault true if yaw fault
    */
    public static void handleSensorFaults(boolean airspeedFault, boolean altitudeFault, boolean pitchFault, boolean rollFault, boolean yawFault) {
        faults = new ArrayList <>();
        if (airspeedFault) {
           faults.add("Airspeed sensor fault detected."); //$NON-NLS-1$
        }
        if (altitudeFault) {
            faults.add("Altitude sensor fault detected."); //$NON-NLS-1$
        }
        if (pitchFault || rollFault || yawFault) {
            faults.add("Attitude sensor fault detected."); //$NON-NLS-1$
        }
        
        notifyPilot(faults);
    }
   /**
    * Notify pilot of the error 
    */
    public static void notifyPilot(List <String> faults){
        for (String fault : faults){
            System.out.println(fault);
        }
        //Notify pilot via hazardalertsdisplay.
        //Won't do anting because issuehazardalert did not end up getting implemented.
        //and fault wont be able to be passed to pilot
        
    }
    //Note all values are currently 0. When we start simulation, proper values are provided.
    /**
    * Schedule updates of altitude sensor
    */
    public void scheduleAltitudeSensorUpdate(){
        for (Sensor sensor : this.altitudeSensors) {
            ((AltitudeSensor)sensor).setCurrAltitudeBarometric(0);
            ((AltitudeSensor)sensor).setCurrAltitudeGPS(0);
        }
    }
    /**
    * Schedule updates of attitude sensor
    */
    public void scheduleAttitudeSensorUpdate(){
        for (Sensor sensor : this.attitudeSensors){
            ((AttitudeSensor)sensor).setYaw(0);
            ((AttitudeSensor)sensor).setRoll(0);
            ((AttitudeSensor)sensor).setRoll(0);
        }
    }
    /**
    * Schedule updates of airspeed sensor
    */
    public void scheduleAirspeedSensorUpdate(){
        for (Sensor sensor : this.airspeedSensors){
            ((AirspeedSensor)sensor).setCurrentAirspeed(0);
        }
    }
    /**
    * Schedule updates of engine parameters
    */
    public void scheduleEngineParameterUpdate(){
        for (Engine e : this.engines){
            e.setCurrThrust(0);
            e.setFuelFlow(0);
        }
    }
   /**
    * Display the sensor based on the current sensor value to the UI
    * @param airspeed the current airspeed
    * @param altitude the current altitude
    * @param pitch the current pitch
    * @param roll the current roll
    * @param yaw the current yaw
    */
    public void displaySensorValues(double airspeed, double altitude, double pitch, double roll, double yaw){
        SensorDataDisplay.updateAirspeed(airspeed);
        SensorDataDisplay.updateAltitude(altitude);
        SensorDataDisplay.updatePitch(pitch);
        SensorDataDisplay.updateRoll(roll);
        SensorDataDisplay.updateYaw(yaw);
        SensorDataDisplay.updateThrust1(this.engines[0].getCurrThrust());
        SensorDataDisplay.updateThrust2(this.engines[1].getCurrThrust());
        SensorDataDisplay.updateFuel1(this.engines[0].getFuelFlow());
        SensorDataDisplay.updateFuel2(this.engines[1].getFuelFlow());

    }
    /**
    * Gets all altitude sensors of this system
    * @param List<Sensor> list of altitude sensors
    */
    public List <Sensor> getAltitudeSensors(){
        return this.altitudeSensors;
    }
    /**
    * Gets all airspeed sensors of this system
    * @param List<Sensor> list of airspeed sensors
    */
    public List <Sensor> getAirspeedSensors(){
        return this.airspeedSensors;
    }
    /**
    * Gets all airspeed sensors of this system
    * @param List<Sensor> list of attitude sensors
    */
    public List <Sensor> getAttitudeSensors(){
        return this.attitudeSensors;
    }
    /**
    * Gets all engines of this system
    * @param Engine[] list of engines
    */
    public Engine[] getEngines(){
        return this.engines;
    }
    /**
    * Gets the aircrafts autopilot system 
    * @return autopilot system
    */
    public AutoPilotSystem getAutoPilotSystem(){
        return this.autoPilotSystem;
    }
    /**
    * Gets the aircrafts backup autopilot system 
    * @return backup autopilot system
    */
    public AutoPilotSystem getBackUpAutoPilotSystem(){
        return this.backupAutoPilotSystem;
    }

    /**
     * Gets the faults of the system
     * @return the faults
     */
    public static List <String> getFaults(){
        return faults;
    }

    /**
     * Sets the altitude sensors of this system
     * @param altitudeSensors the altitude sensors
     */
    public void setAltitudeSensors(List <Sensor> altitudeSensors){
        this.altitudeSensors = altitudeSensors;
    }

    /**
     * Sets the airspeed sensors of this system
     * @param airspeedSensors the airspeed sensors
     */
    public void setAirspeedSensors(List <Sensor> airspeedSensors){
        this.airspeedSensors = airspeedSensors;
    }

    /**
     * Sets the attitude sensors of this system
     * @param attitudeSensors the attitude sensors
     */
    public void setAttitudeSensors(List <Sensor> attitudeSensors){
        this.attitudeSensors = attitudeSensors;
    }

    /**
     * Sets the engines of this system
     * @param engines the engines
     */
    public void setEngines(Engine[] engines){
        this.engines = engines;
    }

    /**
     * Sets the autopilot system of this system
     * @param autoPilotSystem the autopilot system
     */
    public void setAutoPilotSystem(AutoPilotSystem autoPilotSystem){
        this.autoPilotSystem = autoPilotSystem;
    }

    /**
     * Sets the backup autopilot system of this system
     * @param backupAutoPilotSystem the backup autopilot system
     */
    public void setBackupAutoPilotSystem(AutoPilotSystem backupAutoPilotSystem){
        this.backupAutoPilotSystem = backupAutoPilotSystem;
    }

    public static void main(String [] args){
        CoreSystem coreSystem = new CoreSystem();
        coreSystem.start();

    }
}
