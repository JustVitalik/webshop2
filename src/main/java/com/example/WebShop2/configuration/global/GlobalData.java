package com.example.WebShop2.configuration.global;


import com.example.WebShop2.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;

    static {
        cart= new ArrayList<Product>();
    }

}
