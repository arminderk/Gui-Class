/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and computes the bmi depending on height and weight
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

public class BMI extends JInternalFrame {
    
    private static BMI instance = null;
    private JTextField weight, feet, inches;
    private JButton calc;
    private JLabel weightPrompt, heightPrompt, description, result;
    private JLabel weightLabel, feetLabel, inchLabel;
    private JPanel upperPanel, middlePanel, lowerPanel;
    
    public static BMI getInstance() {
        if(instance == null) {
            instance = new BMI();
        }
        return instance;
    }
    
    private BMI() {
        super("BMI", false, true, false, false);
        weight = new JTextField(15);
        feet = new JTextField(5);
        inches = new JTextField(5);
        calc = new JButton("Calculate");
        weightPrompt = new JLabel("Weight: ");
        weightLabel = new JLabel("lb");
        heightPrompt = new JLabel("Height: ");
        feetLabel = new JLabel("ft");
        inchLabel = new JLabel("in");
        description = new JLabel("Result: ");
        result = new JLabel();
        upperPanel = new JPanel();
        middlePanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(weightPrompt);
        upperPanel.add(weight);
        upperPanel.add(weightLabel);
        middlePanel.add(heightPrompt);
        middlePanel.add(feet);
        middlePanel.add(feetLabel);
        middlePanel.add(inches);
        middlePanel.add(inchLabel);
        middlePanel.add(calc);
        lowerPanel.add(description);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 450, 160);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                calculateBMI();
            }
        });
    }
    
    private void calculateBMI() {
        result.setText("");
        try {
            double f = Double.parseDouble(feet.getText()) * 12;
            double in = Double.parseDouble(inches.getText());
            double h = f + in;
            double w = Double.parseDouble(weight.getText());
            double bmi = (w / Math.pow(h, 2)) * 703;
            result.setText(bmi + "");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
}
