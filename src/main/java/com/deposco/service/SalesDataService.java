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
    @Value("classpath:data/sales_data.json")
    private Resource dataFile;

    private final ObjectMapper mapper;

    @Autowired
    public SalesDataService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public double calculateTotalSalesAmount() throws IOException {
        SalesWrapper data = mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        return data.getTotalSalesAmount();
    }

    public double calculateTotalSalesQuantity() throws IOException {
        SalesWrapper data = mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        return data.getTotalSalesQuantity();
    }

    public List<SalesQuantityData> getBestSellingProductsByQuantity() throws IOException {
        SalesWrapper data = mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        return data.getBestSellingByQuantity();
    }

    public List<SalesAmountData> getBestSellingProductsByAmount() throws IOException {
        SalesWrapper data = mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        return data.getBestSellingByAmount();
    }

    public List<MonthlySalesData> getMonthlySalesTrends() throws IOException {
        SalesWrapper data = mapper.readValue(dataFile.getFile(), SalesWrapper.class);
        return data.getMonthlySales();
    }

    public static class SalesWrapper {
        private double totalSalesAmount;
        private double totalSalesQuantity;
        private List<SalesQuantityData> bestSellingByQuantity;
        private List<SalesAmountData> bestSellingByAmount;
        private List<MonthlySalesData> monthlySales;

        // Constructors, getters, and setters below
        public SalesWrapper() {
        }

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

        public List<SalesAmountData> getBestSellingByAmount() {
            return bestSellingByAmount;
        }

        public List<MonthlySalesData> getMonthlySales() {
            return monthlySales;
        }
    }
}