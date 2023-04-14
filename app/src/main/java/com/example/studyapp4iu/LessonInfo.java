package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class LessonInfo extends AppCompatActivity {

    static String kursID2 ="";
    public static String courseAuswahl = "";
    public static ArrayList courseAuswahlReduziert = new ArrayList();

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
        if (stelleVier == -1) {
            textViewLesson4.setText(R.string.lessonTimeNotSet);
        } else {
            String lessonTimeSet = Lesson.lessonUebergabe.substring(stelleDrei + 1, stelleVier);
            if (Boolean.parseBoolean(lessonTimeSet)) {
                textViewLesson4.setText(R.string.lessonTimeSet);
            } else {
                textViewLesson4.setText(R.string.lessonTimeNotSet);
            }
        }



//Hier wird die aktuell zugehörige KursID aus dem Übergabestring extrahiert und in
        String relatedCourseID = Lesson.lessonUebergabe.substring(Lesson.lessonUebergabe.length()-5,Lesson.lessonUebergabe.length());
        String relatedCourseID2 = relatedCourseID.replace("#", "");

//Hier wird das Kurs-Array durchsuchen um abhängig davon den momentanen Kurs anzuzeigen
// und im Anschluss den Kurs in ein einzelnes Array zu überführen
        for(int i = 0; i < Courses.courseListeArray.size(); i++) {

            if(Courses.courseListeArray.get(i).toString().contains(relatedCourseID2)) {
                courseAuswahlReduziert.clear();
                courseAuswahlReduziert.add(Courses.courseListeArray.get(i));
            }

        }
//Darstellung des zugehörigen Kurses aus der Kurs Array Liste
        TextView textViewLesson5 = (TextView) findViewById(R.id.textViewLesson5);
        textViewLesson5.setText(Arrays.toString(courseAuswahlReduziert.toArray()));


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