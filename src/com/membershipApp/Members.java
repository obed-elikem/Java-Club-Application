package com.membershipApp;

public class Members {
    private int serialNumber;
    private String firstName;
    private String lastNmae;
    private String mobileNumber;
    private String emailAddress;
    private String userName;
    private String date;

    public Members(int serialNumber, String firstName, String lastNmae, String mobileNumber, String emailAddress, String userName, String date) {
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.date = date;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
