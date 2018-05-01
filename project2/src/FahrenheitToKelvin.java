/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and converts temperature from fahrenheit to kelvin
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

public class FahrenheitToKelvin extends JInternalFrame {
    
    private static FahrenheitToKelvin instance = null;
    private JTextField fahrenheit;
    private JButton convert;
    private JLabel prompt, description, result;
    private JPanel upper, lower;
    
    public static FahrenheitToKelvin getInstance() {
        if(instance == null) {
            instance = new FahrenheitToKelvin();
        }
        return instance;
    }
    
    private FahrenheitToKelvin() {
        super("FahrenheitToKelvin", false, true, false, false);
        fahrenheit = new JTextField(5);
        convert = new JButton("Convert");
        prompt = new JLabel("Enter \u00b0F ");
        description = new JLabel("Result: ");
        result = new JLabel();
        upper = new JPanel();
        lower = new JPanel();
        upper.add(prompt);
        upper.add(fahrenheit);
        upper.add(convert);
        lower.add(description);
        lower.add(result);
        add(upper, BorderLayout.NORTH);
        add(lower, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 280, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fToK();
            }
        });
    }
    
    private void fToK() {
        result.setText("");
        try {
            double f = Double.parseDouble(fahrenheit.getText());
            double kelvin = (f + 459.67) * 5/9;
            result.setText(kelvin + " kelvin");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
}
