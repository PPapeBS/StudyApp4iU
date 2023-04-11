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



    //ArrayListe für die Lerneinheiten
    public static ArrayList lessonListeArray = new ArrayList();
    public static ArrayList lessonListeArrayReduziert = new ArrayList();
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

//SpinnerObjekt für Kursauswahl innerhalb von Lerneinheiten
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerLessonCourses);




//Array Adapter für Kursauswahl oben innerhalb von Lerneinheiten
        if(Courses.courseListeArray.size() > 0){

            ArrayAdapter<Courses> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Courses.courseListeArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCourses.setAdapter(adapter);
        }

//ÜBERGABE des Ausgewählten KursObjekt an die zur Auswahl der Lesson-Sicht
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             Courses.courseUebergabe = adapterView.getItemAtPosition(i).toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {
             }
             }
        );


//SpinnerObjekt für Lerneinheitauswahl
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerLesson);

//Array Adapter für Lerneinheitenauswahl oben, abgleich ob die ID-Lerneinheit sich auf einen Kurs bezieht
        if(lessonListeArray.size() > 0){
//Entnimmt die Kurs-ID Aus dem ArrayString von dem Kursarray
            String kursID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-5,Courses.courseUebergabe.length());
            for(int i = 0; i < lessonListeArray.size()-1; i ++ ) {
//Entnimmt die Lerneinheit-ID aus dem ArrayString von dem LerneinheitArray
                String lessonID = lessonListeArray.get(i).toString().substring(lessonListeArray.get(i).toString().length()-5,Lesson.lessonListeArray.get(i).toString().length());
//Prüfung ob KursID und LessonID gleich sind
                if(kursID.equals(lessonID)) {
//Falls die Listen gleich sind füge das Objekt zur reduzierten ListArray hinzu
                    lessonListeArrayReduziert.add(lessonListeArray.get(i));
                }
            }
//Über den Adapter wird das das Spinner Objekt mit dem Array verbunden und zeigt die Lerneinheiten an welche sich auf Kurs beziehen
            ArrayAdapter<Lesson> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArrayReduziert);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLesson.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            spinnerLesson.invalidate();

        }


//Dient zur Übergabe des Ausgewählten Objekt an die InfoView-Ansicht von Lerneinheiten
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
// Ausgabe des Inhalts von CourseListeArray
    Log.d("#####Debug onStart#####", "Inhalt von CourseListeArray in Lesson:" +"\n" + Arrays.toString(Courses.courseListeArray.toArray()));

    String kursString = getString(R.string.buttonLesson);

    if(lessonListeArray.size() > 0) {
        for (int i = 0; i < lessonListeArray.size() -1;i++){
        }
//ListView für die Hauptansicht
//MUSS Später noch geändert werden, zeigt alle Lerneinheiten an.
        ListView listViewLesson = (ListView) findViewById(R.id.listViewLesson);
        ArrayAdapter<String> listViewAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArray);
        listViewLesson.setAdapter(listViewAdapter);
    }

}

//Aktuell INBE
//Sowohl in on Resume als auch in OnStart

    @Override
    protected void onResume () {
        super.onResume();

//SpinnerObjekt für Lerneinheitauswahl
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerLesson);
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerLessonCourses);


//Löscht die
        lessonListeArrayReduziert.clear();
        Courses.courseUebergabe = spinnerCourses.getSelectedItem().toString();

//Array Adapter für Lerneinheitenauswahl oben

            if(lessonListeArray.size() > 0){
        String kursID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-5,Courses.courseUebergabe.length());
        for(int i = 0; i < lessonListeArray.size()-1; i ++ ) {
            String lessonID = lessonListeArray.get(i).toString().substring(lessonListeArray.get(i).toString().length()-5,Lesson.lessonListeArray.get(i).toString().length());
            if(kursID.equals(lessonID)) {
                lessonListeArrayReduziert.add(lessonListeArray.get(i));
            }
        }

        ArrayAdapter<Lesson> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArrayReduziert);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLesson.setAdapter(adapter);

        //Adapter Clear funktioniert nicht an dieser Position
        // adapter.clear();

        adapter.addAll(lessonListeArrayReduziert);
        adapter.notifyDataSetChanged();
        spinnerLesson.invalidate();
    }



    }


//Seitenwechsel Kursauswahl
    public void onClickSelectCourse (View button) {
        recreate();
    }

//Seitenwechsel Lesson Info Lerneinheiten
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