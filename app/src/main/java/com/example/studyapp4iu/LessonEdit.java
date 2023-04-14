package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;


public class LessonEdit extends AppCompatActivity {

    static String kursID2 ="";
    public static String courseAuswahl = "";
    public static ArrayList courseAuswahlReduziert = new ArrayList();


    //Methode zum Prüfen ob die Eingabe nummerisch ist
    private boolean istNummerisch(String str) {
        if (str == null) {return false;}
        try { int d = Integer.parseInt(str);} catch (NumberFormatException nfe) { return false; }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_edit);

//SpinnerObjekt für Kursauswahl innerhalb von Lerneinheiten
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerSelectCourse);

//Array Adapter für Kursauswahl oben innerhalb von Lerneinheiten
        if(Courses.courseListeArray.size() > 0){

            ArrayAdapter<Courses> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Courses.courseListeArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCourses.setAdapter(adapter);
        }


//Dient zur Übergabe des Ausgewählten Objekt an die neue Lerneinheit
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

//Hier erzeuge ich mir den String von dem Auswahlelement in Kurs Edit
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          courseAuswahl = adapterView.getItemAtPosition(i).toString();
           }
          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {
          }
          }
        );

//Anzeige des Spinner aus Lessons als ein String und durch Substring auseinandergenommen

        TextView textViewLesson1 = (TextView) findViewById(R.id.editLesson1);
        int stelle = Lesson.lessonUebergabe.indexOf("#");
        textViewLesson1.setText(Lesson.lessonUebergabe.substring(0, stelle));

        TextView textViewLesson2 = (TextView) findViewById(R.id.editLesson2);
        int stelleZwei = Lesson.lessonUebergabe.indexOf('#', stelle + 1);
        textViewLesson2.setText(Lesson.lessonUebergabe.substring(stelle+1, stelleZwei));

        //Dargestellte Zeit in Activitiy
        TextView textViewLesson3 = (TextView) findViewById(R.id.editLesson3);
        int stelleDrei = Lesson.lessonUebergabe.indexOf('#', stelleZwei + 1);
        textViewLesson3.setText(Lesson.lessonUebergabe.substring(stelleZwei+1, stelleDrei));

        //TextView textViewLesson4 = (TextView) findViewById(R.id.editLesson4);
        int stelleVier = Lesson.lessonUebergabe.indexOf('#', stelleDrei + 1);
        //textViewLesson4.setText(Lesson.lessonUebergabe.substring(stelleDrei+1, stelleVier));


//Hier wird die aktuell zugehörige KursID aus dem Übergabestring extrahiert und in
        String relatedCourseID = Lesson.lessonUebergabe.substring(Lesson.lessonUebergabe.length()-5,Lesson.lessonUebergabe.length());
        String relatedCourseID2 = relatedCourseID.replace("#", "");

//Hier wird das Kurs-Array durchsuchen um abhängig davon den momentanen Kurs anzuzeigen
// und im Anschluss den Kurs in ein einzelnes Array zu überführen
        for(int i = 0; i < Courses.courseListeArray.size(); i++) {

            if(Courses.courseListeArray.get(i).toString().contains(relatedCourseID2)) {
                courseAuswahlReduziert.clear();
                courseAuswahlReduziert.add(Courses.courseListeArray.get(i));
            }

        }
//Darstellung des zugehörigen Kurses aus der Kurs Array Liste
        TextView textViewLesson5 = (TextView) findViewById(R.id.editLesson5);
        textViewLesson5.setText(Arrays.toString(courseAuswahlReduziert.toArray()));
    }

//Buttonfunktions delete
    public void onClickDeleteLesson(View button) {

//Löscht das aktuelle Lernobjekt aus dem Array
        for(int i = 0; i < Lesson.lessonListeArray.size(); i++) {
            if (Lesson.lessonListeArray.get(i).toString().contains(Lesson.lessonUebergabe)) {
                Lesson.lessonListeArray.remove(i);

            }

//Seitenwechsel auf Kurse zurück
            Intent changeIntent = new Intent(LessonEdit.this, Lesson.class);
            startActivity(changeIntent);
        }
    }

//Buttonfunktions Lesson speichern,
    public void onClickChangeLesson (View button) {

        TextView textViewLesson1 = (TextView) findViewById(R.id.editLesson1);
        String lessonNo = textViewLesson1.getText().toString();
        TextView textViewLesson2 = (TextView) findViewById(R.id.editLesson2);
        String lessonTitle =  textViewLesson2.getText().toString();
        TextView textViewLesson3 = (TextView) findViewById(R.id.editLesson3);
        String lessonTime =  textViewLesson3.getText().toString();
        String lessonTimeSet;

        if (Integer.parseInt(lessonTime) > 0) {
            lessonTimeSet = "true";
        } else {
            lessonTimeSet = "false";
        }


//hier wird die Kurs ID abhängig von dem im Spinner ausgewählten Element gesetz
        String kursID = courseAuswahl.substring(courseAuswahl.length()-5,courseAuswahl.length());
        String kursID2 = kursID.replace("#", "");
        String courseRelated = kursID2;



        for(int i = 0; i < Lesson.lessonListeArray.size(); i++) {
            if(Lesson.lessonListeArray.get(i).toString().contains(Lesson.lessonUebergabe)) {

//ID des Objektes soll durch den Nutzer nicht veränderbar sein
                String lessonID = Lesson.lessonUebergabe.substring(Lesson.lessonUebergabe.length()-5,Lesson.lessonUebergabe.length());
//Übergbit die veränderten Daten an die ArrayList
                Lesson.lessonListeArray.set(i,lessonNo+"#"+lessonTitle+"#"+lessonTime+ "#"+lessonTimeSet+"#" +courseRelated+"#");

            }
        }


//Ausgabe Toast Kurs geändert
        Toast.makeText(LessonEdit.this,
                R.string.buttonChangeLessons2,
                Toast.LENGTH_LONG ).show();


// Ausgabe des Inhalts von lessonListeArray
//        Log.d("#####Debug on EditLesson#####", "Inhalt von lessonListeArray in LessonEdit:" + Arrays.toString(Lesson.lessonListeArray.toArray()));

//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(LessonEdit.this, Lesson.class);
        startActivity(changeIntent);

    }



}