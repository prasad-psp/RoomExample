package com.example.roomexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomexample.Models.Student;
import com.example.roomexample.Models.StudentDao;

@Database(entities = Student.class,version = 2,exportSchema = false)
public abstract  class StudentDatabase extends RoomDatabase {


    public static StudentDatabase instance;
    public static final String DB_NAME = "student_db";

    public static synchronized StudentDatabase getInstance(Context context) {

        if(instance==null) {

            instance = Room.databaseBuilder(context,StudentDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


    public abstract StudentDao studentDao();
}
