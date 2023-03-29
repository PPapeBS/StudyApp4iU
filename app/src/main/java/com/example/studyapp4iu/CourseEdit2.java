package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class CourseEdit2 extends AppCompatActivity {

//Instanzvariable mit der ich auf die Funktionen von Courses zugreifen kann
    private Courses coursesInstanz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit2);

        coursesInstanz = new Courses();
        // Ausgabe des Inhalts von courseListeArray
        Log.d("#####Debug#####", "Inhalt von courseListeArray in CoursesEdit2:" +"\n" + Arrays.toString(coursesInstanz.courseListeArray.toArray()));

        //Anzeige des Spinner aus Courses als ein String und durch Substring auseinandergenommen
        TextView textViewCours1 = (TextView) findViewById(R.id.textViewCours1);
        int stelle = Courses.courseUebergabe.indexOf("#");
        textViewCours1.setText(Courses.courseUebergabe.substring(0, stelle));
    }






//Buttonfunktions Kurs speichern,
    public void onClickChangeCourse (View button) {

//Neues Kursobjekt LEER, damit ich aufs Array zugreifen kann und Mathe hinzufügen kann.
        coursesInstanz = new Courses();
        ArrayList<Courses> courseListeArray = coursesInstanz.getCourseListeArray();

        // Ausgabe des Inhalts von courseListeArray
        Log.d("#####Debug#####", "Inhalt von courseListeArray in CoursesEdit2:" +"\n" + Arrays.toString(coursesInstanz.courseListeArray.toArray()));


//LEGT ein neues Objekt
        Courses BWL= new Courses(8888, 5, "VERÄNDERT 12","VERÄNDERT in Recht","BREC01",1);
        coursesInstanz.courseListeArray.add(BWL);

        // Ausgabe des Inhalts von courseListeArray
        Log.d("#####Debug#####", "Inhalt von courseListeArray in CoursesEdit2:" +"\n" + Arrays.toString(coursesInstanz.courseListeArray.toArray()));



    }

/*
//So sieht das anlegen von BWL in Courses aus.
      Courses BWL= new Courses(1111, 1, "BWL","Betriebswirtschaft 1","BBWL01",1);
*/


//Buttonfunktions delete Template
    public void onClickDeleteCourse(View button) {
        Toast.makeText(CourseEdit2.this,
                R.string.buttonDeletCourse2,
                Toast.LENGTH_LONG ).show();

//Seitenwechsel zurück
        /*
        Intent changeIntent = new Intent(CourseEdit.this, Courses.class);
        startActivity(changeIntent);
     */

    }
}