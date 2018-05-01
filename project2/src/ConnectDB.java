/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Long Task (Connects and quieries from a database)
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;



public class ConnectDB extends JInternalFrame {
    
    private static ConnectDB instance = null;
    private Connection conn;
    private PreparedStatement st1, st2;
    private ResultSet rs1, rs2;
    private JTextField nameField, ageField;
    private JButton executeQ;
    private JPanel upperPanel, middlePanel, lowerPanel;
    private JLabel promptLabel, ageLabel, description;
    private Task task;
    private Vector<Object> columnNames = new Vector<Object>();
    private Vector<Object> data = new Vector<Object>();
    
    // Create model for the table
    private DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public Class getColumnClass(int column) {
            for (int row = 0; row < getRowCount(); row++) {
                Object o = getValueAt(row, column);
                
                if (o != null) {
                    return o.getClass();
                }
            }
            
            return Object.class;
        }
    };
    
    public static ConnectDB getInstance() {
        if(instance == null) {
            instance = new ConnectDB();
        }
        return instance;
    }
    
    // Long task 
    class Task extends SwingWorker<Void, String> {
        
        @Override
        protected Void doInBackground() {
            connectAndGetData();
            return null;
        }
        
        private void connectAndGetData() {
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Proj2?autoReconnect=true&useSSL=false", "root", "mysqlaccess");
                
                if(nameField.getText().isEmpty() && !ageField.getText().isEmpty()) {
                    ageQuery();
                }
                else if(!nameField.getText().isEmpty() && ageField.getText().isEmpty()) {
                    nameQuery();
                }
                else {
                    JOptionPane.showMessageDialog(new ConnectDB(), "Not valid!");
                }
            }
            catch(ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(new ConnectDB(), "Not valid!");
            }
        }
         
    }
    
    private ConnectDB() {
        super("ConnectDatabase", false, true, false, false);
        nameField = new JTextField(8);
        ageField = new JTextField(6);
        executeQ = new JButton("Execute");
        promptLabel = new JLabel("Enter Student Name: ");
        ageLabel = new JLabel("OR Student Age: ");
        description = new JLabel("Records from db: ");
        upperPanel = new JPanel();
        middlePanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.add(promptLabel);
        upperPanel.add(nameField);
        upperPanel.add(ageLabel);
        upperPanel.add(ageField);
        upperPanel.add(executeQ);
        middlePanel.add(description);
        add(upperPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        
        addButtonListener();
        
        setBounds(25, 25, 700, 550);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void addButtonListener() {
        executeQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                task = new Task();
                task.execute();
            }
        });
    }
    
    // Query by Age
    private void ageQuery() {
        try {
            model.setRowCount(0);
            st2 = conn.prepareStatement("SELECT * FROM students WHERE age = ?");
            st2.setInt(1, Integer.parseInt(ageField.getText()));
            rs2 = st2.executeQuery();
            ResultSetMetaData md2 = rs2.getMetaData();
            int columns = md2.getColumnCount();
            
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement( md2.getColumnName(i) );
            }
            
            while(rs2.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs2.getObject(i));
                }
                data.addElement(row);
            }
            
            createTable();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(new ConnectDB(), "Not valid!");
        }
    }
    
    // Query by Name
    private void nameQuery() {
        try {
            model.setRowCount(0);
            st1 = conn.prepareStatement("SELECT * FROM students WHERE name = ?");
            st1.setString(1, nameField.getText());
            rs1 = st1.executeQuery();
            ResultSetMetaData md1 = rs1.getMetaData();
            int columns = md1.getColumnCount();
            
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md1.getColumnName(i));
            }
            
            while(rs1.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs1.getObject(i));
                }
                data.addElement(row);
            }

            createTable();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(new ConnectDB(), "Not valid!");
        }
    }
    
    private void createTable() {
        JTable table = new JTable(model);
        table.revalidate();
        lowerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        validate();
    }
}