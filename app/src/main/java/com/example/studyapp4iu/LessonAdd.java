package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LessonAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_add);
    }
//Methode zum Prüfen ob die Eingabe nummerisch ist
    private boolean istNummerisch(String str) {
        if (str == null) {return false;}
        try { int d = Integer.parseInt(str);} catch (NumberFormatException nfe) { return false; }
        return true;
    }

    private int lessonId;
    private int lessonNo;
    private String lessonTitle;
    private int courseRelated;
    private int lessonTime;
    private boolean lessonTimeSet;


    public void onClickAddLesson (View button) {
//Auslesen aus den Textfeldern
        TextView textViewCours1 = (TextView) findViewById(R.id.addLesson1);
        String lessonNo = textViewCours1.getText().toString();
        TextView textViewCours2 = (TextView) findViewById(R.id.addLesson2);
        String lessonTitle = textViewCours2.getText().toString();

        //Dargestellte Zeit in Activitiy
        TextView textViewCours3 = (TextView) findViewById(R.id.addLesson3);
        String lessonTime = textViewCours3.getText().toString();
        TextView textViewCours4 = (TextView) findViewById(R.id.addLesson4);
        String lessonTimeSet = textViewCours4.getText().toString();
        TextView textViewCours5 = (TextView) findViewById(R.id.addLesson5);
        String courseRelated = textViewCours5.getText().toString();

//Erstellen des neuen Leeren CourseObjektes
        Lesson newLesson = new Lesson();

//Vergabe der technische ID, passiert automatisch
// Default-Wert für den Fall, dass die Liste leer ist.
        int newLessonId = 1;
        if (!Lesson.lessonListeArray.isEmpty()) {
// Wenn die Liste nicht leer ist, setzen Sie die neue Kurs-ID auf die Größe der Liste plus 1.
            newLessonId = 4999 + Lesson.lessonListeArray.size() + 1;}

//Hier beginnt die Prüfung ob die eingegeben Werte der Textfelder das richtige Format haben

//Prüfe, ob lessonNo nur aus Zahlen besteht und zwischen 1 und 2 Stellen lang ist
        if (istNummerisch(lessonNo) && lessonNo.length() >= 1 && lessonNo.length() <= 2) {
            newLesson.setLessonNo(Integer.parseInt(lessonNo)); } else {
            // Wenn LessonNo weniger als 1 oder mehr als 2 Stellen hat oder keine Zahl ist, zeige eine Fehlermeldung und beende die Methode
            Toast.makeText(this, R.string.errorLessonNo, Toast.LENGTH_SHORT).show(); return;        }
// Wenn lessonTitel leer ist oder mehr als 81 Zeichen hat, zeige eine Fehlermeldung und beende die Methode
        if (lessonTitle.isEmpty() || lessonTitle.length() < 8|| lessonTitle.length() > 81 ) {
            Toast.makeText(this, R.string.errorLessonTitle, Toast.LENGTH_SHORT).show(); return;        }
// Wenn die lessonTime leer ist oder nicht nur aus Zahlen bestehte, zeige eine Fehlermeldung und beende die Methode
        if (istNummerisch(lessonTime) && lessonTime.length() >= 1 && lessonTime.length() <= 31536000 && Integer.parseInt(lessonTime) > 0 ) {
            newLesson.setLessonTime(Integer.parseInt(lessonTime)); } else {
            // Wenn LessonNo weniger als 1 oder mehr als 2 Stellen hat oder keine Zahl ist, zeige eine Fehlermeldung und beende die Methode
            Toast.makeText(this, R.string.errorLessonTime, Toast.LENGTH_SHORT).show(); return;        }
//Prüfung von Time gesetzt nicht durchführen, da es in den Methoden selbst passieren soll.
// Wenn courseRelated nicht 4 Nummern lang ist, zeige eine Fehlermeldung und beendet die Methode
        if (istNummerisch(courseRelated) && courseRelated.length() == 4 && Integer.parseInt(courseRelated) >999 && Integer.parseInt(courseRelated) < 5000) {
            newLesson.setCourseRelated(Integer.parseInt(courseRelated)); } else {
            Toast.makeText(this, R.string.errorCourseRelated, Toast.LENGTH_SHORT).show(); return;        }

        newLesson.setLessonId(newLessonId);
        newLesson.setLessonTitle(lessonTitle);
        newLesson.setLessonTime(Integer.parseInt(lessonTime));
        newLesson.setCourseRelated(Integer.parseInt(courseRelated));

        Lesson.lessonListeArray.add(newLesson);


//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(LessonAdd.this, Lesson.class);
        startActivity(changeIntent);
    }

//DummyLesson hinzufügen
    public void onClickAddLessonDummy (View button){

        Lesson lesson4 =new Lesson(1, 1, "Java Einführung in die Klassen", 1000, 6000, true);
        int lesson4iD =  4999 + Lesson.lessonListeArray.size() + 1;
        lesson4.setLessonId(lesson4iD);
        int lessonCourseNo = Lesson.lessonListeArray.size() + 1;
        lesson4.setLessonNo((lessonCourseNo));
        Lesson.lessonListeArray.add(lesson4);

//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(LessonAdd.this, Lesson.class);
        startActivity(changeIntent);
    }


}