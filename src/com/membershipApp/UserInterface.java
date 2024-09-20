package com.membershipApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class UserInterface {
    private JPanel panel1;
    private JLabel LableTitle;
    private JPanel WestPanel;
    private JButton logInButton;
    private JTextField userNameTextfiled;
    private JPanel EastPanel;
    private JButton createAccountButton;
    private JPasswordField passwordTextField;

    private JMenuBar Title(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_J);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem openMenu = new JMenuItem("Open");
        JMenuItem existMenu = new JMenuItem("Exist");
        JMenuItem AboutMenu = new JMenuItem("About");

        openMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("MembersList");
                frame.setContentPane(new MembersList().MemberListPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setBounds(200,400,650,300);
                frame.setLocationRelativeTo(null);
                frame.setSize(700,700);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        openMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.SHIFT_MASK));


        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));

        JMenuItem HomeMenu = new JMenuItem("Home");
        HomeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));

        JMenuItem  aboutMenu = new JMenuItem("About");
        aboutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));

        fileMenu.add(openMenu);
        fileMenu.add(HomeMenu);
        fileMenu.add(exitMenu);

        helpMenu.add(aboutMenu);

        existMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JWindow window = new JWindow();
                JOptionPane.showMessageDialog(null,
                        "GROUP MEMBERS\n1. Janet Aidoo Coleman - 01212700D\n2. Buabeng Solomon Nyarko - 01211975D\n3. Agbatey Obed Elikem - 01211890D" +
                                "\n4. Cobbinah Abraham Thomas - 01211902D\n5.Amoah Nicholas  - 01211782D\" +\n6. Nai Nii Marley Benjamin - 01210227D" +
                                "\n\n\n\n                                    COURSE TITLE: \n                                      CPS  JAVA II",
                        "2023 ACADEMIC YEAR",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });


        return menuBar;
    }

    public UserInterface() {
        Dimension dim = createAccountButton.getPreferredSize();
        dim.height = 40;
        createAccountButton.setPreferredSize(dim);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccount dialoq = new CreateAccount();
                dialoq.setTitle("Create Account");
                dialoq.setLocationRelativeTo(null);
                dialoq.action = "create";
                dialoq.setSize(650,650);
                dialoq.setVisible(true);
            }
        });

        Controller controller = new Controller();
        Connection LogLink = controller.getMyConn();

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String sQL = "SELECT * FROM javaclub where userName = ? and password = ?";
                    PreparedStatement statement = LogLink.prepareStatement(sQL);
                    statement.setString(1,userNameTextfiled.getText());
                    statement.setString(2,passwordTextField.getText());

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()){
                        JOptionPane.showMessageDialog(null,"login Successful");

                        userNameTextfiled.setText("");
                        passwordTextField.setText("");

                        JFrame frame= new JFrame("MemberList");
                        frame.setContentPane(new MembersList().MemberListPanel);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setBounds(200,400,650,300);
                        frame.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid userName or Password");
                    }

                }catch (Exception el){
                    el.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserInterface");
        UserInterface homePage = new UserInterface();
        frame.setJMenuBar(homePage.Title());
        frame.setContentPane(new UserInterface().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(150,100,700,350);
        frame.setVisible(true);

    }
}
