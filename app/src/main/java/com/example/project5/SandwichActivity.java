package com.example.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SandwichActivity extends AppCompatActivity {

    private Button btn_addSandwich;
    private RadioGroup proteinGroup;
    private RadioGroup breadGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);
        btn_addSandwich = findViewById(R.id.btn_addSandwich);
        btn_addSandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sandwich mySandwich = createSandwich();
                if(mySandwich == null){
                    Toast.makeText(SandwichActivity.this,
                            "Sandwich incomplete", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(SandwichActivity.this,
                        mySandwich.getDetails() + " added to order", Toast.LENGTH_LONG).show();
                CurrentOrder current = CurrentOrder.getCurrent();
                current.addItemToOrder(mySandwich);

            }
        });

        subtotalSetup();


    }

    public Sandwich createSandwich(){
        proteinGroup = findViewById(R.id.proteinGroup);
        int proteinChoice = proteinGroup.getCheckedRadioButtonId();
        String proteinString;
        if(proteinChoice==R.id.beefbtn){
            proteinString = "Beef";
        }
        else if(proteinChoice==R.id.chickenbtn){
            proteinString = "Chicken";
        }
        else if(proteinChoice==R.id.fishbtn){
            proteinString = "Fish";
        }
        else{
            return null;
        }
        breadGroup = findViewById(R.id.breadGroup);
        int breadChoice = breadGroup.getCheckedRadioButtonId();
        String breadString;
        if(breadChoice==R.id.bagelbtn){
            breadString = "Bagel";
        }
        else if(breadChoice==R.id.wheatbtn){
            breadString = "Wheat toast";
        }
        else if(breadChoice==R.id.sourbtn){
            breadString = "Sourdough";
        }
        else{
            return null;
        }
        CheckBox lettuce = findViewById(R.id.lettucebox);
        CheckBox tomatoes = findViewById(R.id.tomatoesbox);
        CheckBox onion = findViewById(R.id.onionbox);
        CheckBox cheese = findViewById(R.id.cheesebox);
        Sandwich mySandwich = new Sandwich(proteinString, breadString, cheese.isChecked(), tomatoes.isChecked(), lettuce.isChecked(), onion.isChecked());
        return mySandwich;
    }

    public void updateSubtotal(){
        Sandwich testSandwich = createSandwich();
        TextView sandwichSubtotal = findViewById(R.id.sandwichSubtotal);
        if(testSandwich == null){
            sandwichSubtotal.setText(getString(R.string.select_options));
        }
        else{
            String subtotalOutput = "$"+testSandwich.price();
            sandwichSubtotal.setText(subtotalOutput);
        }
    }

    public void subtotalSetup(){
        View.OnClickListener subtotalListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateSubtotal();
            }
        };
        CheckBox lettuce = findViewById(R.id.lettucebox);
        lettuce.setOnClickListener(subtotalListener);
        CheckBox tomatoes = findViewById(R.id.tomatoesbox);
        tomatoes.setOnClickListener(subtotalListener);
        CheckBox onion = findViewById(R.id.onionbox);
        onion.setOnClickListener(subtotalListener);
        CheckBox cheese = findViewById(R.id.cheesebox);
        cheese.setOnClickListener(subtotalListener);
        RadioButton beef = findViewById(R.id.beefbtn);
        beef.setOnClickListener(subtotalListener);
        RadioButton chicken = findViewById(R.id.chickenbtn);
        chicken.setOnClickListener(subtotalListener);
        RadioButton fish = findViewById(R.id.fishbtn);
        fish.setOnClickListener(subtotalListener);
        RadioButton bagel = findViewById(R.id.bagelbtn);
        bagel.setOnClickListener(subtotalListener);
        RadioButton wheat = findViewById(R.id.wheatbtn);
        wheat.setOnClickListener(subtotalListener);
        RadioButton sour = findViewById(R.id.sourbtn);
        sour.setOnClickListener(subtotalListener);

    }
}