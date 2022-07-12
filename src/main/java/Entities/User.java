package Entities;

import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String passcode;
    private String userAboutMe;
    private String dob;
    private String imageFormat;
    private int politicalView;

    public User() {}

    public User(String username, String passcode) {
        this.username = username;
        this.passcode = passcode;
    }

//    public User(int userId, String firstName, String lastName, String emailAddress, String username,
//                String dob) {
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.username = username;
//        this.dob = dob;
//    }

    public User(int userId, String firstName, String lastName, String emailAddress, String username, String passcode,
                String userAboutMe, String dob, String imageFormat, int politicalView) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.username = username;
        this.passcode = passcode;
        this.userAboutMe = userAboutMe;
        this.dob = dob;
        this.imageFormat = imageFormat;
        this.politicalView = politicalView;
    }

    // Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(dob, user.dob) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && emailAddress.equals(user.emailAddress) && username.equals(user.username) && passcode.equals(user.passcode)
                && userAboutMe.equals(user.userAboutMe) && imageFormat.equals(user.imageFormat) && politicalView == user.politicalView;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, emailAddress, username, passcode, userAboutMe, dob, imageFormat, politicalView);
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getUserAboutMe() {
        return userAboutMe;
    }

    public void setUserAboutMe(String userAboutMe) {
        this.userAboutMe = userAboutMe;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public int getPoliticalView() {
        return politicalView;
    }

    public void setPoliticalView(int politicalView) {
        this.politicalView = politicalView;
    }
}
