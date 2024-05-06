package com.deposco.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesAmountData {
    private String itemDescription;
    private double totalSalesAmount;

    public SalesAmountData() {}

    // Constructor with all fields
    public SalesAmountData(String itemDescription, double totalSalesAmount) {
        this.itemDescription = itemDescription;
        this.totalSalesAmount = totalSalesAmount;
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

    @JsonProperty("TotalSaleAmount")
    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    @JsonProperty("TotalSaleAmount")
    public void setTotalSalesAmount(double totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }
}