+--------+----------+----------------------------------+----------+---+
| Requi  | Req      | UML reference                    | Implem   | T |
| rement | uirement |                                  | entation | e |
| ID     | Des      |                                  | module   | s |
|        | cription |                                  |          | t |
|        |          |                                  |          | c |
|        |          |                                  |          | a |
|        |          |                                  |          | s |
|        |          |                                  |          | e |
|        |          |                                  |          | i |
|        |          |                                  |          | d |
+========+==========+==================================+==========+===+
| R1     | Input    | enterWaypoint method, waypoints  |          |   |
|        | field    | and waypointInputField           |          |   |
|        | for      | attributes in                    |          |   |
|        | entering | FlightPlanManagement class in    |          |   |
|        | w        | UML class diagram.               |          |   |
|        | aypoints |                                  |          |   |
|        | (l       |                                  |          |   |
|        | atitude, |                                  |          |   |
|        | lo       |                                  |          |   |
|        | ngitude, |                                  |          |   |
|        | al       |                                  |          |   |
|        | titude). |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R2     | Input    | enterSpeedRestriction and        |          |   |
|        | fields   | enterArrivalTime method and      |          |   |
|        | for      | speedInputField and              |          |   |
|        | speed    | timeInputField attributes in     |          |   |
|        | rest     | FlightPlanManagement class in    |          |   |
|        | rictions | UML class diagram                |          |   |
|        | and      |                                  |          |   |
|        | expected |                                  |          |   |
|        | times of |                                  |          |   |
|        | arrival  |                                  |          |   |
|        | at each  |                                  |          |   |
|        | w        |                                  |          |   |
|        | aypoint. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R3     | A submit | loadFlightPlan method and        |          |   |
|        | button   | submitButton attribute in        |          |   |
|        | to load  | FlightPlanManagement class in    |          |   |
|        | and      | UML class diagram                |          |   |
|        | activate |                                  |          |   |
|        | the      |                                  |          |   |
|        | flight   |                                  |          |   |
|        | plan.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R4     | Visual   | displayMap method in             |          |   |
|        | display  | FlightPlanManagement class in    |          |   |
|        | (map)    | UML class diagram                |          |   |
|        | showing  |                                  |          |   |
|        | current  |                                  |          |   |
|        | p        |                                  |          |   |
|        | osition, |                                  |          |   |
|        | planned  |                                  |          |   |
|        | route,   |                                  |          |   |
|        | and      |                                  |          |   |
|        | wa       |                                  |          |   |
|        | ypoints. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R5     | Engage   | engageAutoPilot and              |          |   |
|        | a        | disengageAutoPilot method in     |          |   |
|        | utopilot | AutoPilotControlPanel class in   |          |   |
|        | when at  | UML class diagram                |          |   |
|        | cruising |                                  |          |   |
|        | a        |                                  |          |   |
|        | ltitude, |                                  |          |   |
|        | and      |                                  |          |   |
|        | d        |                                  |          |   |
|        | isengage |                                  |          |   |
|        | it for   |                                  |          |   |
|        | landing. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R6     | Manually | engageAutoPilot and              |          |   |
|        | override | disengageAutoPilot method in     |          |   |
|        | au       | AutoPilotControlPanel class in   |          |   |
|        | topilot. | UML class diagram                |          |   |
+--------+----------+----------------------------------+----------+---+
| R7     | Respond  |                                  |          |   |
|        | quickly  |                                  |          |   |
|        | and      |                                  |          |   |
|        | appro    |                                  |          |   |
|        | priately |                                  |          |   |
|        | to any   |                                  |          |   |
|        | hazards. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R8     | I        | displayIndicatorLights method    |          |   |
|        | ndicator | and autoPilotStatus attribute in |          |   |
|        | lights   | AutoPilotControlPanel class in   |          |   |
|        | for      | UML class diagram                |          |   |
|        | a        |                                  |          |   |
|        | utopilot |                                  |          |   |
|        | status   |                                  |          |   |
|        | (        |                                  |          |   |
|        | engaged, |                                  |          |   |
|        | dis      |                                  |          |   |
|        | engaged, |                                  |          |   |
|        | fault    |                                  |          |   |
|        | con      |                                  |          |   |
|        | dition). |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R9     | Controls | adjustAltitude, adjustSpeed,     |          |   |
|        | for      | adjustHeading method in          |          |   |
|        | manual   | AutoPilotControlPanel class in   |          |   |
|        | o        | UML class diagram                |          |   |
|        | verride: |                                  |          |   |
|        | altitude |                                  |          |   |
|        | adj      |                                  |          |   |
|        | ustment, |                                  |          |   |
|        | speed,   |                                  |          |   |
|        | heading. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R10    | Digital  | displayAirspeed,                 |          |   |
|        | readouts | displayAltitude,displayPitch,    |          |   |
|        | for      | displayRoll, displayYaw,         |          |   |
|        | a        | displayEngineParameters method   |          |   |
|        | irspeed, | and airspeed,                    |          |   |
|        | a        | altitude,pitch,roll,yaw and      |          |   |
|        | ltitude, | engineParams attributes in       |          |   |
|        | pitch,   | SensorDataDisplay class in UML   |          |   |
|        | roll,    | class diagram                    |          |   |
|        | yaw, and |                                  |          |   |
|        | engine   |                                  |          |   |
|        | par      |                                  |          |   |
|        | ameters. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R11    | Visual   | updateAirspeed, updateAltitude,  |          |   |
|        | in       | updatePitch,                     |          |   |
|        | dicators | updateRoll,updateYaw,            |          |   |
|        | for data | updateEn                         |          |   |
|        | update   | gineParameters,displayIndicator, |          |   |
|        | f        | colorChange method in            |          |   |
|        | requency | SensorDataDisplay class in UML   |          |   |
|        | (e.g.,   | class diagram                    |          |   |
|        | colour   |                                  |          |   |
|        | change   |                                  |          |   |
|        | or       |                                  |          |   |
|        | blinking |                                  |          |   |
|        | to       |                                  |          |   |
|        | indicate |                                  |          |   |
|        | fresh    |                                  |          |   |
|        | data).   |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R12    | I        |                                  |          |   |
|        | nterface |                                  |          |   |
|        | should   |                                  |          |   |
|        | be       |                                  |          |   |
|        | re       |                                  |          |   |
|        | sponsive |                                  |          |   |
|        | to pilot |                                  |          |   |
|        | input    |                                  |          |   |
|        | and      |                                  |          |   |
|        | button   |                                  |          |   |
|        | presses. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R13    | The UI   | Hazard alert class in UML        |          |   |
|        | should   | alongside all methods and        |          |   |
|        | have a   | attributes of that class.        |          |   |
|        | d        |                                  |          |   |
|        | edicated |                                  |          |   |
|        | section  |                                  |          |   |
|        | for      |                                  |          |   |
|        | hazard   |                                  |          |   |
|        | warnings |                                  |          |   |
|        | and      |                                  |          |   |
|        | mi       |                                  |          |   |
|        | tigation |                                  |          |   |
|        | actions  |                                  |          |   |
|        | -- these |                                  |          |   |
|        | actions  |                                  |          |   |
|        | must be  |                                  |          |   |
|        | p        |                                  |          |   |
|        | roviding |                                  |          |   |
|        | as soon  |                                  |          |   |
|        | as       |                                  |          |   |
|        | possible |                                  |          |   |
|        | after    |                                  |          |   |
|        | the      |                                  |          |   |
|        | initial  |                                  |          |   |
|        | hazard   |                                  |          |   |
|        | warning  |                                  |          |   |
|        | has been |                                  |          |   |
|        | raised   |                                  |          |   |
|        | --       |                                  |          |   |
|        | ideally  |                                  |          |   |
|        | concu    |                                  |          |   |
|        | rrently. |                                  |          |   |
|        | These    |                                  |          |   |
|        | will be  |                                  |          |   |
|        | in the   |                                  |          |   |
|        | form of  |                                  |          |   |
|        | audible  |                                  |          |   |
|        | and      |                                  |          |   |
|        | visual   |                                  |          |   |
|        | alerts   |                                  |          |   |
|        | for      |                                  |          |   |
|        | i        |                                  |          |   |
|        | mmediate |                                  |          |   |
|        | hazards, |                                  |          |   |
|        | and a    |                                  |          |   |
|        | c        |                                  |          |   |
|        | hecklist |                                  |          |   |
|        | or       |                                  |          |   |
|        | action   |                                  |          |   |
|        | plan for |                                  |          |   |
|        | e        |                                  |          |   |
|        | mergency |                                  |          |   |
|        | pro      |                                  |          |   |
|        | cedures. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R14    | Attitude | AttitudeSensor class in UML with |          |   |
|        | Sensor   | the getters and setter methods   |          |   |
|        | should   | within the class.                |          |   |
|        | measure  |                                  |          |   |
|        | pitch    |                                  |          |   |
|        | (nose    |                                  |          |   |
|        | u        |                                  |          |   |
|        | p/down), |                                  |          |   |
|        | roll     |                                  |          |   |
|        | (wing    |                                  |          |   |
|        | u        |                                  |          |   |
|        | p/down), |                                  |          |   |
|        | and yaw  |                                  |          |   |
|        | (nose    |                                  |          |   |
|        | left     |                                  |          |   |
|        | /right). |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R15    | The data | AttitudeSensor class in UML with |          |   |
|        | format   | update frequency attribute.      |          |   |
|        | for      |                                  |          |   |
|        | ori      |                                  |          |   |
|        | entation |                                  |          |   |
|        | data     |                                  |          |   |
|        | should   |                                  |          |   |
|        | be of    |                                  |          |   |
|        | degrees  |                                  |          |   |
|        | from the |                                  |          |   |
|        | horizon  |                                  |          |   |
|        | for      |                                  |          |   |
|        | pitch    |                                  |          |   |
|        | and roll |                                  |          |   |
|        | and      |                                  |          |   |
|        | magnetic |                                  |          |   |
|        | heading  |                                  |          |   |
|        | for yaw  |                                  |          |   |
|        | with an  |                                  |          |   |
|        | update   |                                  |          |   |
|        | f        |                                  |          |   |
|        | requency |                                  |          |   |
|        | of every |                                  |          |   |
|        | 500      |                                  |          |   |
|        | milli    |                                  |          |   |
|        | seconds. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R16    | Ope      | checkValidOreintationData method |          |   |
|        | rational | in AttitudeSensor class in UML.  |          |   |
|        | ranges   |                                  |          |   |
|        | of       |                                  |          |   |
|        | ori      |                                  |          |   |
|        | entation |                                  |          |   |
|        | data:    |                                  |          |   |
|        | Pitch:   |                                  |          |   |
|        | -30° to  |                                  |          |   |
|        | 30°,     |                                  |          |   |
|        | Roll:    |                                  |          |   |
|        | -60° to  |                                  |          |   |
|        | 60°,     |                                  |          |   |
|        | Yaw:     |                                  |          |   |
|        |          |                                  |          |   |
|        | -180° to |                                  |          |   |
|        | 180°.    |                                  |          |   |
|        | Identify |                                  |          |   |
|        | and      |                                  |          |   |
|        | manage   |                                  |          |   |
|        | exc      |                                  |          |   |
|        | eedances |                                  |          |   |
|        | through  |                                  |          |   |
|        | system   |                                  |          |   |
|        | alerts   |                                  |          |   |
|        | and      |                                  |          |   |
|        | co       |                                  |          |   |
|        | rrective |                                  |          |   |
|        | actions. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R17    | Altitude | Altitudesensor class in UML with |          |   |
|        | Sensor   | the getters and setter methods   |          |   |
|        | should   | of that class.                   |          |   |
|        | provide  |                                  |          |   |
|        | ba       |                                  |          |   |
|        | rometric |                                  |          |   |
|        | and gps  |                                  |          |   |
|        | data.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R18    | Altitude | AltitudeSensor class in UML with |          |   |
|        | sensor   | update frequency attribute.      |          |   |
|        | data     |                                  |          |   |
|        | should   |                                  |          |   |
|        | be in    |                                  |          |   |
|        | the      |                                  |          |   |
|        | format   |                                  |          |   |
|        | of AMSL  |                                  |          |   |
|        | with     |                                  |          |   |
|        | update   |                                  |          |   |
|        | f        |                                  |          |   |
|        | requency |                                  |          |   |
|        | of every |                                  |          |   |
|        | 500      |                                  |          |   |
|        | milli    |                                  |          |   |
|        | seconds. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R19    | Altitude | AltitudeSensor class in UML with |          |   |
|        | sensor   | checkAltitudeWithinRange method  |          |   |
|        | should   | with attributes max_altitude and |          |   |
|        | be       | min_altitude.                    |          |   |
|        | capable  |                                  |          |   |
|        | of       |                                  |          |   |
|        | m        |                                  |          |   |
|        | easuring |                                  |          |   |
|        | altitude |                                  |          |   |
|        | within   |                                  |          |   |
|        | -1000 to |                                  |          |   |
|        | 50000    |                                  |          |   |
|        | feet     |                                  |          |   |
|        | AMSL.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R20    | The      | getCurrentAirspeed,              |          |   |
|        | airspeed | setCurrentAirspeed method and    |          |   |
|        | sensor   | currentAirspeed attribute in     |          |   |
|        | should   | airspeed sensor class UML.       |          |   |
|        | be able  |                                  |          |   |
|        | r        |                                  |          |   |
|        | epresent |                                  |          |   |
|        | the      |                                  |          |   |
|        | ai       |                                  |          |   |
|        | rcraft's |                                  |          |   |
|        | speed    |                                  |          |   |
|        | relative |                                  |          |   |
|        | to the   |                                  |          |   |
|        | sur      |                                  |          |   |
|        | rounding |                                  |          |   |
|        | air and  |                                  |          |   |
|        | be in    |                                  |          |   |
|        | knots.   |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R21    | The      | Updatefrequency attribute in     |          |   |
|        | airspeed | AirspeedSensor class and         |          |   |
|        | sensor   | checkairspeed method.            |          |   |
|        | should   |                                  |          |   |
|        | also be  |                                  |          |   |
|        | able to  |                                  |          |   |
|        | r        |                                  |          |   |
|        | epresent |                                  |          |   |
|        | aircraft |                                  |          |   |
|        | speed    |                                  |          |   |
|        | between  |                                  |          |   |
|        | 50 and   |                                  |          |   |
|        | 500      |                                  |          |   |
|        | knots    |                                  |          |   |
|        | and have |                                  |          |   |
|        | update   |                                  |          |   |
|        | f        |                                  |          |   |
|        | requency |                                  |          |   |
|        | of every |                                  |          |   |
|        | second.  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R22    | Engine   | setFuelFlow, getFuelFlow,        |          |   |
|        | pa       | getCurrentThrust,                |          |   |
|        | rameters | setCurrentThrust method and      |          |   |
|        | should   | updatefrequency attribute in     |          |   |
|        | measure  | Engine class UML.                |          |   |
|        | engine   |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | and fuel |                                  |          |   |
|        | flow in  |                                  |          |   |
|        | data     |                                  |          |   |
|        | format   |                                  |          |   |
|        | of       |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | in       |                                  |          |   |
|        | poun     |                                  |          |   |
|        | ds-force |                                  |          |   |
|        | (lbf)    |                                  |          |   |
|        | and      |                                  |          |   |
|        | should   |                                  |          |   |
|        | have     |                                  |          |   |
|        | update   |                                  |          |   |
|        | f        |                                  |          |   |
|        | requency |                                  |          |   |
|        | of every |                                  |          |   |
|        | second.  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R23    | The      | 3 to 1 relationship from the     |          |   |
|        | system   | sensors class to                 |          |   |
|        | must     | sensordatadisplay and sensordata |          |   |
|        | pr       | class in UML, alongside ids for  |          |   |
|        | ioritize | each sensor. As well as with     |          |   |
|        | safety   | core system and sensor class.    |          |   |
|        | with     |                                  |          |   |
|        | re       |                                  |          |   |
|        | dundancy |                                  |          |   |
|        | included |                                  |          |   |
|        | for      |                                  |          |   |
|        | sensors. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R24    | The      | ReportError method in sensors    |          |   |
|        | system   | and handlesystemerror in         |          |   |
|        | shall    | coresystem class for UML.        |          |   |
|        | handle   |                                  |          |   |
|        | sensor   |                                  |          |   |
|        | errors   |                                  |          |   |
|        | and      |                                  |          |   |
|        | un       |                                  |          |   |
|        | expected |                                  |          |   |
|        | events   |                                  |          |   |
|        | without  |                                  |          |   |
|        | c        |                                  |          |   |
|        | rashing. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R25    | Hazard   | detect                           |          |   |
|        | Mi       | Faults,ensureFailSafeMechanisms, |          |   |
|        | tigation | implementFaultTolerance in       |          |   |
|        | Str      | coresystem class.                |          |   |
|        | ategies: |                                  |          |   |
|        | I        |                                  |          |   |
|        | mplement |                                  |          |   |
|        | fault    |                                  |          |   |
|        | de       |                                  |          |   |
|        | tection, |                                  |          |   |
|        | fault    |                                  |          |   |
|        | to       |                                  |          |   |
|        | lerance, |                                  |          |   |
|        | and      |                                  |          |   |
|        | f        |                                  |          |   |
|        | ail-safe |                                  |          |   |
|        | me       |                                  |          |   |
|        | chanisms |                                  |          |   |
|        | to       |                                  |          |   |
|        | handle   |                                  |          |   |
|        | abnormal |                                  |          |   |
|        | sensor   |                                  |          |   |
|        | values,  |                                  |          |   |
|        | ensuring |                                  |          |   |
|        | the      |                                  |          |   |
|        | system   |                                  |          |   |
|        | can      |                                  |          |   |
|        | safely   |                                  |          |   |
|        | manage   |                                  |          |   |
|        | p        |                                  |          |   |
|        | otential |                                  |          |   |
|        | hazards  |                                  |          |   |
|        | without  |                                  |          |   |
|        | cata     |                                  |          |   |
|        | strophic |                                  |          |   |
|        | f        |                                  |          |   |
|        | ailures. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R26    | Sensors  | 3 to 1 relationship from the     |          |   |
|        | i        | sensors class to                 |          |   |
|        | ncluding | sensordatadisplay and sensordata |          |   |
|        | airspeed | class in UML, alongside ids for  |          |   |
|        | sensor,  | each sensor. As well as with     |          |   |
|        | altitude | core system and sensor class.    |          |   |
|        | sensor,  |                                  |          |   |
|        | and      |                                  |          |   |
|        | attitude |                                  |          |   |
|        | sensor   |                                  |          |   |
|        | should   |                                  |          |   |
|        | operate  |                                  |          |   |
|        | in a     |                                  |          |   |
|        | 2oo3     |                                  |          |   |
|        | archi    |                                  |          |   |
|        | tecture, |                                  |          |   |
|        | p        |                                  |          |   |
|        | roviding |                                  |          |   |
|        | backup   |                                  |          |   |
|        | in the   |                                  |          |   |
|        | case of  |                                  |          |   |
|        | a sensor |                                  |          |   |
|        | failing. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R27    | E        | Sendcontrolsignals and           |          |   |
|        | xecution | receivesensordata methods in     |          |   |
|        | Check    | autopilotsystem.                 |          |   |
|        | Par      |                                  |          |   |
|        | ameters: | sendSensorData methods for       |          |   |
|        | After    | sensors and engine parameters.   |          |   |
|        | sending  |                                  |          |   |
|        | a        |                                  |          |   |
|        | control  |                                  |          |   |
|        | signal,  |                                  |          |   |
|        | the      |                                  |          |   |
|        | system   |                                  |          |   |
|        | must     |                                  |          |   |
|        | verify   |                                  |          |   |
|        | the      |                                  |          |   |
|        | e        |                                  |          |   |
|        | xecution |                                  |          |   |
|        | by       |                                  |          |   |
|        | reading  |                                  |          |   |
|        | back the |                                  |          |   |
|        | relevant |                                  |          |   |
|        | sensor   |                                  |          |   |
|        | data.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R28    | For the  | Sendcontrolsignals method in     |          |   |
|        | A        | autopilotsystem.                 |          |   |
|        | utopilot |                                  |          |   |
|        | Control  |                                  |          |   |
|        | Fr       |                                  |          |   |
|        | equency, |                                  |          |   |
|        | the      |                                  |          |   |
|        | system   |                                  |          |   |
|        | should   |                                  |          |   |
|        | send     |                                  |          |   |
|        | control  |                                  |          |   |
|        | signals  |                                  |          |   |
|        | to the   |                                  |          |   |
|        | ai       |                                  |          |   |
|        | rcraft's |                                  |          |   |
|        | control  |                                  |          |   |
|        | surfaces |                                  |          |   |
|        | (el      |                                  |          |   |
|        | evators, |                                  |          |   |
|        | a        |                                  |          |   |
|        | ilerons, |                                  |          |   |
|        | rudders) |                                  |          |   |
|        | and      |                                  |          |   |
|        | engine   |                                  |          |   |
|        | control  |                                  |          |   |
|        | systems. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R29    | Success  | verifyExecution method in        |          |   |
|        | C        | autopilotsystem class.           |          |   |
|        | riteria: |                                  |          |   |
|        | A        |                                  |          |   |
|        | control  |                                  |          |   |
|        | signal   |                                  |          |   |
|        | is       |                                  |          |   |
|        | co       |                                  |          |   |
|        | nsidered |                                  |          |   |
|        | succ     |                                  |          |   |
|        | essfully |                                  |          |   |
|        | executed |                                  |          |   |
|        | if the   |                                  |          |   |
|        | sensor   |                                  |          |   |
|        | data     |                                  |          |   |
|        | reflects |                                  |          |   |
|        | the      |                                  |          |   |
|        | expected |                                  |          |   |
|        | change   |                                  |          |   |
|        | within a |                                  |          |   |
|        | margin   |                                  |          |   |
|        | of error |                                  |          |   |
|        | of ±2%   |                                  |          |   |
|        | for the  |                                  |          |   |
|        | control  |                                  |          |   |
|        | surfaces |                                  |          |   |
|        | and ±5%  |                                  |          |   |
|        | for      |                                  |          |   |
|        | engine   |                                  |          |   |
|        | par      |                                  |          |   |
|        | ameters, |                                  |          |   |
|        | within 1 |                                  |          |   |
|        | second   |                                  |          |   |
|        | of       |                                  |          |   |
|        | command  |                                  |          |   |
|        | i        |                                  |          |   |
|        | ssuance. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R30    | Failure  | verifyExecution,                 |          |   |
|        | H        | resendControlSignal, and         |          |   |
|        | andling: | alertUserInterface method in     |          |   |
|        | If the   | autopilotsystem class.           |          |   |
|        | e        |                                  |          |   |
|        | xecution |                                  |          |   |
|        | check    |                                  |          |   |
|        | fails    |                                  |          |   |
|        | after    |                                  |          |   |
|        | sending  |                                  |          |   |
|        | a        |                                  |          |   |
|        | control  |                                  |          |   |
|        | signal,  |                                  |          |   |
|        | the      |                                  |          |   |
|        | system   |                                  |          |   |
|        | must     |                                  |          |   |
|        | attempt  |                                  |          |   |
|        | to       |                                  |          |   |
|        | resend   |                                  |          |   |
|        | the      |                                  |          |   |
|        | command  |                                  |          |   |
|        | up to    |                                  |          |   |
|        | three    |                                  |          |   |
|        | times    |                                  |          |   |
|        | before   |                                  |          |   |
|        | alerting |                                  |          |   |
|        | the      |                                  |          |   |
|        | pilot to |                                  |          |   |
|        | the      |                                  |          |   |
|        | issue    |                                  |          |   |
|        | via the  |                                  |          |   |
|        | user     |                                  |          |   |
|        | in       |                                  |          |   |
|        | terface. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R31    | The      | setCurrentThrust(double          |          |   |
|        | system   | newThrust) in engine class       |          |   |
|        | should   |                                  |          |   |
|        | dyn      |                                  |          |   |
|        | amically |                                  |          |   |
|        | adjust   |                                  |          |   |
|        | engine   |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | prop     |                                  |          |   |
|        | ortional |                                  |          |   |
|        | to       |                                  |          |   |
|        | a        |                                  |          |   |
|        | irspeed. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R32    | System   | setCurrentThrust(double          |          |   |
|        | should   | newThrust) in engine class       |          |   |
|        | ac       |                                  |          |   |
|        | curately |                                  |          |   |
|        | model    |                                  |          |   |
|        | the      |                                  |          |   |
|        | effects  |                                  |          |   |
|        | of       |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | adj      |                                  |          |   |
|        | ustments |                                  |          |   |
|        | on       |                                  |          |   |
|        | altitude |                                  |          |   |
|        | changes  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R33    | System   | setCurrentThrust(double          |          |   |
|        | should   | newThrust) in engine class       |          |   |
|        | model    |                                  |          |   |
|        | how      |                                  |          |   |
|        | changes  |                                  |          |   |
|        | in       |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | affect   |                                  |          |   |
|        | the      |                                  |          |   |
|        | air      |                                  |          |   |
|        | craft\'s |                                  |          |   |
|        | pitch,   |                                  |          |   |
|        | and in   |                                  |          |   |
|        | s        |                                  |          |   |
|        | cenarios |                                  |          |   |
|        | of       |                                  |          |   |
|        | as       |                                  |          |   |
|        | ymmetric |                                  |          |   |
|        | thrust,  |                                  |          |   |
|        | its roll |                                  |          |   |
|        | and yaw. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R34    | The      | MAX_THRUST attribute in engine   |          |   |
|        | maximum  |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | will be  |                                  |          |   |
|        | 374 kN   |                                  |          |   |
|        | per      |                                  |          |   |
|        | engine.  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R35    | The      | MIN_THRUST attribute in engine   |          |   |
|        | minimum  |                                  |          |   |
|        | thrust   |                                  |          |   |
|        | will be  |                                  |          |   |
|        | 10kN per |                                  |          |   |
|        | engine.  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R36    | The      | currentThrust attribute in       |          |   |
|        | cruise   | engine                           |          |   |
|        | thrust   |                                  |          |   |
|        | will be  |                                  |          |   |
|        | 56.1kN   |                                  |          |   |
|        | per      |                                  |          |   |
|        | engine.  |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R37    | Int      | Autopilot                        |          |   |
|        | egration | syst                             |          |   |
|        | of       | em-\>controlsurface-\>sensordata |          |   |
|        | a        |                                  |          |   |
|        | utopilot | Sensors-\>sensordata-\>          |          |   |
|        | system   | controlsurface-\>autopilotsystem |          |   |
|        | with     | relationship in UML              |          |   |
|        | sensors  |                                  |          |   |
|        | to       |                                  |          |   |
|        | gather   |                                  |          |   |
|        | data.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R38    | A        | 1 to many relationship between   |          |   |
|        | utopilot | AutoPilotControlPanel and        |          |   |
|        | system   | AutoPilotSystem.                 |          |   |
|        | should   |                                  |          |   |
|        | inc      |                                  |          |   |
|        | orporate |                                  |          |   |
|        | re       |                                  |          |   |
|        | dundancy |                                  |          |   |
|        | in the   |                                  |          |   |
|        | case of  |                                  |          |   |
|        | software |                                  |          |   |
|        | bugs.    |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
| R39    | The      | The bidirectional relationship   |          |   |
|        | system   | of Autopilotsystem engine and    |          |   |
|        | shall    | control system helps ensure the  |          |   |
|        | ensure   | reliable communication alongside |          |   |
|        | co       | methods like sendControlSignals  |          |   |
|        | ntinuous | and sendSensorData.              |          |   |
|        | and      |                                  |          |   |
|        | reliable |                                  |          |   |
|        | commu    |                                  |          |   |
|        | nication |                                  |          |   |
|        | between  |                                  |          |   |
|        | the      |                                  |          |   |
|        | a        |                                  |          |   |
|        | utopilot |                                  |          |   |
|        | system   |                                  |          |   |
|        | and      |                                  |          |   |
|        | critical |                                  |          |   |
|        | com      |                                  |          |   |
|        | ponents, |                                  |          |   |
|        | such as  |                                  |          |   |
|        | the      |                                  |          |   |
|        | engine   |                                  |          |   |
|        | control  |                                  |          |   |
|        | system,  |                                  |          |   |
|        | to       |                                  |          |   |
|        | prevent  |                                  |          |   |
|        | loss of  |                                  |          |   |
|        | control  |                                  |          |   |
|        | over     |                                  |          |   |
|        | throttle |                                  |          |   |
|        | settings |                                  |          |   |
|        | or other |                                  |          |   |
|        | vital    |                                  |          |   |
|        | par      |                                  |          |   |
|        | ameters. |                                  |          |   |
+--------+----------+----------------------------------+----------+---+
