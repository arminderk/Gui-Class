/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and computes the area of a square
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AreaOfSquare extends JInternalFrame {
    
    private static AreaOfSquare instance = null;
    
    private JTextField textField;
    private JButton button;
    private JLabel promptLabel, result, resultLabel;
    private JPanel upperPanel, middlePanel, lowerPanel;
    
    public static AreaOfSquare getInstance() {
        if(instance == null) {
            instance = new AreaOfSquare();
        }
        return instance;
    }
    
    private AreaOfSquare() {
        //args: title, resisability, closability, maximizablity and iconifiability
        super("Area Of Square", false, true, false, false);
        textField = new JTextField(10);
        button = new JButton("Area");
        resultLabel = new JLabel("Area: ");
        result = new JLabel();
        promptLabel = new JLabel("Enter length of sides:");
        upperPanel = new JPanel();
        middlePanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(promptLabel);
        middlePanel.add(textField);
        middlePanel.add(button);
        lowerPanel.add(resultLabel);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);

        addButtonListener();

        setBounds(25, 25, 280, 140);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaOfSquare();
            }
        });
    }
    
    private void areaOfSquare() {
        result.setText("");
        try {
            double s = Double.parseDouble(textField.getText());
            double area = Math.pow(s, 2);
            result.setText(area + "");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Enter a number!");
        }
    }
    
}
