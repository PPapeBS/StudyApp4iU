package com.example.studyapp4iu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Toast;
import com.example.studyapp4iu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


// Programm start der MainActivity wird benötigt damit irgendwas angezeigt wird
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    };

    public void onClickButton(View button) {
        Toast.makeText(MainActivity.this,
                R.string.buttonFunctionTemplates,
                Toast.LENGTH_LONG ).show();
    }
//Seitenwechsel Kurse

     public void onClickCourses(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Courses.class);
        startActivity(changeIntent);    }

    //Seitenwechsel Lerneinheiten
    public void onClickLesson(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Lesson.class);
        startActivity(changeIntent);    }

    //Seitenwechsel Timer
    public void onClickTimer(View button) {
        Intent changeIntent = new Intent(MainActivity.this, Timer.class);
        startActivity(changeIntent);    }
}

