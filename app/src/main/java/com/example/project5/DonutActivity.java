package com.example.project5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an example of an Activity where a RecyclerView is used.
 * @author Lily Chang
 */
public class DonutActivity extends AppCompatActivity {
    //Declare an instance of ArrayList to hold the items to be display with the RecyclerView
    private ArrayList<Donut> donuts = new ArrayList<>();
    /* All the images associated with the menu items are stored in the res/drawable folder
     *  Each image are accessed with the resourse ID, which is an integer.
     *  We need an array of integers to hold the resource IDs. Make sure the index of a given
     *  ID is consistent with the index of the associated menu item in the ArrayList.
     *  An image resource could also be an URI.
     */
    private int [] donutImages = {R.drawable.jelly, R.drawable.glazed, R.drawable.chocolate,
            R.drawable.strawberry, R.drawable.banana, R.drawable.rainbow,
            R.drawable.cinnamon, R.drawable.apple, R.drawable.sprinkles,
            R.drawable.fudge, R.drawable.vanilla, R.drawable.custard};
    // dev change these to donut images

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        DonutAdapter adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        //dev change this to an array of donuts
        String [] donutNames = {"jelly", "glazed", "chocolate", "strawberry", "banana", "rainbow",
                "cinnamon", "apple", "sprinkles",
                "fudge", "vanilla", "custard"};

        int [] donutTypes = {0, 0, 0, 0, 0, 0,
                1, 1, 1,
                2, 2, 2};
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < donutNames.length; i++) {
            donuts.add(new Donut(donutTypes[i], donutNames[i], 1, donutImages[i]));
        }
    }
}
