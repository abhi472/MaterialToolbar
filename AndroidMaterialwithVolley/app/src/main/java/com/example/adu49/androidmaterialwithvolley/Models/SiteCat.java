package com.example.adu49.androidmaterialwithvolley.Models;

import com.example.adu49.androidmaterialwithvolley.Models.Customer;

import java.io.Serializable;
import java.util.ArrayList;

public class SiteCat implements Serializable{

    public String api_key;
    public String message;
    public String error;
    public ArrayList<Customer> SiteCats;
}

