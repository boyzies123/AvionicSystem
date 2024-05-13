# Test requirements for all related systems

Testing requires us to test all possible cases to ensure system handles each case as it should:
- boundary tests to measure the borderline cases (both correct and incorrect)
- correct and incorrect cases to measure validity
- invalid cases to measure error handling

If more methods and system requirements are created through our creation and improvement of the system, more cases may need to be tested compaired to what is here already. These should be added as necessary.

## Sensor

### Ensure every method is handling data correctly and can deal with incorrect data:

Engine, AirspeedSensor, Sensor, AltitudeSensor, AttitudeSensor:
- sendStatus()
- recieveStatus()
- monitor()

SensorData:
- getPitch()
- getYaw()
- getRoll()
- getAirspeed()
- getAltitude()
- getEnginePerameters()
- updateData()

SensorDataDisplay:
- updateFrequencyIndecator()
- displayAirspeed()
- displayAltitude()
- displayPitch()
- displayRoll()
- displayYaw()
- displayEnginePerameters()
- colorChange()
- blink()

### Ensure Connecting systems are receving and delivering data correctly:

AirspeedSensor, AltitudeSensor, AttitudeSensor:
- opperating on a 2oo3 redundancy architecture and is functioning correctly

SensorDataDisplay:
- update indecators are opperating at correct intervals

## Auto Pilot

### Ensure every method is handling data correctly and can deal with incorrect data:

AutoPilotSystem:
- recieveSensorData()
- sendControlSignals()
- verifyExecution()
- resendControlSignal()
- alertUserInterface()

AutoPilorControlPanel:
- engageAutoPilot()
- disengageAutoPilot()
- adjustAltitude()
- adjustSpeed()
- adjustHeading()
- getAutoPilotStatus()
- displayIndecatorLights()

ControlSurface:
- setElevatorPosition()
- setAileronPosition()
- setRubberPosition()
- updateFromSensorData()
- executeControlSignal()

### Ensure Connecting systems are receving and delivering data correctly:

Control Signal:
- confirm recieved control signal within 200 milliseconds
- +-2% for control surface margin of error
- +-5% for engine margin of error
- reflects this change within 1 second of issuance
- The system should attempt this up to 3 times before alerting the UI of the issue

Control Frequency:
- confirm sending control signals every 500 milliseconds

## UI

### Ensure every method is handling data correctly and can deal with incorrect data:

Checklist:
- addItem()
- removeItem()
- clear()
- markItemAsDone()
- isItemDone()
- getAllItems()

HazardAlerts:
- displayChecklist()
- issueHazardAlert()
- triggerAudibleAlert()
- visualAlert()

Waypoint:
- getLongitude()
- getLatitude()
- getAltitude()

FlightPlanManagement:
- enterWaypoint()
- enterSpeedRestrictions()
- enterArivalTime()
- loadFlightPlan()
- displayMap()

### Ensure Connecting systems are receving and delivering data correctly:

Things are being displayed on the UI:
- display checklist
- visual alert
- audible alert
- hazard alerts
- waypoints 
- arival time
- airspeed
- pitch
- roll
- yaw
- engine perameters
- update indecators

