Probability assumptions:

Fault tree#1

![Fault Tree 1](diagrams/FaultTree1.png)

P(CALC-ERR) = 0.01

P(INC-LOGIC) = 0.002

P(SW-BUG-ERR) = 1-(1-0.01)\* (1-0.02) ≈ 0.0298

P(CON-PAN-COMP-FAIL) = 0.04

P(DATA-TRA-ERR) = 0.06

P(COM-FAIL) = 1-(1-0.04)\* (1-0.06) ≈ 0.0976

P(AUTOPILOT-SYS-FAIL) = 1-(1-0.0298) \* (1-0.0976) ≈ 0.124

Fault tree#2

![Fault Tree 2](diagrams/FaultTree2.png)

P(INC-INTERP-PROTO) = 0.04

P(BUF-OVERFLO) = 0.05

P(INC-PRO-VER) = 0.01

P(SW-ERR) = 1-(1-0.04)\* (1-0.05)\*(1-0.01)≈ 0.0971

P(DMG-PORT-CABLE) = 0.01

P(ELEC-MALF) = 0.02

P(SIG-DEGR) = 0.02

P(HAR-FAIL-COM-INTER) = 1-(1-0.01)\* (1-0.02)\*(1-0.02)≈ 0.0492

P(COM-SYS-FAIL) = 1-(1-0.0971)\*(1-0.0492) ≈0.142
