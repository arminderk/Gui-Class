/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the GUI and encrypts a string using the Caesar Cipher algorithm
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.IllegalFormatException;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class CaesarCipher extends JInternalFrame {
    
    private static CaesarCipher instance = null;
    
    private JTextField stringToEncrypt;
    private JButton encryptButton;
    private JLabel description, resultLabel, promptLabel;
    private JPanel textAndButton, displayResult;
    
    public static CaesarCipher getInstance() {
        if(instance == null) {
            instance = new CaesarCipher();
        }
        return instance;
    }
    
    private CaesarCipher() {
        super("Caesar Cipher", false, true, false, false);
        stringToEncrypt = new JTextField(10);
        encryptButton = new JButton("Encrypt");
        promptLabel = new JLabel("String to Encrypt: ");
        description = new JLabel("String: ");
        resultLabel = new JLabel();
        textAndButton = new JPanel();
        displayResult = new JPanel();
        textAndButton.add(promptLabel);
        textAndButton.add(stringToEncrypt);
        textAndButton.add(encryptButton);
        displayResult.add(description);
        displayResult.add(resultLabel);
        add(textAndButton, BorderLayout.NORTH);
        add(displayResult, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 400, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                caesarCipher();
            }
        });
    }
    
    private void caesarCipher() {
        resultLabel.setText("");
        try {
            String ciphered = "";
            char c = '0';
            String plainText = stringToEncrypt.getText();
            
            for(int i = 0; i<plainText.length(); i++) {
                c = plainText.charAt(i);
                if(c == 32) {
                    c = 32;
                }
                else {
                    if((c > 119 && c <= 122) || (c > 87 && c <= 90)) {
                        c = (char) (c - 23);
                    }
                    else {
                        c = (char) (c + 3);
                    }
                }
                ciphered += c;
            }
            
            
            resultLabel.setText(ciphered);
        }
        catch(IllegalFormatException s) {
            JOptionPane.showMessageDialog(this,"Enter a valid input!");
        }
    }
}
