package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "UserActive")
public class UserActive extends Model {

    @Column(name = "firstName")
    public String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column(name = "team")
    public String team;

    public UserActive(){
        super();
    }

    public UserActive(String firstName, String lastName, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getTeam()  {
        return this.team;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    /** запрос в БД, врзвращающий всех пользователей в виде List*/
    public static List<UserActive> getAllUsers() {return new Select().from(UserActive.class).execute();}
}
