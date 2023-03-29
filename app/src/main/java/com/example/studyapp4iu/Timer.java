package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

import java.util.Locale;
import android.widget.TextView;


public class Timer extends AppCompatActivity {

//Variablen für Timer Sekunde, zwei x Aktivitätsprüfung per Wahrheitswert
    private int seconds = 0;
    private boolean running;
    private boolean courseSelected = false;
    private boolean lessonSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
//Startet die Hintergrundaktivität Timer, die auf Running = True wartet

        runTimer();
    }

//IF Abfrage Funktioniert nicht, verwendet beide
//Buttonfunktions Select Kurs
    public void onClickSelectCourse(View button) {
        if (courseSelected = true); {
                Toast.makeText(Timer.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();
            }

        if (courseSelected = false); {
            Toast.makeText(Timer.this,
                    R.string.requestSelectCourse,
                    Toast.LENGTH_LONG ).show();
        }
    }
//Buttonfunktions Select Lerneinheit
//IF Abfrage Funktioniert nicht, verwendet beide
    public void onClickSelectLesson(View button) {
        if (courseSelected = true); {
            Toast.makeText(Timer.this,
                    R.string.buttonFunctionTemplates,
                    Toast.LENGTH_LONG ).show();
        }

        if (courseSelected = false); {
            Toast.makeText(Timer.this,
                    R.string.requestSelectLessons,
                    Toast.LENGTH_LONG ).show();
        }
    }


//Buttonfunktion Timer Start
    public void onClickTimerStart(View button) {

        running =true;

    }


    //Buttonfunktion Timer Stop
    public void onClickTimerStop(View button) {

        running = false;

    }

    //Buttonfunktions Timer Stop
    public void onClickTimerEnd(View button) {
        running = false;
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