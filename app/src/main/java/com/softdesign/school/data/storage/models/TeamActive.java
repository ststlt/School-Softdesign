package com.softdesign.school.data.storage.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "TeamActive")
public class TeamActive extends Model {

    @Column(name = "Name")
    public String mTeamName;

    public TeamActive(){
        super();
    }

    public TeamActive(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    public String getTeamName() {
        return this.mTeamName;
    }

    public void setTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    /** запрос в БД, врзвращающий все команды в виде List*/
    public static List<TeamActive> getAllTeams() {return new Select().from(TeamActive.class).execute();}

    /**Запрос в БД, возвращающий команду с названием name (возвращает объект Team)*/
    public static TeamActive getTeam(String name) {return new Select().from(TeamActive.class).where("Name=?", name).executeSingle();}
}
