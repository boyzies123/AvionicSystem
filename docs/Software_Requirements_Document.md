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

-   R5: Load and activate flight plans.

```{=html}
<!-- -->
```
-   R6: View the aircraft's current position, its planned route, and the
    waypoints of the flight plan, on a map.

-   R7: Engage autopilot when at cruising altitude, and disengage it for
    landing.

-   R8: Manually override autopilot.

-   R9: Respond quickly and appropriately to any hazards.

**Non-functional Requirements**

The UI should contain:

-   R10: Buttons and input fields to enter required flight plan
    information, to load and activate the entered plan, and to
    engage/disengage autopilot.

-   R11: Indicator lights for autopilot status (engaged, disengaged,
    fault condition).

-   R12: Controls for manual override: altitude adjustment, speed,
    heading.

-   R13: Digital readouts for airspeed, altitude, pitch, roll, yaw, and
    engine parameters.

-   R14: Visual indicators for data update frequency (e.g., colour
    change or blinking to indicate fresh data).

**Safety Requirements**

-   R15: The UI should have a dedicated section for hazard warnings and
    mitigation actions -- these actions must be providing as soon as
    possible after the initial hazard warning has been raised -- ideally
    concurrently. These will be in the form of audible and visual alerts
    for immediate hazards, and a checklist or action plan for emergency
    procedures.

**Aircraft Sensors**

**Functional Requirements**

• R16: Attitude Sensor: The system should simulate aircrafts orientation
data including pitch (nose up/down), roll (wing up/down), and yaw (nose
left/right) and should be in data format of degrees from the horizon for
pitch and roll and magnetic heading for yaw with an update frequency of
every 500 milliseconds. Simulate orientation data within typical
operational ranges: Pitch: -30° to 30°, Roll: -60° to 60°, Yaw: -180° to
180°. Identify and manage exceedances through system alerts and
corrective actions.

• R17: Altitude Sensor: should simulate barometric and gps data using
altitude sensor and should be in the format of AMSL. Simulate barometric
and GPS altitude data from -1,000 to 50,000 feet AMSL, with thresholds
for rapid changes indicating potential sensor faults.

• R18: The airspeed sensor should simulate data represent the aircraft's
speed relative to the surrounding air and be in knots.

• R19: The airspeed sensor should also represent aircraft speed between
50 and 500 knots. Define operational thresholds and conditions under
which values exceed normal operational limits.

• R20: The system should simulate engine thrust and fuel flow in data
format of thrust in pounds-force (lbf).

**Non-functional Requirements**

• R21: The system must prioritize safety with redundancy included for
sensors.

• R22: The system shall handle sensor errors and unexpected events
without crashing.

• R23: Altitude Sensor should have update frequency of every 500
milliseconds.

• R24: Attitude Sensor should have update frequency of every 500
milliseconds

• R25: Airspeed Sensor should have update frequency of every second.

• R26: Engine parameters should have update frequency of every second.

**Safety Requirements**

• R27: Hazard Mitigation Strategies: Implement fault detection, fault
tolerance, and fail-safe mechanisms to handle abnormal sensor values,
ensuring the system can safely manage potential hazards without
catastrophic failures.

• R28: Sensors including airspeed sensor, altitude sensor, and attitude
sensor should operate in a 2oo3 architecture, providing backup in the
case of a sensor failing.

**Control Signals**

**Functional Requirements**

• R29: Execution Check Parameters: After sending a control signal, the
system must verify the execution by reading back the relevant sensor
data.

• R30: For the Autopilot Control Frequency, the system should send
control signals to the aircraft's control surfaces (elevators, ailerons,
rudders) and engine control systems.

**Non-functional Requirements**

• R31: For the Autopilot Control Frequency, the system should send
control signals to the aircraft's control surfaces (elevators, ailerons,
rudders) and engine control systems with a frequency of at least every
500 milliseconds.

• R32: For the Execution Check Parameters, after sending a control
signal, the system must verify the execution by reading back the
relevant sensor data.

• R33: Success Criteria: A control signal is considered successfully
executed if the sensor data reflects the expected change within a margin
of error of ±2% for the control surfaces and ±5% for engine parameters,
within 1 second of command issuance.

**Safety Requirements**

• R34: Failure Handling: If the execution check fails after sending a
control signal, the system must attempt to resend the command up to
three times before alerting the pilot to the issue via the user
interface.

**Engine Thrust and Flight Dynamics**

**Functional Requirements**

• R35: Simulation should dynamically respond to changes in thrust to
accurately simulate changes in airspeed, assuming that air density and
drag do not change.

• R36: Simulation should accurately model the effects of thrust
adjustments on altitude changes.

• R37: Simulation should model how changes in thrust affect the
aircraft\'s pitch, and in scenarios of asymmetric thrust, its roll and
yaw.

**Non-functional Requirements**

• R38: The maximum thrust will be 374 kN per engine.

• R39: The minimum thrust will be 10kN per engine.

• R40: The cruise thrust will be 56.1kN per engine.

**Autopilot system**

**Functional Requirements**

•R41: Integration with sensors to gather data

**Non-Functional Requirements**

**Safety Requirements**

•R42:Reliability in handling sensor data.

• R43 Autopilot system should incorporate redundancy in this case of
software bugs.

• R44 There should be redundant communication channels between autopilot
system.
