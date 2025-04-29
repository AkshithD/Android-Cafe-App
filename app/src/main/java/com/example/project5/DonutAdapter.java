package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <DonutsAdapter.DonutHolder>. This will enforce you to define a constructor for the
 * DonutAdapter and an inner class DonutsHolder (a static class)
 * The DonutsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Donut> donuts; //need the data binding to each row of RecyclerView

    public DonutAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method will inflate the row layout for the donuts in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new DonutsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of DonutsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(donuts.get(position).getItemName());
        String priceString = donuts.get(position).price() + "";
        holder.tv_price.setText(priceString);
        holder.thisdonut = donuts.get(position);
        holder.im_item.setImageResource(donuts.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class DonutsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Spinner donutQuantitySpinner;
        private int selectedQuantity;
        private Donut thisdonut;
        private ConstraintLayout parentLayout; //this is the row layout

        public DonutsHolder(@NonNull View donutView) {
            super(donutView);
            tv_name = donutView.findViewById(R.id.tv_flavor);
            tv_price = donutView.findViewById(R.id.tv_price);
            im_item = donutView.findViewById(R.id.im_item);
            btn_add = donutView.findViewById(R.id.btn_add);
            parentLayout = donutView.findViewById(R.id.rowLayout);
            donutQuantitySpinner = donutView.findViewById(R.id.donutQuantitySpinner);
            setAddButtonOnClick(donutView); //register the onClicklistener for the button on each row.
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param donutView
         */
        private void setAddButtonOnClick(@NonNull View donutView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(donutView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(thisdonut.getDetails());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(donutView.getContext(),
                                    thisdonut.getDetails() + " added.", Toast.LENGTH_LONG).show();
                            CurrentOrder current = CurrentOrder.getCurrent();
                            current.addItemToOrder(thisdonut);
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(donutView.getContext(),
                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
            ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(
                    donutView.getContext(),
                    R.array.donut_quantities,
                    android.R.layout.simple_spinner_item
            );
            quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            donutQuantitySpinner.setAdapter(quantityAdapter);
            donutQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TextView spinnerTranslator = (TextView)donutQuantitySpinner.getSelectedView();
                    selectedQuantity = Integer.parseInt(spinnerTranslator.getText().toString());
                    thisdonut.setQuantity(selectedQuantity);
                    updateSubtotal();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedQuantity = 0;
                    thisdonut.setQuantity(selectedQuantity);
                    updateSubtotal();
                }
            });
        }

        public void updateSubtotal(){

            String priceString = thisdonut.price() + "";
            tv_price.setText(priceString);
        }
    }
}
