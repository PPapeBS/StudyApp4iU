package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.util.Locale;

public class LessonInfo extends AppCompatActivity {

    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_info);


//Anzeige des Spinner aus Lessons als ein String und durch Substring auseinandergenommen
        TextView textViewLesson1 = (TextView) findViewById(R.id.textViewLesson1);
        int stelle = Lesson.lessonUebergabe.indexOf("#");
        textViewLesson1.setText(Lesson.lessonUebergabe.substring(0, stelle));

        TextView textViewLesson2 = (TextView) findViewById(R.id.textViewLesson2);
        int stelleZwei = Lesson.lessonUebergabe.indexOf('#', stelle + 1);
        textViewLesson2.setText(Lesson.lessonUebergabe.substring(stelle+1, stelleZwei));

        //Dargestellte Zeit in Activitiy
        TextView textViewLesson3 = (TextView) findViewById(R.id.textViewLesson3);
        int stelleDrei = Lesson.lessonUebergabe.indexOf('#', stelleZwei + 1);
        textViewLesson3.setText(Lesson.lessonUebergabe.substring(stelleZwei+1, stelleDrei));

        TextView textViewLesson4 = (TextView) findViewById(R.id.textViewLesson4);
        int stelleVier = Lesson.lessonUebergabe.indexOf('#', stelleDrei + 1);
        textViewLesson4.setText(Lesson.lessonUebergabe.substring(stelleDrei+1, stelleVier));

        TextView textViewLesson5 = (TextView) findViewById(R.id.textViewLesson5);
        int stellefuenf = Lesson.lessonUebergabe.indexOf('#', stelleVier + 1);
        textViewLesson5.setText(Lesson.lessonUebergabe.substring(stelleVier+1, stellefuenf));


//Umformatierung der dargestellten Zeit
        String secondsString = textViewLesson3.getText().toString();
        seconds = Integer.parseInt(secondsString);

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

//Setz den Textview auf die Variable time welche die Sekunden umwandelt in ein Zeitformt
        textViewLesson3.setText(time);



    }




}