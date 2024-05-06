# SWEN326 – Avionics Monitoring System Requirements

## Pilot User Interface

### Functional Requirements

The UI should allow its users (the pilots of the aircraft) to:
-	Load and activate flight plans.
-	View the aircraft’s current position, its planned route, and the waypoints of the flight plan, on a map.
-	Engage autopilot when at cruising altitude, and disengage it for landing.
-	Manually override autopilot.
-	Respond quickly and appropriately to any hazards.


### Non-functional Requirements

The UI should contain:
-	Buttons and input fields to enter required flight plan information, to load and activate the entered plan, and to engage/disengage autopilot.
-	Indicator lights for autopilot status (engaged, disengaged, fault condition).
-	Controls for manual override: altitude adjustment, speed, heading.
-	Digital readouts for airspeed, altitude, pitch, roll, yaw, and engine parameters.
-	Visual indicators for data update frequency (e.g., colour change or blinking to indicate fresh data).

### Safety Requirements

-	The UI should have a dedicated section for hazard warnings and mitigation actions – these actions must be providing as soon as possible after the initial hazard warning has been raised – ideally concurrently. These will be in the form of audible and visual alerts for immediate hazards, and a checklist or action plan for emergency procedures.

## Aircraft Sensors 

### Functional Requirements

- Attitude Sensor: The system should simulate aircrafts orientation data including pitch (nose up/down), roll (wing up/down), and yaw (nose left/right) and should be in data format of degrees from the horizon for pitch and roll and magnetic heading for yaw with an update frequency of every 500 milliseconds. Simulate orientation data within typical operational ranges: Pitch: -30° to 30°, Roll: -60° to 60°, Yaw: -180° to 180°. Identify and manage exceedances through system alerts and corrective actions.
- Altitude Sensor: should simulate barometric and gps data using altitude sensor and should be in the format of AMSL. Simulate barometric and GPS altitude data from -1,000 to 50,000 feet AMSL, with thresholds for rapid changes indicating potential sensor faults.
- The airspeed sensor should simulate data represent the aircraft’s speed relative to the surrounding air and be in knots. 
- The airspeed sensor should also represent aircraft speed between 50 and 500 knots. Define operational thresholds and conditions under which values exceed normal operational limits.
- The system should simulate engine thrust and fuel flow in data format of thrust in pounds-force (lbf).

### Non-functional Requirements
- The system must prioritize safety with redundancy included for sensors.
- The system shall handle sensor errors and unexpected events without crashing.
- Altitude Sensor should have update frequency of every 500 milliseconds.
- Attitude Sensor should have update frequency of every 500 milliseconds
- Airspeed Sensor should have update frequency of every second.
- Engine parameters should have update frequency of every second.



### Safety Requirements
- Hazard Mitigation Strategies: Implement fault detection, fault tolerance, and fail-safe mechanisms to handle abnormal sensor values, ensuring the system can safely manage potential hazards without catastrophic failures.
- Sensors including airspeed sensor, altitude sensor, and attitude sensor should operate in a 2oo3 architecture, providing backup in the case of a sensor failing.

## Control Signals

### Functional Requirements

- Execution Check Parameters: After sending a control signal, the system must verify the execution by reading back the relevant sensor data.
- For the Autopilot Control Frequency, the system should send control signals to the aircraft’s control surfaces (elevators, ailerons, rudders) and engine control systems.

### Non-functional Requirements

- For the Autopilot Control Frequency, the system should send control signals to the aircraft’s control surfaces (elevators, ailerons, rudders) and engine control systems with a frequency of at least every 500 milliseconds.
- For the Execution Check Parameters, after sending a control signal, the system must verify the execution by reading back the relevant sensor data.
- Success Criteria: A control signal is considered successfully executed if the sensor data reflects the expected change within a margin of error of ±2% for the control surfaces and ±5% for engine parameters, within 1 second of command issuance.

### Safety Requirements

- Failure Handling: If the execution check fails after sending a control signal, the system must attempt to resend the command up to three times before alerting the pilot to the issue via the user interface.


## Engine Thrust and Flight Dynamics

### Functional Requirements

- Simulation should dynamically respond to changes in thrust to accurately simulate changes in airspeed, assuming that air density and drag do not change.
- Simulation should accurately model the effects of thrust adjustments on altitude changes.
- Simulation should model how changes in thrust affect the aircraft's pitch, and in scenarios of asymmetric thrust, its roll and yaw.

### Non-functional Requirements

- The maximum thrust will be 374 kN per engine.
- The minimum thrust will be 10kN per engine.
- The cruise thrust will be 56.1kN per engine.

### Safety Requirements
