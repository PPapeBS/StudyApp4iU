package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson extends AppCompatActivity {

    //Definition der Variablen
    private int lessonId;
    private int lessonNo;
    private String lessonTitle;
    private int courseRelated;
    private int lessonTime;
    private boolean lessonTimeSet;

    public static String lessonUebergabe = "";

//Konstruktor für Lessons
// No-arg Constructor
    public Lesson (){ }
    public Lesson(int lessonId, int lessonNo, String lessonTitle, int courseRelated, int lessonTime, boolean lessonTimeSet) {
        this.lessonId = lessonId;
        this.lessonNo = lessonNo;
        this.lessonTitle = lessonTitle;
        this.courseRelated = courseRelated;
        this.lessonTime = lessonTime;
        this.lessonTimeSet = lessonTimeSet;
    }

    //Getter + Setter Methoden
    public int getLessonId() {
        return lessonId;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
    public int getLessonNo() {
        return lessonNo;
    }
    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }
    public String getLessonTitle() {
        return lessonTitle;
    }
    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
    public int getCourseRelated() {
        return courseRelated;
    }
    public void setCourseRelated(int courseRelated) {
        this.courseRelated = courseRelated;
    }
    public int getLessonTime() {
        return lessonTime;
    }
    public void setLessonTime(int lessonTime) {
        this.lessonTime = lessonTime;
    }
    public boolean isLessonTimeSet() {
        return lessonTimeSet;
    }
    public void setLessonTimeSet(boolean lessonTimeSet) {
        this.lessonTimeSet = lessonTimeSet;
    }



//ArrayListe für die Kurse
    public static ArrayList lessonListeArray = new ArrayList();
//Getter Methode fürs array
    public ArrayList getLessonListeArray() {
        return lessonListeArray;
    }


//Start Methode der Klasse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

            //LEGT Platzhalter Leerobjekt
            Lesson LEER = new Lesson(1,1,"title",1001,20,true);

//SpinnerObjekt für Lerneinheitauswahl
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerLesson);

//Array Adapter für Kursauswahl oben
        if(lessonListeArray.size() > 0){

            ArrayAdapter<Lesson> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLesson.setAdapter(adapter);
        }

//ÜBERGABE des Ausgewählten Objekt an die InfoView-Ansicht von Kurse
        spinnerLesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lessonUebergabe = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                }
            }
            );

    }


@Override
protected void onStart () {
    super.onStart();

// Ausgabe des Inhalts von LessonListeArray
    Log.d("#####Debug onStart#####", "Inhalt von LessonListeArray in Lesson:" +"\n" + Arrays.toString(lessonListeArray.toArray()));

    String kursString = getString(R.string.buttonLesson);

    if(lessonListeArray.size() > 0) {
        for (int i = 0; i < lessonListeArray.size() -1;i++){
        }

//ListView für die Hauptansicht
        ListView listViewLesson = (ListView) findViewById(R.id.listViewLesson);
        ArrayAdapter<String> listViewAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArray);
        listViewLesson.setAdapter(listViewAdapter);
    }

}


//Seitenwechsel Lesson Info Kurs
    public void onClickLessonInfo(View button) {
        Intent changeIntent = new Intent(Lesson.this, LessonInfo.class);
        startActivity(changeIntent);
    }

//Seitenwechsel Lesson Info Edit
    public void onClickLessonEdit(View button) {
    Intent changeIntent = new Intent(Lesson.this, LessonEdit.class);
    startActivity(changeIntent);
}

//Buttonfunktion für AddButton

    public void onClickAddLesson(View button) {
//Seitenwechsel Kurse hinzufügen
        Intent changeIntent = new Intent(Lesson.this, LessonAdd.class);
        startActivity(changeIntent);

    }

//Gibt Daten aus dem Objekt per String zurück, wird benötigt für Spinner und ListView
    @Override
    public String toString() {
//Stringverknüpftung für das Präfix Kurs
        return "Nr:"+lessonNo+"#"+lessonTitle +"#"+lessonTime+ "#"+lessonTimeSet+"#"+courseRelated+"#"
                ;
    }

}