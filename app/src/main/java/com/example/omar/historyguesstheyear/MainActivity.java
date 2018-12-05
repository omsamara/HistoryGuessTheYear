package com.example.omar.historyguesstheyear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    // Image and TextViews
    private TextView currentCategoryView;
    private EditText guessEntry;
    private TextView industrialLevel;
    private TextView civilRightsLevel;
    private TextView progressiveLevel;
    private ImageView sourceImage;

    // Mastery level tracker
    private CategoryLevel industrialLevelEnum = CategoryLevel.NOVICE;
    private CategoryLevel civilRightsLevelEnum = CategoryLevel.NOVICE;
    private CategoryLevel progressiveLevelEnum = CategoryLevel.NOVICE;

    //Preferred Image Height: 263 pixels.

    // Buttons
    private Button industrialButton;
    private Button civilRightsButton;
    private Button progressiveButton;
    private Button submitGuess;

    // Queues for storing the next images and keeping track of what is next
    private Queue<Integer> industrialImageQueue;
    private Queue<Integer> civilRightsImageQueue;
    private Queue<Integer> progressiveImageQueue;

    // Sets containing all of the image Ids to the year they are from
    private HashMap<Integer, Integer> industrialImageMap;
    private HashMap<Integer, Integer> civilRightsImageMap;
    private HashMap<Integer, Integer> progressiveImageMap;

    // Current User Score for the current category
    private int userScore;

    // Number of sources answered for the current category
    private int sourcesAnswered;

    // The ID of the current image that is the primary source
    private int currentImageId;

    private Category currentCategory;

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
        industrialImageMap = new HashMap<>();
        civilRightsImageMap = new HashMap<>();
        progressiveImageMap = new HashMap<>();

        industrialImageQueue = new LinkedList<>();
        civilRightsImageQueue = new LinkedList<>();
        progressiveImageQueue = new LinkedList<>();

        populateImageSets();
        instantiateViews();
        populateImageQueues();
        userScore = 0;
        sourcesAnswered = 0;
        currentCategory = Category.INDUSTRIALIZATION;
    }

    private void instantiateViews() {

        currentCategoryView = findViewById(R.id.currentCategory);
        guessEntry = findViewById(R.id.yearGuessEntry);
        industrialLevel = findViewById(R.id.industrialLevel);
        civilRightsLevel = findViewById(R.id.civilRightsLevel);
        progressiveLevel = findViewById(R.id.progressiveLevel);
        sourceImage = findViewById(R.id.sourceImage);
        sourceImage.setImageResource(R.drawable.carnegiehome);

        industrialButton = findViewById(R.id.industrialButton);
        civilRightsButton = findViewById(R.id.civilRightsButton);
        progressiveButton = findViewById(R.id.progressiveButton);
    }

    private void populateImageSets() {

        industrialImageMap.put(R.drawable.carnegiehome, 1903);
        industrialImageMap.put(R.drawable.gospelofwealth, 1889);
        industrialImageMap.put(R.drawable.henrygeorge, 1879);

        civilRightsImageMap.put(R.drawable.lbj, 1965);
        civilRightsImageMap.put(R.drawable.mlk, 1963);
        civilRightsImageMap.put(R.drawable.robinson, 1951);

        progressiveImageMap.put(R.drawable.womensuffrage, 1917);
        progressiveImageMap.put(R.drawable.oil, 1904);
        progressiveImageMap.put(R.drawable.roosevelt, 1910);
    }

    private void populateImageQueues() {

        // Empties the queues so we're starting from scratch. No chance of duplicate additions.
        while (!industrialImageQueue.isEmpty()) {
            industrialImageQueue.remove();
        }
        while (!civilRightsImageQueue.isEmpty()) {
            industrialImageQueue.remove();
        }
        while (!progressiveImageQueue.isEmpty()) {
            industrialImageQueue.remove();
        }

        industrialImageQueue.add(R.drawable.carnegiehome);
        industrialImageQueue.add(R.drawable.gospelofwealth);
        industrialImageQueue.add(R.drawable.henrygeorge);

        civilRightsImageQueue.add(R.drawable.lbj);
        civilRightsImageQueue.add(R.drawable.mlk);
        civilRightsImageQueue.add(R.drawable.robinson);

        progressiveImageQueue.add(R.drawable.womensuffrage);
        progressiveImageQueue.add(R.drawable.oil);
        progressiveImageQueue.add(R.drawable.roosevelt);
    }

    public void onGuessEntered(View view) {
        // If we've made all of our guesses,
        // show the screen, update their level ranking,
        // and ask them to choose a category to continue on that screen.

        // Check the current category to
        if (currentCategory == Category.INDUSTRIALIZATION) {
            sourceImage.setImageResource(industrialImageQueue.remove());
        } else if (currentCategory == Category.CIVIL_RIGHTS) {

        } else if (currentCategory == Category.PROGRESSIVE) {

        } else {
            // nothing
        }
    }

    public void onIndustrialButtonClick(View view) {
        populateImageQueues();
        // Update the current Category TextView
        currentCategory = Category.INDUSTRIALIZATION;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());
        // Reset the current score
        userScore = 0;
        sourcesAnswered = 0;
        sourceImage.setImageResource(R.drawable.carnegiehome);
    }

    public void onProgressiveButtonClicked(View view) {
        populateImageQueues();
        
        // Update the current Category TextView
        currentCategory = Category.CIVIL_RIGHTS;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());

        userScore = 0;
        sourcesAnswered = 0;
        sourceImage.setImageResource(R.drawable.lbj);

    }

    public void onCivilRightsButtonClicked(View view) {
        populateImageQueues();
        // Update the current Category TextView
        currentCategory = Category.PROGRESSIVE;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());

        userScore = 0;
        sourcesAnswered = 0;
        sourceImage.setImageResource(R.drawable.womensuffrage);
    }

}
