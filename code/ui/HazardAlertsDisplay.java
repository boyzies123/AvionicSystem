package code.ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Code made by: James McKenzie
 * Date created: 13/05/2024
 * Date modified: 22/05/2024
 */
public class HazardAlertsDisplay{


    private static JPanel panel;
    // private List<String> hazardWarnings;
    // private Checklist emergencyChecklist;

    private static JLabel hazardAlertLabel;
    private static JTextArea emergencyActionPlan;

    public static void initialize(){
        panel = new JPanel(new GridLayout(2,1));
        hazardAlertLabel = new JLabel("No hazards detected");
        emergencyActionPlan = new JTextArea(5, 20);
        emergencyActionPlan.setEditable(false);
        emergencyActionPlan.setLineWrap(true);
        emergencyActionPlan.setWrapStyleWord(true);

        // Add action listeners for buttons
        // autopilotButton.addActionListener(e -> engageAutoPilot());

        panel.setBorder(BorderFactory.createTitledBorder("Hazard Alerts"));
        panel.add(hazardAlertLabel);
        panel.add(new JScrollPane(emergencyActionPlan));
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void displayChecklist(){

    }

    public static void issueHazardAlert(){
        
    }

    public static void triggerAudibleAlert(){
        
    }
    public static void visualAlert(){
        
    }
    
}
