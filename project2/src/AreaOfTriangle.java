/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and computes the area of a triangle
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

public class AreaOfTriangle extends JInternalFrame {
    
    private static AreaOfTriangle instance = null;
    
    private JTextField base, height;
    private JButton button;
    private JLabel result, resultLabel, baseLabel, heightLabel;
    private JPanel upperPanel, lowerPanel;
    
    public static AreaOfTriangle getInstance() {
        if(instance == null) {
            instance = new AreaOfTriangle();
        }
        return instance;
    }
    
    private AreaOfTriangle() { 
        super("Area of Triangle", false, true, false, false);
        base = new JTextField(5);
        height = new JTextField(5);
        button = new JButton("Calculate");
        baseLabel = new JLabel("Base: ");
        heightLabel = new JLabel("Height: ");
        resultLabel = new JLabel("Area: ");
        result = new JLabel();
        upperPanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(baseLabel);
        upperPanel.add(base);
        upperPanel.add(heightLabel);
        upperPanel.add(height);
        upperPanel.add(button);
        lowerPanel.add(resultLabel);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 400, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);   
    }
    
    private void addButtonListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaOfTriangle();
            }
        });
    }
    
    private void areaOfTriangle() {
        result.setText("");
        try {
            double b = Double.parseDouble(base.getText());
            double h = Double.parseDouble(height.getText());
            double area = (b * h) / 2;
            result.setText(area + "");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Enter a number!");
        }
    }
}
