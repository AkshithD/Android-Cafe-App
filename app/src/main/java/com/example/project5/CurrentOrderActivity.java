package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The CurrentOrderActivity class represents the activity that displays the current order and allows the user to remove an item from the order.
 * It displays the items in the order, the subtotal, sales tax, and total amount of the order.
 * The user can select an item from the list view and click a button to remove the selected item from the order.
 * The user can also click a button to place the order, which adds the order to the list of all orders.
 * The user can also click a button to cancel the order, which clears the current order.
 * @author Joseph Goeller, Akshith Dandemraju
 */

public class CurrentOrderActivity extends AppCompatActivity {
    CurrentOrder currentOrder = CurrentOrder.getCurrent();
    Order order = currentOrder.getOrder();
    Button removeItemButton; // Declare the button
    ListView orderList; // Declare the list view
    EditText subTotal; // Declare the text field
    EditText salesTax; // Declare the text field
    EditText totalAmount; // Declare the text field
    Button placeOrderButton; // Declare the button
    int selectedPosition;
    // Create an ArrayAdapter
    ArrayAdapter<String> adapter;

    /**
     * Creates the activity that displays the current order and allows the user to remove an item from the order.
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order_view);
        ArrayList<String> orderStrings = new ArrayList<>();
        order.getItems().forEach((n) -> orderStrings.add(n.getDetails()));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderStrings);
        // Initialize the button after setContentView
        removeItemButton = findViewById(R.id.button1);
        orderList = findViewById(R.id.listView1);
        placeOrderButton = findViewById(R.id.button);
        // prices
        subTotal = findViewById(R.id.editTextNumber3);
        salesTax = findViewById(R.id.editTextNumber2);
        totalAmount = findViewById(R.id.editTextNumber1);
        subTotal.setEnabled(false);
        salesTax.setEnabled(false);
        totalAmount.setEnabled(false);
        // Set the adapter to the ListView
        orderList.setAdapter(adapter);
        // Set the click listener for the button
        selectedPosition = -1;
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Store the position of the clicked item
                selectedPosition = position;
            }
        });
        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the position of the selected item
                if (selectedPosition != -1) {
                    // Remove the item from the order
                    currentOrder.removeItemFromOrder(selectedPosition);
                    selectedPosition = -1;
                    // Notify the adapter that the data set has changed
                    adapter.notifyDataSetChanged();
                    // Update the view
                    updateView();
                }
            }
        });
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the order to the list of all orders
                if (order.getItems().size() == 0) {
                    return;
                }
                AllOrders.getInstance().addOrder(order);
                currentOrder.newCurrentOrder();
                updateView();
            }
        });
        updateView();

    }

    /**
     * Updates the view with the current order information.
     */
    public void updateView() {
        order = currentOrder.getOrder();
        double subtotal = order.getSubtotal();
        double salestax = subtotal * 0.06625;
        double total = subtotal + salestax;
        subTotal.setText(String.format("%.2f", subtotal));
        salesTax.setText(String.format("%.2f", salestax));
        totalAmount.setText(String.format("%.2f", total));

        // Update the list view
        if (order.getItems().size() == 0) {
            adapter.clear();
        }
        else {
            adapter.clear();
            order.getItems().forEach((n) -> adapter.add(n.getDetails()));
        }
        adapter.notifyDataSetChanged();

    }
}
