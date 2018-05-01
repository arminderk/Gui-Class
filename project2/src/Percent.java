/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and calculates the percentage of a number
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Percent extends JInternalFrame {
    
    private static Percent instance = null;
    private JTextField percent, number;
    private JButton calc;
    private JLabel prompt1, prompt2, description, result;
    private JPanel upperPanel, lowerPanel;
    
    public static Percent getInstance() {
        if(instance == null) {
            instance = new Percent();
        }
        return instance;
    }
    
    private Percent() {
        super("Percent", false, true, false, false);
        percent = new JTextField(5);
        number = new JTextField(8);
        calc = new JButton("Calculate");
        prompt1 = new JLabel("What is ");
        prompt2 = new JLabel("% of ");
        description = new JLabel("Result: ");
        result = new JLabel();
        upperPanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(prompt1);
        upperPanel.add(percent);
        upperPanel.add(prompt2);
        upperPanel.add(number);
        upperPanel.add(calc);
        lowerPanel.add(description);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 450, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                calculatePercent();
            }
        });
    }
    
    private void calculatePercent() {
        result.setText("");
        try {
            double percentage = Double.parseDouble(percent.getText());
            double n = Double.parseDouble(number.getText());
            double answer = (percentage/100) * n;
            result.setText(answer + "");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
    
}
