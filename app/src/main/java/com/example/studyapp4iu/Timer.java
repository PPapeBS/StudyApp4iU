package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Handler;

import java.util.Locale;
import android.widget.TextView;


public class Timer extends AppCompatActivity {

//Variablen für Timer Sekunde, zwei x Aktivitätsprüfung per Wahrheitswert
    private int seconds = 0;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
//Startet die Hintergrundaktivität Timer, die auf Running = True wartet
        runTimer();


//SpinnerObjekt für Kursauswahl innerhalb von dem Timer
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinnerSelectCourse);


//Array Adapter für Kursauswahl oben innerhalb von dem Timer
        if(Courses.courseListeArray.size() > 0){

            ArrayAdapter<Courses> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Courses.courseListeArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCourses.setAdapter(adapter);
        }

//ÜBERGABE des Ausgewählten KursObjekt an die zur Auswahl der Timer-Sicht
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

//Lerneinheiten
//SpinnerObjekt für Lerneinheitenauswahl innerhalb von dem Timer
        Spinner spinnerLesson = (Spinner) findViewById(R.id.spinnerSelectLesson);


//Array Adapter für Lerneinheitenauswahl oben innerhalb von dem Timer
        if(Lesson.lessonListeArray.size() > 0){

            ArrayAdapter<Courses> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Lesson.lessonListeArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLesson.setAdapter(adapter);
        }

//ÜBERGABE des Ausgewählten LerneinheitObjekt an die zur Auswahl der Timer-Sicht
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
        Toast.makeText(Timer.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();

    }

//Buttonfunktions Select Lerneinheit
    public void onClickSelectLesson(View button) {
            Toast.makeText(Timer.this,
                    R.string.buttonFunctionTemplates,
                    Toast.LENGTH_LONG ).show();
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

//Hier will ich das Lernobjekt neu schreiben, dafür die aktuelle Zeit auf die gespeicherte Zeit addieren
//Löscht das aktuelle Lernobjekt aus dem Array
        for(int i = 0; i < Lesson.lessonListeArray.size() -1; i++) {
            if (Lesson.lessonListeArray.get(i).toString().contains(Lesson.lessonUebergabe)) {
                Lesson.lessonListeArray.remove(i);
            }

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

            int stelleFuenf = Lesson.lessonUebergabe.indexOf('#', stelleVier + 1);
            String courseRelated= new String (Lesson.lessonUebergabe.substring(stelleVier+1, stelleFuenf));

//Verändert das Array und übergibt alle Daten inklusive der neuen Zeit
            Lesson.lessonListeArray.set(i,lessonNo+"#"+lessonTitle+"#"+lessonTime+ "#"+lessonTimeSet+"#" +courseRelated+"#");

        }
//Setz die Zeit wieder auf 0 zurück
        seconds = 0;

        Toast.makeText(Timer.this,
                R.string.buttonEndLessons,
                Toast.LENGTH_LONG ).show();
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