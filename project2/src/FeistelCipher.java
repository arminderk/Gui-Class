/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and encrypts an array of integers using the Feistel Cipher algorithm
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class FeistelCipher extends JInternalFrame {
    
    private static FeistelCipher instance = null;
    
    private JTextField textFields[];
    private JButton encryptButton;
    private JPanel upperPanel, centerPanel, lowerPanel;
    private JLabel arrayLabel, description, result;
    int[] keys = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
    };
    
    public static FeistelCipher getInstance() {
        if(instance == null) {
            instance = new FeistelCipher();
        }
        return instance;
    }
    
    private FeistelCipher() {
        super("Feistel Cipher", false, true, false, false);
        textFields = new JTextField[6];
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());
        arrayLabel = new JLabel("Enter Numbers Below: ");
        upperPanel.add(arrayLabel);
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        
        for(int i = 0; i<6; i++) {
            textFields[i] = new JTextField(2);
            centerPanel.add(textFields[i]);
        }
        
        encryptButton = new JButton("Encrypt");
        centerPanel.add(encryptButton);
        lowerPanel = new JPanel();
        description = new JLabel("Result: ");
        result = new JLabel();
        lowerPanel.add(description);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 360, 150);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        encryptButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               feistelCipher();
           }
        });
    }
    
    private void feistelCipher() {
        result.setText("");
        try {
            int[] input = new int[textFields.length];
            int middle = input.length / 2;
            int[] left = new int[middle];
            int[] right = new int[middle];
            int[] encrypted = new int[input.length];
            for(int i = 0; i<textFields.length; i++) {
                input[i] = Integer.parseInt(textFields[i].getText());
            }
            System.arraycopy(input, 0, left, 0, middle);
            System.arraycopy(input, middle, right, 0, middle);
            for (int round = 0; round < 16; round++) {
                for (int i = 0; i < right.length; i++) {
                    right[i] = left[i] ^ (right[i] ^ keys[round]);
                }
                swap(left, right);
            }
            System.arraycopy(left, 0, encrypted, 0, left.length);
            System.arraycopy(right, 0, encrypted, middle, right.length);
            result.setText(Arrays.toString(encrypted));
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Please enter numbers!");
        }
    }
    
    //Swap two arrays
    private void swap(int[] left, int[] right) {
        int[] temp = new int[left.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = left[i];
            left[i] = right[i];
            right[i] = temp[i];
        }   
    }
    
}
