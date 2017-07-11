package model;

/**
 * Created by uasso on 07/07/2017.
 */
public class UserData {
    private final String firstname;
    String lastname;
    String address;
    String homephone;
    String email;

    public UserData (String firstname, String lastname, String address, String homephone, String email) {
        this.firstname =firstname;
        this.lastname = lastname;
        this.address =address;
        this.homephone = homephone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }
}
