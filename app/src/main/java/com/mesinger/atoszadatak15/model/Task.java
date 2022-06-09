package com.mesinger.atoszadatak15.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mesinger.atoszadatak15.data.TypeConverters;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Entity(tableName = "tasks")
public class Task implements Parcelable{


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String type;
    private String status;
    private int complexity;
    private int timeSpent;
    private String startDateTime;
    private String  endDateTime;

    public Task(String name, String description, String type, String status, int complexity, int timeSpent, String startDateTime, String endDateTime) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.complexity = complexity;
        this.timeSpent = timeSpent;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Task() {

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", complexity=" + complexity +
                ", timeSpent=" + timeSpent +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                '}';
    }

    protected Task(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        type = in.readString();
        status = in.readString();
        complexity = in.readInt();
        timeSpent = in.readInt();
        startDateTime = in.readString();
        endDateTime = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(type);
        parcel.writeString(status);
        parcel.writeInt(complexity);
        parcel.writeInt(timeSpent);
        parcel.writeString(startDateTime);
        parcel.writeString(endDateTime);
    }
}
