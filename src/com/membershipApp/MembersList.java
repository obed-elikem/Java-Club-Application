package com.membershipApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MembersList {
    public JPanel MemberListPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JDialog dialog;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable Registertable;
    private JPanel Panel1;

    DefaultTableModel model = new DefaultTableModel();
    CreateAccount account = new CreateAccount();

    public  MembersList(){
        CreateRegisterTable();

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFromDatabase();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastName = searchTextField.getText();
                filter(lastName);
                refreshTable();
            }
        });
    }
    private void CreateRegisterTable(){
        Object[] columnName = new Object[7];

        columnName [0] = "serial number";
        columnName [1] = "First name";
        columnName [2] = "Last name";
        columnName [3] = "Mobile number";
        columnName [4] = "Email address";
        columnName [5] = "User name";
        columnName [6] = "Date registrerd";

        model.setColumnIdentifiers(columnName);

        Object[] rowData = new Object[7];
        Controller contact = new Controller();
        ArrayList<Members>list = contact.getMembers();


        for (int i = 0; i <list.size();i++){
            rowData[0] = list.get(i).getSerialNumber();
            rowData[1] = list.get(i).getFirstName();
            rowData[2] = list.get(i).getLastNmae();
            rowData[3] = list.get(i).getMobileNumber();
            rowData[4] = list.get(i).getEmailAddress();
            rowData[5] = list.get(i).getUserName();
            rowData[6] = list.get(i).getDate();
            model.addRow(rowData);
        }
        Registertable.setModel(model);

    }
    private void filter(String lastName){
        TableRowSorter<DefaultTableModel>searchItem = new TableRowSorter<>(model);
        Registertable.setRowSorter(searchItem);
        searchItem.setRowFilter(RowFilter.regexFilter(lastName));
    }
    public void fillUpdateDialog(){
        int i = Registertable.getSelectedRow();

        if (i >= 0){
            account.serialNumberField.setText(Registertable.getValueAt(i,0).toString());
            account.FirstNametextField.setText(Registertable.getValueAt(i,1).toString());
            account.LastNametextField.setText(Registertable.getValueAt(i,2).toString());
            account.MobileformattedTextField.setText(Registertable.getValueAt(i,3).toString());
            account.mailTextField.setText(Registertable.getValueAt(i,4).toString());
            account.UserNametextField.setText(Registertable.getValueAt(i,5).toString());
            account.passwordField1.setText("********");

        }
    }
    public void deleteFromDatabase(){
        int j = Registertable.getSelectedRow();
        if (j >-1){
            int serialNumber = Integer.parseInt(Registertable.getValueAt(j,0).toString());
            try {
                Connection link = new Controller().getMyConn();
                String sQL = "DELETE FROM javaclub WHERE serialNumber = '" + serialNumber + "'";
                PreparedStatement statement = link.prepareStatement(sQL);
                int update = statement.executeUpdate();

                if (update > 0) {
                    JOptionPane.showMessageDialog(null,"Register updates successfully");
                    System.out.println(serialNumber);
                }
                else {
                    JOptionPane.showMessageDialog(null,"update failed");
                    System.out.println(serialNumber);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void refreshTable(){
        model.fireTableDataChanged();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MembersList");
        frame.setContentPane(new MembersList().MemberListPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(150,100,700,350);
        frame.setVisible(true);
    }
}

