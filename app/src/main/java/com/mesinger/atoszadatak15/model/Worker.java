package com.mesinger.atoszadatak15.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workers")
public class Worker {



    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;
    private String workPosition;
    private String oib;

    public Worker(int id, String name, String surname, String workPosition, String oib) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.workPosition = workPosition;
        this.oib = oib;
    }

    public Worker() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }
}
