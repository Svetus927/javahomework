package model;

/**
 * Created by uasso on 10/11/2017.
 */
public class MantisUserData {
    public int id;
    public String username;
    public String realname;
    public String email;
    public String password;


    public MantisUserData( ) {

    }
    // конструктор обычный. если переделывать во fluent интерфейс то не понадобится, протсто каждый раз возвращать сам объект
    public MantisUserData( String username, String realname, String email, String password) {
        this.username = username;
        this.realname =  realname;
        this.email = email;
        this.password = password;
    }

    public int Id() {
        return id;
    }

    public String Username() {
        return username;
    }

    public String Realname() {
        return realname;
    }

    public String Email() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String Password() {

        return password;
    }
}
