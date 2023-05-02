package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import android.widget.TextView;


public class Timer extends AppCompatActivity {

//Variablen für Timer Sekunde, zwei x Aktivitätsprüfung per Wahrheitswert
    private int seconds = 0;
    private boolean running;

    public static ArrayList lessonListeArrayReduziert = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
//Startet die Hintergrundaktivität Timer, die auf Running = True wartet
        runTimer();

//SpinnerObjekt für Kursauswahl innerhalb von dem Timer
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerSelectCourse);

//Array Adapter für Kursauswahl oben innerhalb von dem Timer
        if (Courses.courseListeArray.size() > 0) {


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




    }


@Override
    protected void onResume () {
    super.onResume();

//Lerneinheiten
//SpinnerObjekt für Lerneinheitenauswahl innerhalb von dem Timer
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerSelectLesson);
        Spinner spinnerCourse = (Spinner) findViewById(R.id.spinnerSelectCourse);

//Löscht die ArrayList
    lessonListeArrayReduziert.clear();
//Zugriff auf Courseübergabe innerhalb der onResume Methode
    Courses.courseUebergabe = spinnerCourse.getSelectedItem().toString();

//Array Adapter für Lerneinheitenauswahl oben innerhalb der Lerneinheit
    if(Lesson.lessonListeArray.size() > 0){
        String kursID = Courses.courseUebergabe.substring(Courses.courseUebergabe.length()-5,Courses.courseUebergabe.length());
        for(int i = 0; i < Lesson.lessonListeArray.size(); i ++ ) {
            String lessonID = Lesson.lessonListeArray.get(i).toString().substring(Lesson.lessonListeArray.get(i).toString().length()-5,Lesson.lessonListeArray.get(i).toString().length());
            if(kursID.equals(lessonID)) {
                lessonListeArrayReduziert.add(Lesson.lessonListeArray.get(i));
            }
        }

        ArrayAdapter<Lesson> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lessonListeArrayReduziert);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLesson.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        spinnerLesson.invalidate();

Log.d("##Debug onResume##", "Der Wert von LessonListArrayReduziert ist:" + Arrays.toString(lessonListeArrayReduziert.toArray()));

    }



        spinnerLesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Lesson.lessonUebergabe = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            }

        );



    }




//Buttonfunktions Select Kurs
    public void onClickSelectCourse(View button) {
        recreate();
    }

//Buttonfunktion Timer Reset
    public void onClickTimerReset(View button) {

        running =false;
        seconds = 0;
    }


//Buttonfunktion Timer Start
    public void onClickTimerStart(View button) {

        running =true;
    }

//Buttonfunktion Timer Stop
    public void onClickTimerStop(View button) {

        running = false;
    }

//Buttonfunktions Timer Ende
    public void onClickTimerEnd(View button) {
        running = false;

        //Zerlegt den LessonÜbergabe String in die Bestandteile
        int stelle = Lesson.lessonUebergabe.indexOf("#");
        String lessonNo= new String(Lesson.lessonUebergabe.substring(0, stelle));

        int stelleZwei = Lesson.lessonUebergabe.indexOf('#', stelle + 1);
        String lessonTitle= new String(Lesson.lessonUebergabe.substring(stelle+1, stelleZwei));

//Gebuchte Zeit der Lerneinheit
        int stelleDrei = Lesson.lessonUebergabe.indexOf('#', stelleZwei + 1);
        String lessonTime= new String(Lesson.lessonUebergabe.substring(stelleZwei+1, stelleDrei));
//Wandelt den String in Int um und addiert die aktuelle Sekundenzahl
        int lessonTimeInt = Integer.parseInt(lessonTime) + seconds;
//Wandelt den Int zurück in String
        String newLessonTime = Integer.toString(lessonTimeInt);
        lessonTime = newLessonTime;

        int stelleVier = Lesson.lessonUebergabe.indexOf('#', stelleDrei + 1);
        String lessonTimeSet= new String(Lesson.lessonUebergabe.substring(stelleDrei+1, stelleVier));
        if (Integer.parseInt(lessonTime) > 0) {
            lessonTimeSet = "true";
        } else {
            lessonTimeSet = "false";
        }

        int stelleFuenf = Lesson.lessonUebergabe.indexOf('#', stelleVier + 1);
        String courseRelated= new String (Lesson.lessonUebergabe.substring(stelleVier+1, stelleFuenf));
        int courseRelated2 = Integer.parseInt(courseRelated);

Log.d("##Debug onClickTimerEnd VORHER##", "Der Wert von LessonÜbergabe ist:" + Lesson.lessonUebergabe);

Log.d("##Debug onClickTimerEnd VORHER##", "Der Wert von LessonListArray ist:" + Arrays.toString(Lesson.lessonListeArray.toArray()));

//Hier will ich das Lernobjekt neu schreiben, dafür die aktuelle Zeit auf die gespeicherte Zeit addieren
//Löscht das aktuelle Lernobjekt aus dem Array

            for(int i = 0; i < Lesson.lessonListeArray.size(); i++) {
            if (Lesson.lessonListeArray.get(i).toString().contains(Lesson.lessonUebergabe)) {
                Lesson.lessonListeArray.set(i,lessonNo+"#"+lessonTitle+"#"+newLessonTime+ "#"+lessonTimeSet+"#" +courseRelated+"#");


Log.d("##Debug onClickTimerEnd NACHHER##", "Der Wert von LessonListArray ist:" + Arrays.toString(Lesson.lessonListeArray.toArray()));


            }

            }


//Setz die Zeit wieder auf 0 zurück
        seconds = 0;

        Toast.makeText(Timer.this,
                R.string.buttonEndLessons,
                Toast.LENGTH_LONG ).show();
        recreate();

Log.d("##Debug onClickTimerEnd am Ende##", "Der Wert von LessonListArray ist:" + Arrays.toString(Lesson.lessonListeArray.toArray()));


    }

//Methode runTimer, wird zum start ausgeführt
    private void runTimer()
    {

// Zugriff auf den TextView in der Activity
        final TextView timeView
                = (TextView)findViewById(
                R.id.viewTimer);
// Erstellt den neuer Handler um zwischen den Threats-Daten zu übertragen zur setContentView(R.layout.activity_timer)
        final Handler handler = new Handler();
        handler.post(new Runnable() {
//Führt die Methode run aus, welche die Sekunden richtig darstellt (DelayMilli) und in ein Zeitformat umwandelt)

            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time= String
                        .format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);

//Setz den Textview auf die Variable time welche die Sekunden umwandelt in ein Zeitformt
                timeView.setText(time);

//Wenn der Timer laufen soll wir der Sekundenzähler um 1 erhlht mit einer Verzögerung von 1000 Millisekunden
                if (running) {seconds++; }
                handler.postDelayed(this, 1000);
            }
        });
    }
}