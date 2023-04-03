package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CourseAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);

    }
//Methode zum Prüfen ob die Eingabe nummerisch ist
    private boolean istNummerisch(String str) {
        if (str == null) {return false;}
        try { int d = Integer.parseInt(str);} catch (NumberFormatException nfe) { return false; }
        return true;
    }

    public void onClickAddCourse (View button) {
//Auslesen aus den Textfeldern
        TextView textViewCours1 = (TextView) findViewById(R.id.addCourse1);
        String courseNo = textViewCours1.getText().toString();
        TextView textViewCours2 = (TextView) findViewById(R.id.addCourse2);
        String courseNameShort = textViewCours2.getText().toString();
        TextView textViewCours3 = (TextView) findViewById(R.id.addCourse3);
        String courseIubhId = textViewCours3.getText().toString();
        TextView textViewCours4 = (TextView) findViewById(R.id.addCourse4);
        String courseSem = textViewCours4.getText().toString();
        TextView textViewCours5 = (TextView) findViewById(R.id.addCourse5);
        String courseNameLong = textViewCours5.getText().toString();

//Erstellen des neuen Leeren CourseObjektes
        Courses newCourse = new Courses();

//Vergabe der technische ID, passiert automatisch
// Default-Wert für den Fall, dass die Liste leer ist.
        int newCourseId = 1;
        if (!Courses.courseListeArray.isEmpty()) {
// Wenn die Liste nicht leer ist, setzen Sie die neue Kurs-ID auf die Größe der Liste plus 1.
                newCourseId = 999 + Courses.courseListeArray.size() + 1;}

//Hier beginnt die Prüfung ob die eingegeben Werte der Textfelder das richtige Format haben

//Prüfe, ob courseNo nur aus Zahlen besteht und zwischen 1 und 2 Stellen lang ist
        if (istNummerisch(courseNo) && courseNo.length() >= 1 && courseNo.length() <= 2) {
            newCourse.setCourseNo(Integer.parseInt(courseNo)); } else {
        // Wenn courseNo weniger als 1 oder mehr als 2 Stellen hat oder keine Zahl ist, zeige eine Fehlermeldung und beende die Methode
            Toast.makeText(this, R.string.errorCourseNo, Toast.LENGTH_SHORT).show(); return;        }
// Wenn courseNameShort leer ist oder mehr als 8 Zeichen hat, zeige eine Fehlermeldung und beende die Methode
        if (courseNameShort.isEmpty() || courseNameShort.length() > 8) {
            Toast.makeText(this, R.string.errorCourseNameShort, Toast.LENGTH_SHORT).show(); return;        }
// Wenn courseIubhId leer ist oder mehr als 8 Zeichen hat, zeige eine Fehlermeldung und beende die Methode
        if (courseIubhId.isEmpty() || courseIubhId.length() > 8) {
            Toast.makeText(this, R.string.errorCourseIubhId, Toast.LENGTH_SHORT).show(); return;        }
// Prüfe, ob courseSem nur aus Zahlen besteht und maximal 2 Stellen lang ist
        if (istNummerisch(courseSem) && courseSem.length() <= 2) {
            newCourse.setCourseSem(Integer.parseInt(courseSem));  } else {
        // Wenn courseSem leer ist oder mehr als 2 Stellen hat oder keine Zahl ist, zeige eine Fehlermeldung und beende die Methode
            Toast.makeText(this, R.string.errorCourseSem, Toast.LENGTH_SHORT).show(); return;         }
// Wenn courseNameLong größer als 256 Zeichen hat, zeige eine Fehlermeldung und beendet die Methode
        if (courseNameLong.length() > 256) {
            Toast.makeText(this, R.string.errorCourseNameLong, Toast.LENGTH_SHORT).show(); return;        }

        newCourse.setCourseID(newCourseId);
        newCourse.setCourseNameShort(courseNameShort);
        newCourse.setCourseIubhId(courseIubhId);
        newCourse.setCourseSem(Integer.parseInt(courseSem));
        newCourse.setCourseNameLong(courseNameLong);

        Courses.courseListeArray.add(newCourse);


//Seitenwechsel auf Kurse zurück
        Intent changeIntent = new Intent(CourseAdd.this, Courses.class);
        startActivity(changeIntent);
    }
//Dummykurs hinzufügen
        public void onClickAddCourseDummy (View button){

            Courses mathe = new Courses(1, 1, "MATHE1", "Für Mathematik gibt es keine allgemein anerkannte Definition; heute wird sie üblicherweise als eine Wissenschaft beschrieben", "MATH01", 4);
            int matheCourseID =  999 + Courses.courseListeArray.size() + 1;
            mathe.setCourseID(matheCourseID);
            int matheCourseNo = Courses.courseListeArray.size() + 1;
            mathe.setCourseNo(matheCourseNo);
            Courses.courseListeArray.add(mathe);


//Seitenwechsel auf Kurse zurück
            Intent changeIntent = new Intent(CourseAdd.this, Courses.class);
            startActivity(changeIntent);
        }








}