package com.poly.global;

import java.util.ArrayList;
import java.util.List;

import com.poly.model.Product;

public class GlobalData {
    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }

}
