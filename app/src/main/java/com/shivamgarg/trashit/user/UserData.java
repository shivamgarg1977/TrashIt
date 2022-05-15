package com.shivamgarg.trashit.user;

public class UserData {

    private String fullName,email,password,address,profilePic,phoneNumber;

    public UserData(){ }
    public UserData(String fullName, String email, String password, String address, String profilePic, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.profilePic = profilePic;
        this.phoneNumber = phoneNumber;
    }

    public UserData(String fullName, String email, String password, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber=phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
