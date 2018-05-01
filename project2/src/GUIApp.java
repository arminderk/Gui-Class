/*
Arminder Khinda
Date: 03/06/2018
Comp 585
Purpose: Creates the main frame, categories, and sub-categories
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class GUIApp extends JFrame {

    private JDesktopPane desktop;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;
    private JPanel labelPanel;
    private JLabel statusLabel;
    private JTree tree;

    // menu stuff
    private JMenuBar menuBar;
    private JMenu app;
    private JMenu help;
    private JMenuItem exit;
    private JMenuItem about;

    public GUIApp() {
        initComponents();
    }

    private void initComponents() {
        buildDesktop();
        buildTree();
        addTreeListeners();
        buildMenu();
        addMenuListeners();
        buildPanel();
        buildFrame();
    }

    private void buildTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tools");
        DefaultMutableTreeNode area = new DefaultMutableTreeNode("Area");
        DefaultMutableTreeNode areaOfCircle = new DefaultMutableTreeNode("Area of Circle");
        DefaultMutableTreeNode areaOfSquare = new DefaultMutableTreeNode("Area of Square");
        DefaultMutableTreeNode areaOfTriangle = new DefaultMutableTreeNode("Area of Triangle");
        DefaultMutableTreeNode encryption = new DefaultMutableTreeNode("Encryption Algorithms");
        DefaultMutableTreeNode ceasarCipher = new DefaultMutableTreeNode("Caesar Cipher");
        DefaultMutableTreeNode feistelCipher = new DefaultMutableTreeNode("Feistel Cipher");
        DefaultMutableTreeNode rsa = new DefaultMutableTreeNode("RSA");
        DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Temperature");
        DefaultMutableTreeNode fToC = new DefaultMutableTreeNode("Fahrenheit To Celsius");
        DefaultMutableTreeNode cToF = new DefaultMutableTreeNode("Celsius To Fahrenheit");
        DefaultMutableTreeNode fToK = new DefaultMutableTreeNode("Fahrenheit To Kelvin");
        DefaultMutableTreeNode speed = new DefaultMutableTreeNode("Speed");
        DefaultMutableTreeNode mToK = new DefaultMutableTreeNode("mph to km/h");
        DefaultMutableTreeNode kToM = new DefaultMutableTreeNode("km/h to mph");
        DefaultMutableTreeNode mToF = new DefaultMutableTreeNode("mph to feet/second");
        DefaultMutableTreeNode other = new DefaultMutableTreeNode("Other");
        DefaultMutableTreeNode dbConn = new DefaultMutableTreeNode("DB Connect");
        DefaultMutableTreeNode percent = new DefaultMutableTreeNode("Percent");
        DefaultMutableTreeNode bmi = new DefaultMutableTreeNode("BMI");
        area.add(areaOfCircle);
        area.add(areaOfSquare);
        area.add(areaOfTriangle);
        encryption.add(ceasarCipher);
        encryption.add(feistelCipher);
        encryption.add(rsa);
        temp.add(fToC);
        temp.add(fToK);
        temp.add(cToF);
        speed.add(mToK);
        speed.add(kToM);
        speed.add(mToF);
        other.add(dbConn);
        other.add(percent);
        other.add(bmi);
        root.add(area);
        root.add(encryption);
        root.add(temp);
        root.add(speed);
        root.add(other);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
    }

    private void addTreeListeners() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int selRow = tree.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
                if(selRow != -1) {
                    treeClicked();
                }
            }
        });
    }

    private void treeClicked() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(node != null && node.isLeaf()) {
            switch(node.toString()) {
                case "Area of Circle":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    AreaOfCircle areaOfCircle = AreaOfCircle.getInstance();
                    if(!areaOfCircle.isVisible()) {
                        areaOfCircle.setVisible(true);
                        desktop.add(areaOfCircle);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Area of Square":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    AreaOfSquare areaOfSquare = AreaOfSquare.getInstance();
                    if(!areaOfSquare.isVisible()) {
                        areaOfSquare.setVisible(true);
                        desktop.add(areaOfSquare);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Area of Triangle":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    AreaOfTriangle areaOfTriangle = AreaOfTriangle.getInstance();
                    if(!areaOfTriangle.isVisible()) {
                        areaOfTriangle.setVisible(true);
                        desktop.add(areaOfTriangle);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Caesar Cipher":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    CaesarCipher caesarCipher = CaesarCipher.getInstance();
                    if(!caesarCipher.isVisible()) {
                        caesarCipher.setVisible(true);
                        desktop.add(caesarCipher);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Feistel Cipher":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    FeistelCipher feistelCipher = FeistelCipher.getInstance();
                    if(!feistelCipher.isVisible()) {
                        feistelCipher.setVisible(true);
                        desktop.add(feistelCipher);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "RSA":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    RSA rsa = RSA.getInstance();
                    if(!rsa.isVisible()) {
                        rsa.setVisible(true);
                        desktop.add(rsa);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Fahrenheit To Celsius":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    FahrenheitToCelsius fToC = FahrenheitToCelsius.getInstance();
                    if(!fToC.isVisible()) {
                        fToC.setVisible(true);
                        desktop.add(fToC);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Fahrenheit To Kelvin":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    FahrenheitToKelvin fToK = FahrenheitToKelvin.getInstance();
                    if(!fToK.isVisible()) {
                        fToK.setVisible(true);
                        desktop.add(fToK);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Celsius To Fahrenheit":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    CelsiusToFahrenheit cToF = CelsiusToFahrenheit.getInstance();
                    if(!cToF.isVisible()) {
                        cToF.setVisible(true);
                        desktop.add(cToF);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "mph to km/h":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    MilesToKilometers mToK = MilesToKilometers.getInstance();
                    if(!mToK.isVisible()) {
                        mToK.setVisible(true);
                        desktop.add(mToK);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "km/h to mph":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    KilometersToMiles kToM = KilometersToMiles.getInstance();
                    if(!kToM.isVisible()) {
                        kToM.setVisible(true);
                        desktop.add(kToM);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "mph to feet/second":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    MilesToFeet mToF = MilesToFeet.getInstance();
                    if(!mToF.isVisible()) {
                        mToF.setVisible(true);
                        desktop.add(mToF);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "DB Connect":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    ConnectDB dbconn = ConnectDB.getInstance();
                    if(!dbconn.isVisible()) {
                        dbconn.setVisible(true);
                        desktop.add(dbconn);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "Percent":
                    // bring up the dialog box
                    Percent percent = Percent.getInstance();
                    if(!percent.isVisible()) {
                        percent.setVisible(true);
                        desktop.add(percent);
                    }
                    break;
                default:
                    break;
            }
            switch(node.toString()) {
                case "BMI":
                    // bring up the dialog box
                    BMI bmi = BMI.getInstance();
                    if(!bmi.isVisible()) {
                        bmi.setVisible(true);
                        desktop.add(bmi);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void buildDesktop() {
        desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon imageIcon = new ImageIcon("images/csun_logo.png");
                Image image = imageIcon.getImage();

                int x=0;
                int y=0;
                double imageWidth = image.getWidth(null);
                double imageHeight = image.getHeight(null);
                double screenWidth = getWidth();
                double screenHeight = getHeight();

                if(screenWidth != 0) {
                    x = (int) screenWidth / 2 - (int) imageWidth / 2;
                }
                if(screenHeight != 0) {
                    y = (int) screenHeight / 2 - (int) imageHeight / 2;
                }

                g.drawImage(image, x, y, this);
            }
        };
    }

    private void addMenuListeners() {

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                exitActionPerformed();
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aboutActionPerformed();
            }
        });

    }

    private void exitActionPerformed() {
        dispose();
    }

    private void aboutActionPerformed() {
        JOptionPane.showMessageDialog(this, "Thx for using my app!");
    }

    private void buildPanel() {

        panel = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tree);

        // label panel and label
        labelPanel = new JPanel();
        statusLabel = new JLabel();
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel.setMinimumSize(new Dimension(0,18));
        statusLabel.setPreferredSize(new Dimension(0,18));

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
        splitPane.setContinuousLayout(true);
        splitPane.add(scrollPane, JSplitPane.LEFT);
        splitPane.add(desktop, JSplitPane.RIGHT);

        panel.setLayout(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);

        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(statusLabel, BorderLayout.CENTER);
    }

    private void buildMenu() {
        menuBar = new JMenuBar();
        app = new JMenu("App");
        help = new JMenu("Help");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");
        app.add(exit);
        help.add(about);
        menuBar.add(app);
        menuBar.add(help);
    }

    private void buildFrame() {
        setLayout(new BorderLayout());
        getContentPane().add(labelPanel, BorderLayout.SOUTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/csun.gif"));
        setTitle("Second Project");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950,650);
        setVisible(true);
    }

}
