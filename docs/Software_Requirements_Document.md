**SWEN326 – Avionics Monitoring System Requirements**

**Pilot User Interface**

**Functional Requirements**

• R1: Input field for entering waypoints (latitude, longitude, altitude). 

• R2: Input fields for speed restrictions and expected times of arrival at each waypoint.

• R3: A submit button to load and activate the flight plan.

• R4: Visual display (map) showing current position, planned route, and waypoints.

The UI should allow its users (the pilots of the aircraft) to: 

- R5: Engage autopilot when at cruising altitude, and disengage it for landing.
- R6: Manually override autopilot.
- R7: Respond quickly and appropriately to any hazards.

The UI should contain:

- R8: Indicator lights for autopilot status (engaged, disengaged, fault condition).
- R9: Controls for manual override: altitude adjustment, speed, heading.
- R10: Digital readouts for airspeed, altitude, pitch, roll, yaw, and engine parameters for the two engines.
- R11: Visual indicators for data update frequency (e.g., colour change or blinking to indicate fresh data).

**Non-functional Requirements**

• R12: Interface should be responsive to pilot input and button presses.

**Safety Requirements**

•R13:  The UI should have a dedicated section for hazard warnings and mitigation actions – these actions must be providing as soon as possible after the initial hazard warning has been raised – ideally concurrently.

•R14: Interface should include a checklist or action plan for emergency procedures.

•R15: Audible and visual alerts should be provided for immediate hazards.

**Aircraft Sensors** 

**Functional Requirements**

• R16: Attitude Sensor should measure pitch (nose up/down), roll (wing up/down), and yaw (nose left/right). 

• R17: The data format for orientation data should be of degrees from the horizon for pitch and roll and magnetic heading for yaw with an update frequency of every 500 milliseconds. 

• R18: Operational ranges of orientation data: Pitch: -30° to 30°, Roll: -60° to 60°, Yaw: 

-180° to 180°. Identify and manage exceedances through system alerts and corrective actions.

• R19: Altitude Sensor should provide barometric and gps data.

• R20Altitude sensor data should be in the format of AMSL with update frequency of every 500 milliseconds.

• R21: Altitude sensor should be capable of measuring altitude within -1000 to 50000 feet AMSL.

• R22: The airspeed sensor should be able represent the aircraft’s speed relative to the surrounding air and be in knots. 

• R23: The airspeed sensor should also be able to represent aircraft speed between 50 and 500 knots and have update frequency of every second.

• R24: Engine parameters should measure engine thrust and fuel flow in data format of thrust in pounds-force (lbf) and should have update frequency of every second.

**Non-functional Requirements**

• R25: The system must prioritize safety with redundancy included for sensors.

• R26: The system shall handle sensor errors and unexpected events without crashing.




**Safety Requirements**

• R27: Hazard Mitigation Strategies: Implement fault detection, fault tolerance, and fail-safe mechanisms to handle abnormal sensor values, ensuring the system can safely manage potential hazards without catastrophic failures.

• R28: Sensors including airspeed sensor, altitude sensor, and attitude sensor should operate in a 2oo3 architecture, providing backup in the case of a sensor failing.

**Control Signals**

**Functional Requirements**

•  R29: Execution Check Parameters: After sending a control signal, the system must verify the execution by reading back the relevant sensor data.

•  R30: For the Autopilot Control Frequency, the system should send control signals to the aircraft’s control surfaces (elevators, ailerons, rudders) and engine control systems with a frequency of at least every 500 milliseconds.

**Non-functional Requirements**

•  R31: Success Criteria: A control signal is considered successfully executed if the sensor data reflects the expected change within a margin of error of ±2% for the control surfaces and ±5% for engine parameters, within 1 second of command issuance.

**Safety Requirements**

• R32: Failure Handling: If the execution check fails after sending a control signal, the system must attempt to resend the command up to three times before alerting the pilot to the issue via the user interface.



**Engine Thrust and Flight Dynamics**

**Functional Requirements**

• R33: The system should dynamically adjust engine thrust proportional to airspeed.

• R34: System should accurately model the effects of thrust adjustments on altitude changes

• R35: System should model how changes in thrust affect the aircraft's pitch, and in scenarios of asymmetric thrust, its roll and yaw.

**Non-functional Requirements**

• R36: The maximum thrust will be 374 kN per engine.

• R37:  The minimum thrust will be 10kN per engine.


**Autopilot system**

**Functional Requirements**

•R38: Integration of autopilot system with sensors to gather data.

**Non-Functional Requirements**

**Safety Requirements**

• R39: Autopilot system should incorporate redundancy in the case of software bugs.

• R40: The system shall ensure continuous and reliable communication between the autopilot system and critical components, such as the engine control system, to prevent loss of control over throttle settings or other vital parameters.







