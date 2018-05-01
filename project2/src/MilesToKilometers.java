/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and converts the distance from mph to km/h
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

public class MilesToKilometers extends JInternalFrame {
    
    private static MilesToKilometers instance = null;
    private JTextField miles;
    private JButton convert;
    private JLabel prompt, description, result;
    private JPanel upper, middle, lower;
    
    public static MilesToKilometers getInstance() {
        if(instance == null) {
            instance = new MilesToKilometers();
        }
        return instance;
    }
    
    private MilesToKilometers() {
        super("MilesToKilometers", false, true, false, false);
        miles = new JTextField(10);
        convert = new JButton("Convert");
        prompt = new JLabel("Enter mph below: ");
        description = new JLabel("Result: ");
        result = new JLabel();
        upper = new JPanel();
        middle = new JPanel();
        lower = new JPanel();
        upper.add(prompt);
        middle.add(miles);
        middle.add(convert);
        lower.add(description);
        lower.add(result);
        add(upper, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(lower, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 280, 140);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mphToKm();
            }
        });
    }
    
    private void mphToKm() {
        result.setText("");
        try {
            double mph = Double.parseDouble(miles.getText());
            double kmh = mph * 1.609344;
            result.setText(kmh + " km/h");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
    
    
}
