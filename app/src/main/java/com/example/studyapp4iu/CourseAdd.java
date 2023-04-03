package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class CourseAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);



    }



    public void onClickAddChangeCourse (View button) {
//AUslesen aus den Textfeldern und dann einfügen in das Objekt funktioniert noch nicht

        TextView textViewCours1 = (TextView) findViewById(R.id.addCourse1);
        String courseNo = textViewCours1.getText().toString();
        TextView textViewCours2 = (TextView) findViewById(R.id.addCourse2);
        String courseNameShort =  textViewCours2.getText().toString();
        TextView textViewCours3 = (TextView) findViewById(R.id.addCourse3);
        String courseIubhId =  textViewCours3.getText().toString();
        TextView textViewCours4 = (TextView) findViewById(R.id.addCourse4);
        String courseSem = textViewCours4.getText().toString();
        TextView textViewCours5 = (TextView) findViewById(R.id.addCourse5);
        String courseNameLong = textViewCours5.getText().toString();

        Courses newCourse =new Courses();
        newCourse.setCourseID(4444);
        newCourse.setCourseNo(4);
        newCourse.setCourseNameShort("Mathe");
        newCourse.setCourseIubhId("MATH01");
        newCourse.setCourseSem(4);
        newCourse.setCourseNameLong("Mathe Einführung");

        Courses.courseListeArray.add(newCourse);



//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(CourseAdd.this, Courses.class);
        startActivity(changeIntent);

    }






}