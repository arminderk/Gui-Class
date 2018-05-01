/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and encrypts an integer using the RSA algorithm
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


public class RSA extends JInternalFrame {
    
    private static RSA instance = null;
    
    private JTextField numToEncrypt;
    private JButton encryptButton;
    private JPanel upperPanel, lowerPanel;
    private JLabel numDesc, description, result;
    
    public static RSA getInstance() {
        if(instance == null) {
            instance = new RSA();
        }
        return instance;
    }
    
    private RSA() {
        super("RSA", false, true, false, false);
        numToEncrypt = new JTextField(5);
        encryptButton = new JButton("Encrypt");
        numDesc = new JLabel("Enter number:");
        description = new JLabel("Result: ");
        result = new JLabel();
        upperPanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(numDesc);
        upperPanel.add(numToEncrypt);
        upperPanel.add(encryptButton);
        lowerPanel.add(description);
        lowerPanel.add(result);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 300, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rsaAlgorithm();
            }
        });
    }
    
    private void rsaAlgorithm() {
        result.setText("");
        try {
            int encrypt = Integer.parseInt(numToEncrypt.getText());
            int [] publicKeysAndZ = publicKeysAndZ(3, 11);
            int cipher = (int) (Math.pow(encrypt, publicKeysAndZ[1]) % publicKeysAndZ[0]);
            result.setText("" + cipher);
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a number!");
        }
    }
    
    // Generate random public keys 
    private int[] publicKeysAndZ(int p, int q) {
        int [] publicKeys = new int[3];
        int n = p * q;
        int z = (p-1) * (q-1);
        int k = generateRandomK(z);
        publicKeys[0] = n;
        publicKeys[1] = k;
        publicKeys[2] = z;
        return publicKeys;
    }
    
    // Generate a random number k for public keys
    private int generateRandomK(int z) {
        int [] primeNumbers = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31};
        int [] numbersNotCoPrime = new int[primeNumbers.length];
        int randomPrime = (int) (Math.random() * numbersNotCoPrime.length/2);
        int j = 0;
        for(int i = 0; i<primeNumbers.length; i++) {
            if(z % primeNumbers[i] != 0) {
                numbersNotCoPrime[j] = primeNumbers[i];
                j++;
            }
        }
        int prime = numbersNotCoPrime[randomPrime];
        return prime;     
    }
}
