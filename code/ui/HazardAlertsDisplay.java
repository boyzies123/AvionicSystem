package code.ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class HazardAlertsDisplay extends JPanel{

    private List<String> hazardWarnings;
    // private Checklist emergencyChecklist;

    private JLabel hazardAlertLabel;
    private JTextArea emergencyActionPlan;

    public HazardAlertsDisplay(){
        super(new GridLayout(2,1));
        hazardAlertLabel = new JLabel("No hazards detected");
        emergencyActionPlan = new JTextArea(5, 20);
        emergencyActionPlan.setEditable(false);
        emergencyActionPlan.setLineWrap(true);
        emergencyActionPlan.setWrapStyleWord(true);

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

        setBorder(BorderFactory.createTitledBorder("Hazard Alerts"));
        add(hazardAlertLabel);
        add(new JScrollPane(emergencyActionPlan));
    }

    public void displayChecklist(){

    }

    public void issueHazardAlert(){
        
    }

    public void triggerAudibleAlert(){
        
    }
    public void visualAlert(){
        
    }
    
}
