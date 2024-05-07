package com.deposco.service;


import com.deposco.model.MonthlySalesData;
import com.deposco.model.SalesAmountData;
import com.deposco.model.SalesQuantityData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SalesDataService {
    private final ObjectMapper mapper;
    private final Resource dataFile;
    private SalesWrapper cachedData;

    @Autowired
    public SalesDataService(ObjectMapper mapper, @Value("classpath:data/sales_data.json") Resource dataFile) {
        this.mapper = mapper;
        this.dataFile = dataFile;
        this.cachedData = loadSalesData();
    }

    private SalesWrapper loadSalesData() {
        try {
            return mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sales data from file", e);
        }
    }

    public double calculateTotalSalesAmount() {
        return cachedData.getTotalSalesAmount();
    }

    public double calculateTotalSalesQuantity() {
        return cachedData.getTotalSalesQuantity();
    }

    public List<SalesQuantityData> getBestSellingProductsByQuantity() {
        return cachedData.getBestSellingByQuantity();
    }

    public List<SalesAmountData> getBestSellingProductsByAmount() {
        return cachedData.getBestSellingByAmount();
    }

    public List<MonthlySalesData> getMonthlySalesTrends() {
        return cachedData.getMonthlySales();
    }

    public static class SalesWrapper {
        private double totalSalesAmount;
        private double totalSalesQuantity;
        private List<SalesQuantityData> bestSellingByQuantity;
        private List<SalesAmountData> bestSellingByAmount;
        private List<MonthlySalesData> monthlySales;

        public double getTotalSalesAmount() {
            return totalSalesAmount;
        }

        public void setTotalSalesAmount(double totalSalesAmount) {
            this.totalSalesAmount = totalSalesAmount;
        }

        public double getTotalSalesQuantity() {
            return totalSalesQuantity;
        }

        public void setTotalSalesQuantity(double totalSalesQuantity) {
            this.totalSalesQuantity = totalSalesQuantity;
        }

        public List<SalesQuantityData> getBestSellingByQuantity() {
            return bestSellingByQuantity;
        }

        public void setBestSellingByQuantity(List<SalesQuantityData> bestSellingByQuantity) {
            this.bestSellingByQuantity = bestSellingByQuantity;
        }

        public List<SalesAmountData> getBestSellingByAmount() {
            return bestSellingByAmount;
        }

        public void setBestSellingByAmount(List<SalesAmountData> bestSellingByAmount) {
            this.bestSellingByAmount = bestSellingByAmount;
        }

        public List<MonthlySalesData> getMonthlySales() {
            return monthlySales;
        }

        public void setMonthlySales(List<MonthlySalesData> monthlySales) {
            this.monthlySales = monthlySales;
        }
    }
}