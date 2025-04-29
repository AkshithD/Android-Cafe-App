package com.example.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CoffeeActivity extends AppCompatActivity {

    private Button btn_addCoffee;
    private String selectedSize;
    private int selectedQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        btn_addCoffee = findViewById(R.id.btn_addCoffee);
        btn_addCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coffee myCoffee = createCoffee();
                if(myCoffee == null){
                    Toast.makeText(CoffeeActivity.this,
                            "Coffee incomplete", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(CoffeeActivity.this,
                        myCoffee.getDetails() + " added to order", Toast.LENGTH_LONG).show();
                CurrentOrder current = CurrentOrder.getCurrent();
                current.addItemToOrder(myCoffee);

            }
        });
        spinnerSetup();
        subtotalSetup();
    }

    public Coffee createCoffee(){
        if(selectedSize==null || selectedSize.equals("none")){
            return null;
        }
        if(selectedQuantity<1){
            return null;
        }
        CheckBox sweet = findViewById(R.id.sweetbox);
        CheckBox french = findViewById(R.id.frenchbox);
        CheckBox irish = findViewById(R.id.irishbox);
        CheckBox mocha = findViewById(R.id.mochabox);
        CheckBox caramel = findViewById(R.id.caramelbox);
        Coffee myCoffee = new Coffee(selectedSize, sweet.isChecked(), french.isChecked(), irish.isChecked(), caramel.isChecked(), mocha.isChecked(), selectedQuantity);
        return myCoffee;
    }

    public void updateSubtotal(){
        Coffee testCoffee = createCoffee();
        TextView coffeeSubtotal = findViewById(R.id.coffeeSubtotal);
        if(testCoffee == null){
            coffeeSubtotal.setText(getString(R.string.select_options));
        }
        else{
            String subtotalOutput = "$"+testCoffee.price();
            coffeeSubtotal.setText(subtotalOutput);
        }
    }

    public void spinnerSetup(){
        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.coffee_sizes,
                android.R.layout.simple_spinner_item
        );
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnerTranslator = (TextView)sizeSpinner.getSelectedView();
                selectedSize = spinnerTranslator.getText().toString();
                updateSubtotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedSize = "none";
                updateSubtotal();
            }
        });

        Spinner quantitySpinner = (Spinner) findViewById(R.id.quantitySpinner);
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.coffee_quantities,
                android.R.layout.simple_spinner_item
        );
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnerTranslator = (TextView)quantitySpinner.getSelectedView();
                selectedQuantity = Integer.parseInt(spinnerTranslator.getText().toString());
                updateSubtotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedQuantity = 0;
                updateSubtotal();
            }
        });
    }

    public void subtotalSetup(){
        View.OnClickListener subtotalListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateSubtotal();
            }
        };
        CheckBox irish = findViewById(R.id.irishbox);
        irish.setOnClickListener(subtotalListener);
        CheckBox mocha = findViewById(R.id.mochabox);
        mocha.setOnClickListener(subtotalListener);
        CheckBox sweet = findViewById(R.id.sweetbox);
        sweet.setOnClickListener(subtotalListener);
        CheckBox french = findViewById(R.id.frenchbox);
        french.setOnClickListener(subtotalListener);
        CheckBox caramel = findViewById(R.id.caramelbox);
        caramel.setOnClickListener(subtotalListener);

    }
}