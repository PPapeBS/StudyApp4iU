package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;
import android.os.Bundle;


public class LessonEdit extends AppCompatActivity {

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

//Anzeige des Spinner aus Lessons als ein String und durch Substring auseinandergenommen

        TextView textViewLesson1 = (TextView) findViewById(R.id.editLesson1);
        int stelle = Lesson.lessonUebergabe.indexOf("#");
        textViewLesson1.setText(Lesson.lessonUebergabe.substring(0, stelle));

        TextView textViewLesson2 = (TextView) findViewById(R.id.editLesson2);
        int stelleZwei = Lesson.lessonUebergabe.indexOf('#', stelle + 1);
        textViewLesson2.setText(Lesson.lessonUebergabe.substring(stelle+1, stelleZwei));

        TextView textViewLesson3 = (TextView) findViewById(R.id.editLesson3);
        int stelleDrei = Lesson.lessonUebergabe.indexOf('#', stelleZwei + 1);
        textViewLesson3.setText(Lesson.lessonUebergabe.substring(stelleZwei+1, stelleDrei));

        TextView textViewLesson4 = (TextView) findViewById(R.id.editLesson4);
        int stelleVier = Lesson.lessonUebergabe.indexOf('#', stelleDrei + 1);
        textViewLesson4.setText(Lesson.lessonUebergabe.substring(stelleDrei+1, stelleVier));

        TextView textViewLesson5 = (TextView) findViewById(R.id.editLesson5);
        int stelleFuenf = Lesson.lessonUebergabe.indexOf('#', stelleVier + 1);
        textViewLesson5.setText(Lesson.lessonUebergabe.substring(stelleVier+1, stelleFuenf));


    }
//Buttonfunktions delete
    public void onClickDeleteLesson(View button) {

//Löscht das aktuelle Lernobjekt aus dem Array
        for(int i = 0; i < Lesson.lessonListeArray.size() -1; i++) {
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


        //return "Nr:"+lessonNo+"#"+lessonTitle +"#Time:"+lessonTime+ "#"+lessonTimeSet+"#"+courseRelated+"#"

        TextView textViewLesson1 = (TextView) findViewById(R.id.editLesson1);
        String lessonNo = textViewLesson1.getText().toString();
        TextView textViewLesson2 = (TextView) findViewById(R.id.editLesson2);
        String lessonTitle =  textViewLesson2.getText().toString();
        TextView textViewLesson3 = (TextView) findViewById(R.id.editLesson3);
        String lessonTime =  textViewLesson3.getText().toString();
        TextView textViewLesson4 = (TextView) findViewById(R.id.editLesson4);
        String lessonTimeSet = textViewLesson4.getText().toString();
        TextView textViewLesson5 = (TextView) findViewById(R.id.editLesson5);
        String courseRelated = textViewLesson5.getText().toString();

        for(int i = 0; i < Lesson.lessonListeArray.size() -1; i++) {
            if(Lesson.lessonListeArray.get(i).toString().contains(Lesson.lessonUebergabe)) {

//ID des Objektes soll durch den Nutzer nicht veränderbar sein
                String lessonID = Lesson.lessonUebergabe.substring(Lesson.lessonUebergabe.length()-5,Lesson.lessonUebergabe.length());
//Übergbit die veränderten Daten an die ArrayList
                //               return "Nr:"+lessonNo+"#"+lessonTitle +"#Time:"+lessonTime+ "#"+lessonTimeSet+"#"+courseRelated+"#"
                Lesson.lessonListeArray.set(i,lessonNo+"#"+lessonTitle+"#"+lessonTime+ "#"+lessonTimeSet+"#" +courseRelated+"#");
            }
        }


//Ausgabe Toast Kurs geändert
        Toast.makeText(LessonEdit.this,
                R.string.buttonChangeLessons2,
                Toast.LENGTH_LONG ).show();


// Ausgabe des Inhalts von lessonListeArray
        Log.d("#####Debug on EditLesson#####", "Inhalt von lessonListeArray in LessonEdit:" +"\n" + Arrays.toString(Lesson.lessonListeArray.toArray()));

//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(LessonEdit.this, Lesson.class);
        startActivity(changeIntent);

    }



}