package com.wings.may24.model;

import java.io.Serializable;

public class PriceStockModel implements Serializable {
    private static final long serialVersionUUID = -1L;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int price;
    private int stock;

}
