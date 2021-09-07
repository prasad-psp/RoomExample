package com.example.roomexample.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "student" , indices = {@Index(value = {"mobile"},unique = true)})
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @ColumnInfo(name = "mobile")
    private String mobile;


    public Student(String name, int age, @NonNull String mobile) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
    }

    public Student() {
    }

    public Student(int id, String name, int age, @NonNull String mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
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

    @NonNull
    public String getMobile() {
        return mobile;
    }

    public void setMobile(@NonNull String mobile) {
        this.mobile = mobile;
    }
}
