package model;

import javax.persistence.*;

/**
 * Created by uasso on 07/07/2017.
 */
@Entity // для маппинга запросов  на языке OML
@Table(name="addressbook")
public class UserData {
    @Id // пометка id
    @Column() // маппинг столбца раз называется также то поле name не нужно
    int id;

    @Column(name="firstname")
    String firstname;

    @Column(name="lastname")
    String lastname;

    String address;
    String homephone;
    String mobilephone;
    String workphone;
    String email;

    @Transient // для пропуска привязки-маппинга к столбу т.к. такого столбца нет
    String group; // для привязки контакта к опр группе

// конструктор  UserData нам больше не нужен, тк будем использоват билдер с флюент интерфейсами  и сеттить все в любой момент
  /*  public UserData (String firstname, String lastname, String address, String homephone, String email) {
        this.firstname =firstname;
        this.lastname = lastname;
        this.address =address;
        this.homephone = homephone;
        this.email = email;
    }  */

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public UserData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public UserData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public int getId() {
        return id;
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
