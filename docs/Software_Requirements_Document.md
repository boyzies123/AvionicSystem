**SWEN326 -- Avionics Monitoring System Requirements**

**Pilot User Interface**

**Functional Requirements**

• R1: Input field for entering waypoints (latitude, longitude,
altitude).

• R2: Input fields for speed restrictions and expected times of arrival
at each waypoint.

• R3: A submit button to load and activate the flight plan.

• R4: Visual display (map) showing current position, planned route, and
waypoints.

The UI should allow its users (the pilots of the aircraft) to:

-   R5: Engage autopilot when at cruising altitude, and disengage it for
    landing.

-   R6: Manually override autopilot.

-   R7: Respond quickly and appropriately to any hazards.

The UI should contain:

-   R8: Indicator lights for autopilot status (engaged, disengaged,
    fault condition).

-   R9: Controls for manual override: altitude adjustment, speed,
    heading.

-   R10: Digital readouts for airspeed, altitude, pitch, roll, yaw, and
    engine parameters.

-   R11: Visual indicators for data update frequency (e.g., colour
    change or blinking to indicate fresh data).

**Non-functional Requirements**

• R12: Interface should be responsive to pilot input and button presses.

**Safety Requirements**

-   R13: The UI should have a dedicated section for hazard warnings and
    mitigation actions -- these actions must be providing as soon as
    possible after the initial hazard warning has been raised -- ideally
    concurrently. These will be in the form of audible and visual alerts
    for immediate hazards, and a checklist or action plan for emergency
    procedures.

**Aircraft Sensors**

**Functional Requirements**

• R14: Attitude Sensor should measure pitch (nose up/down), roll (wing
up/down), and yaw (nose left/right).

• R15: The data format for orientation data should be of degrees from
the horizon for pitch and roll and magnetic heading for yaw with an
update frequency of every 500 milliseconds.

• R16: Operational ranges of orientation data: Pitch: -30° to 30°, Roll:
-60° to 60°, Yaw:

-180° to 180°. Identify and manage exceedances through system alerts and
corrective actions.

• R17: Altitude Sensor should provide barometric and gps data.

• R18: Altitude sensor data should be in the format of AMSL with update
frequency of every 500 milliseconds.

• R19: Altitude sensor should be capable of measuring altitude within
-1000 to 50000 feet AMSL.

• R20: The airspeed sensor should be able represent the aircraft's speed
relative to the surrounding air and be in knots.

• R21: The airspeed sensor should also be able to represent aircraft
speed between 50 and 500 knots and have update frequency of every
second.

• R22: Engine parameters should measure engine thrust and fuel flow in
data format of thrust in pounds-force (lbf) and should have update
frequency of every second.

**Non-functional Requirements**

• R23: The system must prioritize safety with redundancy included for
sensors.

• R24: The system shall handle sensor errors and unexpected events
without crashing.

**Safety Requirements**

• R25: Hazard Mitigation Strategies: Implement fault detection, fault
tolerance, and fail-safe mechanisms to handle abnormal sensor values,
ensuring the system can safely manage potential hazards without
catastrophic failures.

• R26: Sensors including airspeed sensor, altitude sensor, and attitude
sensor should operate in a 2oo3 architecture, providing backup in the
case of a sensor failing.

**Control Signals**

**Functional Requirements**

• R27: Execution Check Parameters: After sending a control signal, the
system must verify the execution by reading back the relevant sensor
data.

• R28: For the Autopilot Control Frequency, the system should send
control signals to the aircraft's control surfaces (elevators, ailerons,
rudders) and engine control systems with a frequency of at least every
500 milliseconds.

**Non-functional Requirements**

• R29: Success Criteria: A control signal is considered successfully
executed if the sensor data reflects the expected change within a margin
of error of ±2% for the control surfaces and ±5% for engine parameters,
within 1 second of command issuance.

**Safety Requirements**

• R30: Failure Handling: If the execution check fails after sending a
control signal, the system must attempt to resend the command up to
three times before alerting the pilot to the issue via the user
interface.

**Engine Thrust and Flight Dynamics**

**Functional Requirements**

• R31: The system should dynamically adjust engine thrust proportional
to airspeed.

• R32: System should accurately model the effects of thrust adjustments
on altitude changes

• R33: System should model how changes in thrust affect the aircraft\'s
pitch, and in scenarios of asymmetric thrust, its roll and yaw.

**Non-functional Requirements**

• R34: The maximum thrust will be 374 kN per engine.

• R35: The minimum thrust will be 10kN per engine.

• R36: The cruise thrust will be 56.1kN per engine.

**Autopilot system**

**Functional Requirements**

•R37: Integration of autopilot system with sensors to gather data.

**Non-Functional Requirements**

**Safety Requirements**

• R38: Autopilot system should incorporate redundancy in the case of
software bugs.

• R39: The system shall ensure continuous and reliable communication
between the autopilot system and critical components, such as the engine
control system, to prevent loss of control over throttle settings or
other vital parameters.
