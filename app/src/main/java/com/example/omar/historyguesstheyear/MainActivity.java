package com.example.omar.historyguesstheyear;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    // Image and TextViews
    private TextView currentCategoryView;
    private EditText yearEntry;
    private TextView industrialLevelView;
    private TextView civilRightsLevelView;
    private TextView progressiveLevelView;

    private String industrialLevel = "Novice";
    private String civilRightsLevel = "Novice";
    private String progressiveLevel = "Novice";

    private ImageView sourceImage;

    // Mastery level tracker

    // Queues for storing the next images and keeping track of what is next
    private Queue<Integer> industrialImageQueue;
    private Queue<Integer> civilRightsImageQueue;
    private Queue<Integer> progressiveImageQueue;

    // Sets containing all of the image Ids to the year they are from
    private HashMap<Integer, Integer> industrialImageMap;
    private HashMap<Integer, Integer> civilRightsImageMap;
    private HashMap<Integer, Integer> progressiveImageMap;

    private HashMap<String, Integer> levelRankings;
    // Number of sources answered for the current category
    private int sourcesAnswered;

    private int combinedYearDifference;
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
        // Remove the first source from the image queue because it's already the default image.
        industrialImageQueue.remove();
        sourcesAnswered = 0;
        currentCategory = Category.INDUSTRIALIZATION;
        currentImageId = R.drawable.carnegiehome;
    }

    private void instantiateViews() {

        currentCategoryView = findViewById(R.id.currentCategory);
        yearEntry = findViewById(R.id.yearGuessEntry);
        industrialLevelView = findViewById(R.id.industrialLevel);
        civilRightsLevelView = findViewById(R.id.civilRightsLevel);
        progressiveLevelView = findViewById(R.id.progressiveLevel);
        sourceImage = findViewById(R.id.sourceImage);
        sourceImage.setImageResource(R.drawable.carnegiehome);
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

        levelRankings.put("Novice", 1);
        levelRankings.put("Intermediate", 2);
        levelRankings.put("Expert", 3);
        levelRankings.put("History Master", 4);
        levelRankings.put("Historic God", 5);
    }

    private void populateImageQueues() {

        // Empties the queues so we're starting from scratch. No chance of duplicate additions.
        while (!industrialImageQueue.isEmpty()) {
            industrialImageQueue.remove();
        }
        while (!civilRightsImageQueue.isEmpty()) {
            civilRightsImageQueue.remove();
        }
        while (!progressiveImageQueue.isEmpty()) {
            progressiveImageQueue.remove();
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

    private String calculateLevel() {
        if (combinedYearDifference > 50) {
            return "Novice";
        }
        else if (combinedYearDifference > 25) {
            return "Intermediate";
        }
        else if (combinedYearDifference > 10) {
            return "Expert";
        } else if (combinedYearDifference > 0) {
            return "History Master";
        } else {
            return "Historian God";
        }
    }

    public void onGuessEntered(View view) {
        // If we've made all of our guesses,
        // show the screen, update their level ranking,
        if (yearEntry.getText().toString().equals("")) {
            Toast.makeText(this, "You must enter a value for your guess",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        sourcesAnswered++;
        if (sourcesAnswered > 3) {
            Toast.makeText(this, "Please use a button to select a category",
                    Toast.LENGTH_SHORT).show();
            yearEntry.getText().clear();
            return;
        }
        HashMap<Integer, Integer> currentCategoryMap;
        TextView currentCategoryLevelView;
        String startingString = "";
        if (currentCategory == Category.INDUSTRIALIZATION) {
            currentCategoryMap = industrialImageMap;
            currentCategoryLevelView = industrialLevelView;
            startingString = "Inudstrialization Level: ";
        } else if (currentCategory == Category.CIVIL_RIGHTS) {
            currentCategoryMap = civilRightsImageMap;
            currentCategoryLevelView = civilRightsLevelView;
            startingString = "Civil Rights Level:      ";
        } else {
            currentCategoryMap = progressiveImageMap;
            currentCategoryLevelView = progressiveLevelView;
            startingString = "Porgress Era Level: ";
        }

        combinedYearDifference += Math.abs(Integer.parseInt(yearEntry.getText().toString()) - currentCategoryMap.get(currentImageId));

        yearEntry.getText().clear();

        if (sourcesAnswered >= 3) {
            LinearLayout layout = findViewById(R.id.playAgainLayout);
            layout.setBackgroundColor(Color.GRAY);
            layout.setVisibility(View.VISIBLE);

            TextView completionMessage = findViewById(R.id.winnerMessage);

            String newLevel = calculateLevel();
            if (currentCategory == Category.INDUSTRIALIZATION) {
                if (levelRankings.get(industrialLevel) < levelRankings.get(newLevel)) {
                    industrialLevel = newLevel;
                    currentCategoryLevelView.setText(startingString + newLevel);
                }
            } else if (currentCategory == Category.CIVIL_RIGHTS) {
                if (levelRankings.get(civilRightsLevel) < levelRankings.get(newLevel)) {
                    civilRightsLevel = newLevel;
                    currentCategoryLevelView.setText(startingString + newLevel);
                }
            } else {
                if (levelRankings.get(progressiveLevel) < levelRankings.get(newLevel)) {
                    progressiveLevel = newLevel;
                    currentCategoryLevelView.setText(startingString + newLevel);
                }
            }
            completionMessage.setText("You've finished this Category with a total year difference of " + combinedYearDifference + "! " +
                    "Making you a " + newLevel + ". Please select a new category to try again!");
        }

        // and ask them to choose a category to continue on that screen.
        Queue<Integer> queueToRemoveFrom;
        // Check the current category to
        if (currentCategory == Category.INDUSTRIALIZATION) {
            queueToRemoveFrom = industrialImageQueue;
        } else if (currentCategory == Category.CIVIL_RIGHTS) {
            queueToRemoveFrom = civilRightsImageQueue;
        } else {
            queueToRemoveFrom = progressiveImageQueue;
        }

        if (!queueToRemoveFrom.isEmpty()) {

            currentImageId = queueToRemoveFrom.peek();
            sourceImage.setImageResource(queueToRemoveFrom.remove());
        } else {
            // Display a toast message saying to pick a new category
        }

    }

    public void onCloseMessage(View view) {

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

    }
    public void onIndustrialButtonClick(View view) {
        populateImageQueues();
        // Update the current Category TextView
        currentCategory = Category.INDUSTRIALIZATION;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());
        yearEntry.getText().clear();
        // Reset the current score
        combinedYearDifference = 0;
        sourcesAnswered = 0;
        sourceImage.setImageResource(R.drawable.carnegiehome);
        industrialImageQueue.remove();
    }

    public void onCivilRightsButtonClicked(View view) {
        populateImageQueues();
        // Update the current Category TextView
        currentCategory = Category.CIVIL_RIGHTS;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());
        yearEntry.getText().clear();

        sourcesAnswered = 0;
        combinedYearDifference = 0;
        currentImageId = R.drawable.lbj;
        sourceImage.setImageResource(R.drawable.lbj);
        progressiveImageQueue.remove();
    }

    public void onProgressiveButtonClicked(View view) {
        populateImageQueues();

        // Update the current Category TextView
        currentCategory = Category.PROGRESSIVE;
        currentCategoryView.setText("Current Category: " + currentCategory.getName());
        yearEntry.getText().clear();

        sourcesAnswered = 0;
        combinedYearDifference = 0;
        currentImageId = R.drawable.womensuffrage;
        sourceImage.setImageResource(R.drawable.womensuffrage);
        progressiveImageQueue.remove();
    }

}
