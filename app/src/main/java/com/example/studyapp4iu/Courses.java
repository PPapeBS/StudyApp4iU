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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import android.widget.Spinner;
import android.widget.TextView;

public class Courses extends AppCompatActivity {

    //Definition der Variablen
    // CourseID auf Public gesetzt
    public int courseID;
    private int courseNo;
    private String courseNameShort;
    private String courseNameLong;
    private String courseIubhId;
    private int courseSem;

    public static String courseUebergabe = "";



    //Konstruktor für Courses
  //No-arg Constructor
  public Courses (){ }
  public Courses(int courseID, int courseNo, String courseNameShort, String courseNameLong, String courseIubhId, int courseSem) {
        this.courseID = courseID;
        this.courseNo = courseNo;
        this.courseNameShort = courseNameShort;
        this.courseNameLong = courseNameLong;
        this.courseIubhId = courseIubhId;
        this.courseSem = courseSem;
    }

  //Getter + Setter Methoden
    public int getCourseID() {
        return courseID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public int getCourseNo() {
        return courseNo;
    }
    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }
    public String getCourseNameShort() {
        return courseNameShort;
    }
    public void setCourseNameShort(String courseNameShort) {
        this.courseNameShort = courseNameShort;
    }
    public String getCourseNameLong() {
        return courseNameLong;
    }
    public void setCourseNameLong(String courseNameLong) {
        this.courseNameLong = courseNameLong;
    }
    public String getCourseIubhId() {
        return courseIubhId;
    }
    public void setCourseIubhId(String courseIubhId) {
        this.courseIubhId = courseIubhId;
    }
    public int getCourseSem() {
        return courseSem;
    }
    public void setCourseSem(int courseSem) {
        this.courseSem = courseSem;
    }



//ArrayListe für die Kurse
    public static ArrayList courseListeArray = new ArrayList();

//Getter Methode fürs array
    public ArrayList getCourseListeArray() {
        return courseListeArray;
    }


//Start Methode der Klasse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //LEGT Platzhalter Leerobjekt
          Courses LEER = new Courses(0000, 0, "leer", "leerlang", "000", 0);

//SpinnerObjekt für Kursauswahl
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);

//Array Adapter für Kursauswahl oben
        if(courseListeArray.size() > 0){
            ArrayAdapter<Courses> adapter = new ArrayAdapter<Courses>(this, android.R.layout.simple_spinner_item, courseListeArray){
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

//ÜBERGABE des Ausgewählten Objekt an die InfoView-Ansicht von Kurse
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseUebergabe = adapterView.getItemAtPosition(i).toString();




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

// Ausgabe des Inhalts von courseListeArray
    Log.d("#####Debug onStart#####", "Inhalt von courseListeArray in Courses:" +"\n" + Arrays.toString(courseListeArray.toArray()));

    String kursString = getString(R.string.buttonCourse);

    if(courseListeArray.size() > 0) {
    for (int i = 0; i < courseListeArray.size();i++){
    }

//ListView für die Hauptansicht
        ListView listViewCourses = (ListView) findViewById(R.id.listViewCourses);
        ArrayAdapter<String> listViewAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,courseListeArray);
        listViewCourses.setAdapter(listViewAdapter);

    }


}



//Seitenwechsel Kurse Info
    public void onClickCourseInfo(View button) {
        Intent changeIntent = new Intent(Courses.this, CourseInfo.class);
        startActivity(changeIntent);
    }
//Seitenwechsel Kurse Edit
    public void onClickCourseEdit(View button) {
        Intent changeIntent = new Intent(Courses.this, CourseEdit.class);
        startActivity(changeIntent);
    }


//Buttonfunktion für AddButton

    public void onClickAddCourse(View button) {
//Seitenwechsel Kurse hinzufügen
        Intent changeIntent = new Intent(Courses.this, CourseAdd.class);
        startActivity(changeIntent);

    }

//Gibt Daten aus dem Objekt per String zurück, wird benötigt für Spinner und ListView
    @Override
    public String toString() {
//Stringverknüpftung für das Präfix Kurs
        return "Nr:"+courseNo+"#"+courseNameShort +"#IU:"+courseIubhId+ "#Sem:"+courseSem+"#" +courseNameLong+"#"+courseID+"#"
        ;
    }


public void onClickSortCourse(View button) {
//Kurs sortieren Kurse hinzufügen
//Sortiert die Liste anhand der courseNo

Log.d("##Debug Listensortierung VORHER##", "Der Wert von CourseListArray ist:" + Arrays.toString(Courses.courseListeArray.toArray()));
        Collections.sort(courseListeArray, new Comparator<Courses>() {
            @Override
            public int compare(Courses o1, Courses o2) {

                return Integer.compare(o1.getCourseNo(), o2.getCourseNo());

            }
        });
Log.d("##Debug Listensortierung NACHHER##", "Der Wert von CourseListArray ist:" + Arrays.toString(Courses.courseListeArray.toArray()));
        recreate();

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