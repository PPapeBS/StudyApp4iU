package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

//SpinnerObjekt für Kursauswahl innerhalb von Lerneinheiten
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerLessonCourses);

//Array Adapter für Kursauswahl oben innerhalb von Lerneinheiten
        ArrayAdapter<Courses> adapter = new ArrayAdapter<Courses>(this, android.R.layout.simple_spinner_item, Courses.courseListeArray){
            //  überschreiben der Methode, zur Anzeige des Spinnertextes
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                String courseName = getItem(position).getCourseNameShort();
                tv.setText(courseName);
                return view;
            }

            // Überschreiben der Methode, die den angezeigten Text des ausgewählten Elements festlegt
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                String courseName = getItem(position).getCourseNameShort();
                tv.setText(courseName);
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);
    }


@Override
protected void onStart () {
    super.onStart();


// Ausgabe des Inhalts von LessonListeArray
    Log.d("##Debug onStart##", "Inhalt von LessonListeArray in Lesson:" + Arrays.toString(lessonListeArray.toArray()));
// Ausgabe des Inhalts von CourseListeArray
    Log.d("##Debug onStart##", "Inhalt von CourseListeArray in Lesson:"  + Arrays.toString(Courses.courseListeArray.toArray()));

    if(lessonListeArray.size() > 0) {
        for (int i = 0; i < lessonListeArray.size();i++){
        }
//ListView für die Hauptansicht
        ListView listViewLesson = (ListView) findViewById(R.id.listViewLesson);
        ArrayAdapter<String> listViewAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArrayReduziert);
        listViewLesson.setAdapter(listViewAdapter);
    }
}

@Override
protected void onResume () {
        super.onResume();

//SpinnerObjekt für Lerneinheitauswahl
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerLesson);
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerLessonCourses);

//Löscht die ArrayList
        lessonListeArrayReduziert.clear();
//Zugriff auf Courseübergabe innerhalb der onResume Methode
        Courses.courseUebergabe = spinnerCourses.getSelectedItem().toString();

//Array Adapter für Lerneinheitenauswahl oben innerhalb der Lerneinheit
        if(lessonListeArray.size() > 0){
        String kursID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-5,Courses.courseUebergabe.length());
        //ALter Code funktioniert nicht
        //for(int i = 0; i < lessonListeArray.size()-1; i ++ ) {
                for(int i = 0; i < lessonListeArray.size(); i ++ ) {
            String lessonID = lessonListeArray.get(i).toString().substring(lessonListeArray.get(i).toString().length()-5,Lesson.lessonListeArray.get(i).toString().length());
            if(kursID.equals(lessonID)) {
                lessonListeArrayReduziert.add(lessonListeArray.get(i));
            }
        }


//Lässt sich leider nicht so anpassen wie Kursübersicht, weil er mit einer Exeption abstürzt

           ArrayAdapter<Lesson> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArrayReduziert);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLesson.setAdapter(adapter);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLesson.setAdapter(adapter);


        adapter.notifyDataSetChanged();
        spinnerLesson.invalidate();

Log.d("##Debug onResume##", "Der Wert von LessonListArrayReduziert ist:" + Arrays.toString(lessonListeArrayReduziert.toArray()));

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


//Neuladen der Seite über Kursauswahl
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

//Buttonfunktion für AddNewCourses

    public void onClickAddCourse(View button) {
//Seitenwechsel Kurse hinzufügen
        Intent changeIntent = new Intent(Lesson.this, CourseAdd.class);
        startActivity(changeIntent);

    }

//Zurückbutton Funktion nicht fehlerfrei deswegen deaktiviert
//Wenn neue MainActivity gestartet wird, werden die DummyObjekt erneut generiert, weil sie in der MainActivity und nicht einer SingleTone bzw. Datenbank geladen werden.
/*
    @Override
    public void onBackPressed() {
        Intent changeIntent = new Intent(this, MainActivity.class);
        startActivity(changeIntent);
        finish();
    }
*/




}