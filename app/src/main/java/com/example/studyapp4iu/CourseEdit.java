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

//Übergebenen Daten aus Courses
/*
public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseUebergabe = adapterView.getItemAtPosition(i).toString();
            }
//Noch übernommen von dem MailHandling, anpassen auf die Objetveränderung
    public void changeCourse( View button) {
        EditText editCourse1 =
                (EditText) findViewById(R.id.editCourse1 );
 */

//Buttonfunktions delete Template
    public void onClickDeleteCourse(View button) {
        Toast.makeText(CourseEdit.this,
                R.string.buttonDeletCourse2,
                Toast.LENGTH_LONG ).show();

//Seitenwechsel zurück
        /*
        Intent changeIntent = new Intent(CourseEdit.this, Courses.class);
        startActivity(changeIntent);
     */

    }

///Hier muss ich jetzt irgendwie Kurs in das Array von Kursübersicht bekommen
//Buttonfunktions Kurs speichern,
    public void onClickChangeCourse (View button) {

    for(int i = 0; i < Courses.courseListeArray.size() -1; i++) {
        if(Courses.courseListeArray.get(i).toString().contains(Courses.courseUebergabe)) {
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
            String courseID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-6,Courses.courseUebergabe.length());
            Courses.courseListeArray.set(i,"Nr: "+courseNo+"# "+courseNameShort +"# IU:"+courseIubhId+ "# Sem:"+courseSem+"# " +courseNameLong+"# "+courseID+"# " );



        }
    }

//KOPIE AUS COURSES JAVA
        /*
        return

  */
//Seitenwechsel Kurse Zurück, damit die Activity nicht zerstört wird wie beim zurück button
            Intent changeIntent = new Intent(CourseEdit.this, Courses.class);
            startActivity(changeIntent);

}












}