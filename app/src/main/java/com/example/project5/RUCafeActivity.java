package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The RUCafeActivity class represents the main activity of the app.
 * It displays the main menu of the app, which includes buttons to navigate to the donut, coffee, and sandwich menus,
 * the current order, and all orders.
 * @author Joseph Goeller, Akshith Dandemraju
 */

public class RUCafeActivity extends AppCompatActivity {
    ImageButton DonutButton;
    ImageButton CoffeeButton;
    ImageButton SandwichButton;
    ImageButton CurrOrdButton;
    ImageButton AllOrdButton;

    /**
     * Creates the main menu of the app.
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.rucafe_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DonutButton = findViewById(R.id.imageButton);
        CoffeeButton = findViewById(R.id.imageButton1);
        SandwichButton = findViewById(R.id.imageButton2);
        CurrOrdButton = findViewById(R.id.imageButton3);
        AllOrdButton = findViewById(R.id.imageButton4);
        DonutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(DonutActivity.class);
            }
        });

        CoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(CoffeeActivity.class);
            }
        });

        SandwichButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(SandwichActivity.class);
            }
        });

        CurrOrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(CurrentOrderActivity.class);
            }
        });

        AllOrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(AllOrdersActivity.class);
            }
        });
    }

    /**
     * Opens a new activity.
     * @param activityClass The class of the activity to open
     */
    public void openNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}