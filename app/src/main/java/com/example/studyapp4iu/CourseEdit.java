package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class CourseEdit extends AppCompatActivity {

    //Methode zum Prüfen ob die Eingabe nummerisch ist
    private boolean istNummerisch(String str) {
        if (str == null) {return false;}
        try { int d = Integer.parseInt(str);} catch (NumberFormatException nfe) { return false; }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

//Anzeige des Spinner aus Courses als ein String und durch Substring auseinandergenommen

        TextView textViewCours1 = (TextView) findViewById(R.id.editCourse1);
        int stelle = Courses.courseUebergabe.indexOf("#");
        textViewCours1.setText(Courses.courseUebergabe.substring(0, stelle));

        TextView textViewCours2 = (TextView) findViewById(R.id.editCourse2);
        int stelleZwei = Courses.courseUebergabe.indexOf('#', stelle + 1);
        textViewCours2.setText(Courses.courseUebergabe.substring(stelle+1, stelleZwei));

        TextView textViewCours3 = (TextView) findViewById(R.id.editCourse3);
        int stelleDrei = Courses.courseUebergabe.indexOf('#', stelleZwei + 1);
        textViewCours3.setText(Courses.courseUebergabe.substring(stelleZwei+1, stelleDrei));

        TextView textViewCours4 = (TextView) findViewById(R.id.editCourse4);
        int stelleVier = Courses.courseUebergabe.indexOf('#', stelleDrei + 1);
        textViewCours4.setText(Courses.courseUebergabe.substring(stelleDrei+1, stelleVier));

        TextView textViewCours5 = (TextView) findViewById(R.id.editCourse5);
        int stelleFuenf = Courses.courseUebergabe.indexOf('#', stelleVier + 1);
        textViewCours5.setText(Courses.courseUebergabe.substring(stelleVier+1, stelleFuenf));



    }
//Buttonfunktions delete
    public void onClickDeleteCourse(View button) {

//Löscht das aktuelle Kursobjekt aus dem Array
        for(int i = 0; i < Courses.courseListeArray.size() -1; i++) {
            if (Courses.courseListeArray.get(i).toString().contains(Courses.courseUebergabe)) {
                Courses.courseListeArray.remove(i);
            }

//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(CourseEdit.this, Courses.class);
        startActivity(changeIntent);
        }
    }

//Buttonfunktions Kurs speichern,
    public void onClickChangeCourse (View button) {

        TextView textViewCours1 = (TextView) findViewById(R.id.editCourse1);
        String courseNo = textViewCours1.getText().toString();
        TextView textViewCours2 = (TextView) findViewById(R.id.editCourse2);
        String courseNameShort =  textViewCours2.getText().toString();
        TextView textViewCours3 = (TextView) findViewById(R.id.editCourse3);
        String courseIubhId =  textViewCours3.getText().toString();
        TextView textViewCours4 = (TextView) findViewById(R.id.editCourse4);
        String courseSem = textViewCours4.getText().toString();
        TextView textViewCours5 = (TextView) findViewById(R.id.editCourse5);
        String courseNameLong = textViewCours5.getText().toString();


        for(int i = 0; i < Courses.courseListeArray.size() -1; i++) {
        if(Courses.courseListeArray.get(i).toString().contains(Courses.courseUebergabe)) {

//ID des Objektes soll durch den Nutzer nicht veränderbar sein
            String courseID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-6,Courses.courseUebergabe.length());
//Übergbit die veränderten Daten an die ArrayList
            Courses.courseListeArray.set(i,courseNo+courseNameShort +courseIubhId+courseSem+courseNameLong+courseID);
        }
    }
// Ausgabe des Inhalts von courseListeArray
            Log.d("#####Debug on EditKurs#####", "Inhalt von courseListeArray in CoursEdit:" +"\n" + Arrays.toString(Courses.courseListeArray.toArray()));

//Seitenwechsel auf Kurse zurück
            Intent changeIntent = new Intent(CourseEdit.this, Courses.class);
            startActivity(changeIntent);

}












}