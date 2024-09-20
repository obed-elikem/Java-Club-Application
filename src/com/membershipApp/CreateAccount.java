package com.membershipApp;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateAccount extends JDialog{
    private JPanel ContentPane;
    public JTextField FirstNametextField;
    public JTextField LastNametextField;
    public JTextField UserNametextField;
    public JPasswordField passwordField1;
    public JTextField mailTextField;
    private JButton saveButton;
    private JButton cancelButton;
    public JFormattedTextField MobileformattedTextField;
    private JPanel FormPanel;
    public JTextField serialNumberField;

    public String action;
    CreateAccount forRefresh;

    Controller link = new Controller();
    Connection mylink = link.getMyConn();

    public CreateAccount(){
        serialNumberField = new JTextField();

        setContentPane(ContentPane);
        setModal(true);
        getRootPane().setDefaultButton(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action.equals("create")){
                    insertData();
                }
                else {
                    updateDatabase();
                }
                onSave();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                onCancel();
            }
        });
        ContentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
            },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        }
        private void onSave() {
            dispose();
        }
        private void onCancel() {
            dispose();
        }
        public void insertData(){
            Date today = new Date();
            SimpleDateFormat format =new SimpleDateFormat("dd/MM/yy");
            String dateString = format.format(today);

            try{
                DatabaseMetaData dMD = mylink.getMetaData();
                ResultSet resultSet = dMD.getTables(null,null,"javaclub",null);

                String sQL = "INSERT INTO javaclub VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statement = mylink.prepareStatement(sQL);

                int dataInserted = 0;
                 if (resultSet.next()){
                     statement.setInt(1,0);
                     statement.setString(2,FirstNametextField.getText());
                     statement.setString(3,LastNametextField.getText());
                     statement.setString(4,MobileformattedTextField.getText());
                     statement.setString(5, mailTextField.getText());
                     statement.setString(6,UserNametextField.getText());
                     statement.setString(7,passwordField1.getText());
                     statement.setString(8,dateString);
                     dataInserted = statement.executeUpdate();
                 }
                 if (dataInserted >=0){
                     JOptionPane.showMessageDialog(null,"Data inserted succesfully");
                 }else {
                     JOptionPane.showMessageDialog(null,"Error! Data insertion failed");
                 }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public  void updateDatabase(){
        String sQL = "UPDATE javaclub SET firstName = ' "
                + FirstNametextField.getText() + "', "
                +"lastName = '" + LastNametextField.getText() + "',"
                +"mobileNumber ='" + MobileformattedTextField.getText()+"',"
                +"emailAddress ='" + mailTextField.getText()+"',"
                +"userName = '" + UserNametextField.getText() + "',"
                +"password = '" + passwordField1.getSelectedText() + "',"
                +"WHERE serialNumber ='"+ Integer.parseInt(serialNumberField.getText() )+"'";
        try {
            PreparedStatement statement = mylink.prepareStatement(sQL);
            int update = statement.executeUpdate();

            String serialNumber = String.valueOf(serialNumberField);
            if (update > 0){
                JOptionPane.showMessageDialog(null,"Register updates successfully");
                System.out.println(serialNumber);
            }else {
                JOptionPane.showMessageDialog(null,"update failed");
                System.out.println(serialNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("CreateAccount");
        frame.setContentPane(new CreateAccount().ContentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(150,100,700,350);
        frame.setVisible(true);
    }

    }

