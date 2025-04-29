package com.example.project5;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The AllOrdersActivity class represents the activity that displays all orders and allows the user to cancel an order.
 * It displays the order number, the items in the order, and the total price of the order.
 * The user can select an order from a spinner and click a button to cancel the selected order.
 * @author Joseph Goeller, Akshith Dandemraju
 */
public class AllOrdersActivity extends AppCompatActivity {
    Button cancelOrderButton; // Declare the button
    ListView orderList; // Declare the list view
    Spinner orderSpinner; // Declare the spinner
    EditText total; // Declare the text field
    // Create an ArrayAdapter
    ArrayAdapter<String> adapter;
    ArrayAdapter<Integer> spinnerAdapter;
    AllOrders allOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_orders_view);
        allOrders = AllOrders.getInstance();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        spinnerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item);
        allOrders.getOrders().forEach((n) -> {
            spinnerAdapter.add(n.getOrderNumber());
            spinnerAdapter.notifyDataSetChanged();
        });
        // Initialize the button after setContentView
        cancelOrderButton = findViewById(R.id.button2);
        orderList = findViewById(R.id.listView);
        orderSpinner = findViewById(R.id.spinner);
        total = findViewById(R.id.editTextNumber);
        total.setEnabled(false);
        // Set the adapter to the ListView
        orderList.setAdapter(adapter);
        orderSpinner.setAdapter(spinnerAdapter);
        // Set the click listener for the button
        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the index selected in the spinner and remove it from the list
                int index = orderSpinner.getSelectedItemPosition();
                Log.d("index", String.valueOf(index));
                if (index != -1 && index < allOrders.getOrders().size()){
                    allOrders.getOrders().remove(index);
                    Integer selectedItem = spinnerAdapter.getItem(index);
                    if (selectedItem != null) {
                        // Remove the selected item from the spinnerAdapter
                        spinnerAdapter.remove(selectedItem);
                    }
                    spinnerAdapter.notifyDataSetChanged();
                    total.setText("0.00");
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    if(index<allOrders.getOrders().size()){
                        Order selectedOrder = allOrders.getOrders().get(index);
                        total.setText(String.format("%.2f", selectedOrder.getSubtotal() * 1.06625));
                        selectedOrder.getItems().forEach((n) -> adapter.add(n.getDetails()));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        // handle spinner selection
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the selected order
                Order selectedOrder = null;
                if (position != -1) {
                    selectedOrder = allOrders.getOrders().get(position);
                }
                // set total
                if (selectedOrder!= null) {
                    total.setText(String.format("%.2f", selectedOrder.getSubtotal() * 1.06625));
                }
                // set list view
                adapter.clear();
                if (selectedOrder != null) {
                    selectedOrder.getItems().forEach((n) -> adapter.add(n.getDetails()));
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // clean up
                total.setText("0.00");
                adapter.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
