package com.example.omar.historyguesstheyear;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    // Mastery level tracker
    private CategoryLevel industrialLevel = CategoryLevel.NOVICE;
    private CategoryLevel civilRightsLevel = CategoryLevel.NOVICE;
    private CategoryLevel progressiveLevel = CategoryLevel.NOVICE;

    // Category Buttons
    private Button industrialButton;
    private Button civilRightsButton;
    private Button progressiveButton;

    /**
     * Entry point for the app
     *
     * @param savedInstanceState saved information used for the restoration of the activity.
     *                           Initially null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate all class variables

    }

    private void instantiateViews() {
        industrialButton = findViewById(R.id.industrialButton);
        civilRightsButton = findViewById(R.id.civilRightsButton);
        progressiveButton = findViewById(R.id.progressiveButton);
    }
}
