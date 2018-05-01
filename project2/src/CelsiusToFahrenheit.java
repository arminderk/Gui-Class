/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI converts temperature from celsius to fahrenheit
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

public class CelsiusToFahrenheit extends JInternalFrame {
    
    private static CelsiusToFahrenheit instance = null;
    private JTextField celsius;
    private JButton convert;
    private JLabel prompt, description, result;
    private JPanel upper, lower;
    
    public static CelsiusToFahrenheit getInstance() {
        if(instance == null) {
            instance = new CelsiusToFahrenheit();
        }
        return instance;
    }
    
    private CelsiusToFahrenheit() {
        super("CelsiusToFahrenheit", false, true, false, false);
        celsius = new JTextField(5);
        convert = new JButton("Convert");
        prompt = new JLabel("Enter \u00b0C ");
        description = new JLabel("Result: ");
        result = new JLabel();
        upper = new JPanel();
        lower = new JPanel();
        upper.add(prompt);
        upper.add(celsius);
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
                cToF();
            }
        });
    }
    
    private void cToF() {
        result.setText("");
        try {
            double c = Double.parseDouble(celsius.getText());
            double celsius = (c * 9/5) + 32;
            result.setText(celsius + "\u00b0F");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
}
