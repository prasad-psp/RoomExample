package com.example.roomexample.Models;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addStudent(Student student);

    @Query("select * from student")
    List<Student> getAllStudents();

    @Query("select * from student WHERE mobile = :mobilePass")
    List<Student> getStudent(String mobilePass);

    @Query("delete from student")
    void deleteAllStudents();

    @Query("DELETE from student WHERE mobile = :mobilePass")
    void deleteStudent(String mobilePass);

    @Query("UPDATE student SET name =:namePass , age =:agePass WHERE mobile =:mobilePass")
    int updateStudent(String namePass,int agePass,String mobilePass);


}
