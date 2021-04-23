package com.example.roomdatabasesimplecrud.Room;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentInformation {

    @PrimaryKey (autoGenerate = true)
    int id;

    String name;
    int age;

    public StudentInformation(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
