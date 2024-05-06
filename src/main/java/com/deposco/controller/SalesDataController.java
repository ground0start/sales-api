package com.deposco.controller;

import com.deposco.model.MonthlySalesData;
import com.deposco.model.SalesAmountData;
import com.deposco.model.SalesQuantityData;
import com.deposco.service.SalesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling requests related to sales data.
 * Provides endpoints for retrieving total sales amounts, sales quantities,
 * best selling products, and sales trends.
 */
@RestController
@RequestMapping("/api/sales")
public class SalesDataController {

    @Autowired
    private SalesDataService salesDataService;

    /**
     * Retrieves the total sales amount.
     * @return Total sales amount as a double.
     * @throws IOException if there is an error accessing the data source.
     */
    @GetMapping("/total/amount")
    public double getTotalSalesAmount() throws IOException {
        return salesDataService.calculateTotalSalesAmount();
    }

    /**
     * Retrieves the total sales quantity.
     * @return Total sales quantity as a double.
     * @throws IOException if there is an error accessing the data source.
     */
    @GetMapping("/total/quantity")
    public double getTotalSalesQuantity() throws IOException {
        return salesDataService.calculateTotalSalesQuantity();
    }

    /**
     * Retrieves a list of best-selling products sorted by quantity.
     * @return List of sales quantity data.
     * @throws IOException if there is an error accessing the data source.
     */
    @GetMapping("/bestselling/quantity")
    public List<SalesQuantityData> getBestSellingProductsByQuantity() throws IOException {
        return salesDataService.getBestSellingProductsByQuantity();
    }

    /**
     * Retrieves a list of best-selling products sorted by total sales amount.
     * @return List of sales amount data.
     * @throws IOException if there is an error accessing the data source.
     */
    @GetMapping("/bestselling/amount")
    public List<SalesAmountData> getBestSellingProductsByAmount() throws IOException {
        return salesDataService.getBestSellingProductsByAmount();
    }

    /**
     * Retrieves monthly sales trends data.
     * @return List of monthly sales data.
     * @throws IOException if there is an error accessing the data source.
     */
    @GetMapping("/trends")
    public List<MonthlySalesData> getMonthlySalesTrends() throws IOException {
        return salesDataService.getMonthlySalesTrends();
    }

    /**
     * Handles IOException that may occur during data retrieval.
     * @param ex The exception that was thrown.
     * @return A response entity containing the error message and a HTTP status code.
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>("An error occurred while processing your request: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}