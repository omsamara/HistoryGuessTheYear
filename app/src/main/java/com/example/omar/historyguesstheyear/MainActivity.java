package com.example.omar.historyguesstheyear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    // Mastery level tracker
    private CategoryLevel industrialLevel;
    private CategoryLevel civilRightsLevel;
    private CategoryLevel progressiveLevel;

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

    private void instantiateButtoWidths() {

        industrialButton = findViewById(R.id.industrialButton);
        civilRightsButton = findViewById(R.id.civilRightsButton);
        progressiveButton = findViewById(R.id.progressiveButton);

        int width = getResources().getDisplayMetrics().widthPixels / 3;
        int height = getResources().getDisplayMetrics().heightPixels / 10;
        industrialButton.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    }
}
