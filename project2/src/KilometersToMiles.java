/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and converts the distance from km/h to mph
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

public class KilometersToMiles extends JInternalFrame {
    
    private static KilometersToMiles instance = null;
    private JTextField kilometers;
    private JButton convert;
    private JLabel prompt, description, result;
    private JPanel upper, middle, lower;
    
    public static KilometersToMiles getInstance() {
        if(instance == null) {
            instance = new KilometersToMiles();
        }
        return instance;
    }
    
    private KilometersToMiles() {
        super("KilometersToMiles", false, true, false, false);
        kilometers = new JTextField(10);
        convert = new JButton("Convert");
        prompt = new JLabel("Enter km/h below: ");
        description = new JLabel("Result: ");
        result = new JLabel();
        upper = new JPanel();
        middle = new JPanel();
        lower = new JPanel();
        upper.add(prompt);
        middle.add(kilometers);
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
                kmToMph();
            }
        });
    }
    
    private void kmToMph() {
        result.setText("");
        try {
            double kmh = Double.parseDouble(kilometers.getText());
            double mph = kmh / 1.609344;
            result.setText(mph + " mph");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }
    
    
}
