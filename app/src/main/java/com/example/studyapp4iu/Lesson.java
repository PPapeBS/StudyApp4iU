package com.example.studyapp4iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class Lesson extends AppCompatActivity {

    //Definition der Variablen
    private int lessonId;
    private int lessonNo;
    private String lessonTitle;
    private int courseRelated;
    private int lessonTime;
    private boolean lessonTimeSet;

    //Konstruktor für Courses
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

    //Start Methode der Klasse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Legt Lerneinheitobjekt an.
        Lesson lesson1 =new Lesson(4444, 1, "Stakeholdermanagement", 1111, 60, true);
        Lesson lesson2 =new Lesson(5555, 2, "Shareholdervalue", 1111, 180, true);
        Lesson lesson3 =new Lesson(6666, 3, "Grundgesetz", 3333, 99, true);

    }

    //Buttonfunktions Select Kurs
    public void onClickButtonSelectCourse(View button) {
        Toast.makeText(Lesson.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();
    }

    //Buttonfunktions Kurs auswählen
    public void onClickButtonAddCourse(View button) {
        Toast.makeText(Lesson.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();
    }
}