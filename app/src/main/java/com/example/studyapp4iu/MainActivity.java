package com.example.studyapp4iu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Toast;
import com.example.studyapp4iu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

// Programm start der MainActivity wird benötigt damit irgendwas angezeigt wird
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//Erzeugen von Dummy Objekte für Kurse, Lessons, Zeiteinheiten
// Legt Kursobjekt an, aber nur wenn die Activity noch nie aufgerufen wurde

        if (savedInstanceState == null) {
        //Erzeugen von Dummy Objekte für Kurse, Lessons, Zeiteinheiten
        // Legt Kursobjekt an.

        Courses bwl= new Courses(1000, 1, "BWL","Betriebswirtschaft 1","BBWL01",1);
        Courses.courseListeArray.add(bwl);
        Courses java= new Courses(1001, 2, "JAVA","Einführung in die Programmierung mit Java","IOBP01",2);
        Courses.courseListeArray.add(java);
        Courses recht= new Courses(1002, 3, "RECHT 1","Einführung in Recht","BREC01",1);
        Courses.courseListeArray.add(recht);

// Legt Lerneinheitobjekt an.
        Lesson lesson1 =new Lesson(5000, 1, "Stakeholdermanagement", 1000, 60, true);
        Lesson.lessonListeArray.add(lesson1);
        Lesson lesson2 =new Lesson(5001, 2, "Shareholdervalue", 1000, 0, false);
        Lesson.lessonListeArray.add(lesson2);
        Lesson lesson3 =new Lesson(5002, 3, "Grundgesetz", 1002, 99, true);
        Lesson.lessonListeArray.add(lesson3);
        }

    }

    public void onClickButton(View button) {
        Toast.makeText(MainActivity.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();
    }
//Seitenwechsel Kurse
     public void onClickCourses(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Courses.class);
        startActivity(changeIntent);
    }

    //Seitenwechsel Lerneinheiten
    public void onClickLesson(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Lesson.class);
        startActivity(changeIntent);    }

    //Seitenwechsel Timer
    public void onClickTimer(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Timer.class);
        startActivity(changeIntent);    }
}

