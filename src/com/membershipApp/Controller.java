package com.membershipApp;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Controller {
        public static Connection myConn;

        public static void main(String[] args) {
            getMyConn();
            createNewTable();
        }

        public static Connection getMyConn(){
            try{
                String url = "jdbc:mysql://localhost:3306/colemansclub";
                String user = "root";
                String password = "";

                myConn = DriverManager.getConnection(url,user,password);
                JOptionPane.showMessageDialog(null,"connected successfully");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return myConn;
        }

        public static void createNewTable(){
            try{
                DatabaseMetaData dm = myConn.getMetaData();
                ResultSet set = dm.getTables(null,null,"javaclub",null);

                if(set.next()){
                    new CreateAccount();
                }
                else {
                    String sQL = "CREATE TABLE javaclub("
                            + "serialNumber int (10) NOT NULL AUTO_INCREMENT,"
                            + "firstName varchar (20),"
                            + "lastName varchar (20),"
                            + "mobileNumber varchar (20),"
                            + "emailAddress varchar (50),"
                            + "userName varchar (25),"
                            + "password varchar (25),"
                            + "date varchar (20))";

                    PreparedStatement statement = myConn.prepareStatement(sQL);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"javaclub table created successfully");

                    getMyConn();
                    new UserInterface();
                    new CreateAccount();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public ArrayList<Members> getMembers(){

            ArrayList<Members> list = new ArrayList<>();

            Members fromDatabase;

            try{
                String sqL = "SELECT * FROM javaclub";

                PreparedStatement statement = getMyConn().prepareStatement(sqL);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    fromDatabase = new Members(
                            resultSet.getInt("serialNumber"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("mobileNumber"),
                            resultSet.getString("emailAddress"),
                            resultSet.getString("userName"),
                            resultSet.getString("date")
                    );

                    list.add(fromDatabase);
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }

    }

