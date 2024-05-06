package com.deposco.test.service;

import com.deposco.model.MonthlySalesData;
import com.deposco.model.SalesAmountData;
import com.deposco.model.SalesQuantityData;
import com.deposco.service.SalesDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SalesDataServiceTest {

    @Mock
    private SalesDataService salesDataService;

    @BeforeEach
    public void setup() {}

    @Test
    public void testCalculateTotalSalesAmount() throws IOException {
        when(salesDataService.calculateTotalSalesAmount()).thenReturn(100000.0);
        double result = salesDataService.calculateTotalSalesAmount();
        assertEquals(100000.0, result);
        verify(salesDataService).calculateTotalSalesAmount();
    }

    @Test
    public void testCalculateTotalSalesQuantity() throws IOException {
        when(salesDataService.calculateTotalSalesQuantity()).thenReturn(100.0);
        double result = salesDataService.calculateTotalSalesQuantity();
        assertEquals(100.0, result);
        verify(salesDataService).calculateTotalSalesQuantity();
    }

    @Test
    public void testGetBestSellingProductsByQuantity() throws IOException {
        List<SalesQuantityData> expectedList = Arrays.asList(new SalesQuantityData("Product A", 150));
        when(salesDataService.getBestSellingProductsByQuantity()).thenReturn(expectedList);

        List<SalesQuantityData> resultList = salesDataService.getBestSellingProductsByQuantity();
        assertEquals(1, resultList.size());
        assertEquals("Product A", resultList.get(0).getItemDescription());
        assertEquals(150, resultList.get(0).getTotalQuantity());
    }

    @Test
    public void testGetBestSellingProductsByAmount() throws IOException {
        List<SalesAmountData> expectedList = Arrays.asList(new SalesAmountData("Product A", 20000.0));
        when(salesDataService.getBestSellingProductsByAmount()).thenReturn(expectedList);

        List<SalesAmountData> resultList = salesDataService.getBestSellingProductsByAmount();
        assertEquals(1, resultList.size());
        assertEquals("Product A", resultList.get(0).getItemDescription());
        assertEquals(20000.0, resultList.get(0).getTotalSalesAmount());
    }

    @Test
    public void testGetMonthlySalesTrends() throws IOException {
        List<MonthlySalesData> expectedList = Arrays.asList(new MonthlySalesData("January", 10000.0));
        when(salesDataService.getMonthlySalesTrends()).thenReturn(expectedList);

        List<MonthlySalesData> resultList = salesDataService.getMonthlySalesTrends();
        assertEquals(1, resultList.size());
        assertEquals("January", resultList.get(0).getMonth());
        assertEquals(10000.0, resultList.get(0).getTotalSales());
    }
}