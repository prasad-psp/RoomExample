package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roomexample.Models.Student;
import com.example.roomexample.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "RoomExample";
    ActivityMainBinding binding;


    Student student;
    StudentDatabase studentDatabase;

    List<Student> studentList,students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.insertBtn.setOnClickListener(this);
        binding.updateBtn.setOnClickListener(this);
        binding.deleteAllBtn.setOnClickListener(this);
        binding.deleteStudentBtn.setOnClickListener(this);
        binding.viewAllBtn.setOnClickListener(this);
        binding.viewStudentBtn.setOnClickListener(this);

        studentDatabase = StudentDatabase.getInstance(this);

        studentList = new ArrayList<>();
        students = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int view = v.getId();
                if(view == R.id.insertBtn) {
                    String insertString = binding.insertEdt.getText().toString();
                    String[] splitString = insertString.split(" ");

                    student = new Student(splitString[0],Integer.parseInt(splitString[1]),splitString[2]);
                    addStudent(student);
                }

                if(view == R.id.viewAllBtn) {
                    viewAllStudent();
                }

                if(view == R.id.viewStudentBtn) {
                    viewStudent(binding.viewStudentEdt.getText().toString());
                }

                if(view == R.id.updateBtn) {
                    String insertString = binding.updateEdt.getText().toString();
                    String[] splitString = insertString.split(" ");
                    updateStudent(splitString[0],Integer.parseInt(splitString[1]),splitString[2]);
                }

                if(view == R.id.deleteStudentBtn) {
                    deleteStudent(binding.deleteStudentEdt.getText().toString());
                }

                if(view == R.id.deleteAllBtn) {
                    deleteAllStudent();
                }
            }
        }).start();

    }






    private void addStudent(Student student) {

        long i= studentDatabase.studentDao().addStudent(student);
        if(i == -1) {
            Log.d(TAG, "addStudent details already exists ");
        }else {
            Log.d(TAG, "addStudent succ ");
        }
    }


    private void viewAllStudent(){

        if(studentList.size()>0){
            studentList.clear();
        }
        studentList = studentDatabase.studentDao().getAllStudents();

        for(int i=0; i<studentList.size();i++) {
            Log.d(TAG,"student id = "+studentList.get(i).getId()+" name = "+studentList.get(i).getName()+" age = "+studentList.get(i).getAge()+" mobile = "+
                    studentList.get(i).getMobile());
        }
    }

    private void viewStudent(String mobile) {
        if(students.size()>0){
            students.clear();
        }

        students = studentDatabase.studentDao().getStudent(mobile);

        for(int i=0;i<students.size();i++) {
            Log.d(TAG,"student id = "+students.get(i).getId()+" name = "+students.get(i).getName()+" age = "+students.get(i).getAge()+" mobile = "+
                    students.get(i).getMobile());
        }

    }

    private void deleteAllStudent() {
        studentDatabase.studentDao().deleteAllStudents();
        Log.d(TAG,"deleteAllStudent succ");
    }

    private void deleteStudent(String mobile) {
        studentDatabase.studentDao().deleteStudent(mobile);
        Log.d(TAG," deleteStudent succ");
    }

    private void updateStudent(String name,int age,String mobile) {
        int i = studentDatabase.studentDao().updateStudent(name,age,mobile);

        if(i==1) {
            Log.d(TAG, "updateStudent succ");
        }
        else {
            Log.d(TAG,"Update details failed");
        }
    }



}