package com.example.iglym.bulkbuyerv2;

import java.util.TreeSet;

public class BuyOrder {

    public TreeSet<String> Users;
    public String productName;
    public String city;
    public String pickupLocation;
    public String numberOfBuyers;
    public String pricePerBuyer;
    public String purchaseDate;
    public String purchaseTime;

    public BuyOrder(){} //required default constructor for calls to DataSnapshot.getValue(User.class)

    public BuyOrder(String productName, String city,
                    String pickupLocation, String numberOfBuyers,
                    String pricePerBuyer, String purchaseDate,
                    String purchaseTime, TreeSet<String> firstBuyer){

        this.productName = productName;
        this.city = city;
        this.pickupLocation = pickupLocation;
        this.numberOfBuyers = numberOfBuyers;
        this.pricePerBuyer = pricePerBuyer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;

        //the treeset needs to be implemented to specify which users are associated with the order

    }


}
