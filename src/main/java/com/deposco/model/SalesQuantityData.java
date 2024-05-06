package com.deposco.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesQuantityData {
    private String itemDescription;
    private int totalQuantity;

    public SalesQuantityData() {}

    // Constructor with all fields
    public SalesQuantityData(String itemDescription, int totalQuantity) {
        this.itemDescription = itemDescription;
        this.totalQuantity = totalQuantity;
    }

    // Getters and Setters
    @JsonProperty("ItemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("ItemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("Quantity")
    public int getTotalQuantity() {
        return totalQuantity;
    }

    @JsonProperty("Quantity")
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}