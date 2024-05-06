package com.deposco.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MonthlySalesData {
    private String month;
    private double totalSales;

    public MonthlySalesData(String month, double totalSales) {
        this.month = month;
        this.totalSales = totalSales;
    }

    public MonthlySalesData() {}

    // Getters and Setters
    @JsonProperty("Month")
    public String getMonth() {
        return month;
    }

    @JsonProperty("Month")
    public void setMonth(String month) {
        this.month = month;
    }

    @JsonProperty("TotalSaleAmount")
    public double getTotalSales() {
        return totalSales;
    }

    @JsonProperty("TotalSaleAmount")
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}