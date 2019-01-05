package com.example.iglym.bulkbuyerv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.TreeSet;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText productName;
    private EditText city;
    private EditText pickupLocation;
    private EditText numOfBuyers;
    private EditText pricePerBuyer;
    private EditText purchaseDate;
    private EditText purchaseTime;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this creates a list in the database
        mDatabase = FirebaseDatabase.getInstance().getReference("Buy Orders"); //database needs organizing

        //define the various fields
        productName = findViewById(R.id.productName);
        city = findViewById(R.id.city);
        pickupLocation = findViewById(R.id.pickupLocation);
        numOfBuyers = findViewById(R.id.numberOfBuyers);
        pricePerBuyer = findViewById(R.id.pricePerBuyer);
        purchaseDate = findViewById(R.id.purchaseDate);
        purchaseTime = findViewById(R.id.purchaseTime);
        submitButton= findViewById(R.id.submitButton);

        //create the button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                submitBuyOrder();
            }

        });
    }


    private void submitBuyOrder(){

        //grab the contents of the fields and assign them to strings
        final String orderProductName = productName.getText().toString();
        final String orderCity = city.getText().toString();
        final String orderPickupLocation = pickupLocation.getText().toString();
        final String orderNumOfBuyers = numOfBuyers.getText().toString();
        final String orderPricePerBuyer = pricePerBuyer.getText().toString();
        final String orderPurchaseDate = purchaseDate.getText().toString();
        final String orderPurchaseTime= purchaseTime.getText().toString();

        //set required fields. Buy order shouldn't go through if input fields are blank
        if (TextUtils.isEmpty(orderProductName)) {
            productName.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderCity)) {
            city.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderPickupLocation)) {
            pickupLocation.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderNumOfBuyers)) {
            numOfBuyers.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderPricePerBuyer)) {
            pricePerBuyer.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderPurchaseDate)) {
            purchaseDate.setError("required");
            return;
        }

        if (TextUtils.isEmpty(orderPurchaseTime)) {
            purchaseTime.setError("required");
            return;
        }

        // Disable button to prevent accidentally posting same order twice
        enableEditing(false);
        Toast.makeText(this, "Submitting...", Toast.LENGTH_SHORT).show();

        placeOrder(orderProductName, orderCity, orderPickupLocation, orderNumOfBuyers,
                orderPricePerBuyer, orderPurchaseDate, orderPurchaseTime);
        enableEditing(true);
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    //does what it says it does - pass it true, and it lets you edit, pass it false, and it doesn't
    private void enableEditing(boolean enabled){
        productName.setEnabled(enabled);
        city.setEnabled(enabled);
        pickupLocation.setEnabled(enabled);
        numOfBuyers.setEnabled(enabled);
        pricePerBuyer.setEnabled(enabled);
        purchaseDate.setEnabled(enabled);
        purchaseTime.setEnabled(enabled);

        if(enabled){
            submitButton.setVisibility(View.VISIBLE);
        }
        else{
            submitButton.setVisibility(View.GONE);
        }

    }

    //THIS NEEDS TO BE CHANGED TO INTERACT WITH THE PROFILE PAGE TO TRACK USERS
    //THIS METHOD SHOULD ALSO GET THE POSTING USER'S USERNAME AND .ADD IT TO THE TREESET
    //.push allows for unique keys to be generated for each buy order
    private void placeOrder(String oProductName, String oCity,
                            String oPickupLocation, String oNumOfBuyers,
                            String oPricePerBuyer, String oPurchaseDate,
                            String oPurchaseTime){
        TreeSet<String> users = new TreeSet<String>();


        BuyOrder thisOrder = new BuyOrder(oProductName, oCity,
                oPickupLocation, oNumOfBuyers, oPricePerBuyer,
                oPurchaseDate, oPurchaseTime, users);


        DatabaseReference newBuyOrderRef = mDatabase.push();
        newBuyOrderRef.setValue(thisOrder);


    }
}
