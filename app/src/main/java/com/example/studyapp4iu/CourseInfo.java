package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CourseInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);


//Anzeige des Spinner aus Courses als ein String und durch Substring auseinandergenommen
        TextView textViewCours1 = (TextView) findViewById(R.id.textViewCours1);
        int stelle = Courses.courseUebergabe.indexOf("#");
        textViewCours1.setText(Courses.courseUebergabe.substring(0, stelle));

        TextView textViewCours2 = (TextView) findViewById(R.id.textViewCours2);
        int stelleZwei = Courses.courseUebergabe.indexOf('#', stelle + 1);
        textViewCours2.setText(Courses.courseUebergabe.substring(stelle+1, stelleZwei));

        TextView textViewCours3 = (TextView) findViewById(R.id.textViewCours3);
        int stelleDrei = Courses.courseUebergabe.indexOf('#', stelleZwei + 1);
        textViewCours3.setText(Courses.courseUebergabe.substring(stelleZwei+1, stelleDrei));

        TextView textViewCours4 = (TextView) findViewById(R.id.textViewCours4);
        int stelleVier = Courses.courseUebergabe.indexOf('#', stelleDrei + 1);
        textViewCours4.setText(Courses.courseUebergabe.substring(stelleDrei+1, stelleVier));

        TextView textViewCours5 = (TextView) findViewById(R.id.textViewCours5);
        int stelleFuenf = Courses.courseUebergabe.indexOf('#', stelleVier + 1);
        textViewCours5.setText(Courses.courseUebergabe.substring(stelleVier+1, stelleFuenf));




    }

}