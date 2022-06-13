package com.mesinger.atoszadatak15.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workers")
public class Worker implements Parcelable {



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


    protected Worker(Parcel in) {
        id = in.readInt();
        name = in.readString();
        surname = in.readString();
        workPosition = in.readString();
        oib = in.readString();
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(workPosition);
        parcel.writeString(oib);
    }


}
