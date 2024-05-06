package com.deposco.test.controller;

import com.deposco.controller.SalesDataController;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SalesDataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SalesDataService salesDataService;

    @InjectMocks
    private SalesDataController salesDataController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(salesDataController).build();
    }

    @Test
    public void testGetTotalSalesAmount() throws Exception {
        when(salesDataService.calculateTotalSalesAmount()).thenReturn(123456.78);

        mockMvc.perform(get("/api/sales/total/amount"))
                .andExpect(status().isOk())
                .andExpect(content().string("123456.78"));
    }

    @Test
    public void testGetTotalSalesQuantity() throws Exception {
        when(salesDataService.calculateTotalSalesQuantity()).thenReturn(222.2);

        mockMvc.perform(get("/api/sales/total/quantity"))
                .andExpect(status().isOk())
                .andExpect(content().string("222.2"));
    }

    @Test
    public void testGetBestSellingProductsByQuantity1() throws Exception {
        List<SalesQuantityData> list = Arrays.asList(new SalesQuantityData("Item 1", 100));
        when(salesDataService.getBestSellingProductsByQuantity()).thenReturn(list);

        mockMvc.perform(get("/api/sales/bestselling/quantity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ItemDescription").value("Item 1"))
                .andExpect(jsonPath("$[0].Quantity").value(100));
    }

    @Test
    public void testGetBestSellingProductsByAmount() throws Exception {
        List<SalesAmountData> list = Arrays.asList(new SalesAmountData("Item 1", 200000.00));
        when(salesDataService.getBestSellingProductsByAmount()).thenReturn(list);

        mockMvc.perform(get("/api/sales/bestselling/amount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ItemDescription").value("Item 1"))
                .andExpect(jsonPath("$[0].TotalSaleAmount").value(200000.00));
    }

    @Test
    public void testGetMonthlySalesTrends() throws Exception {
        List<MonthlySalesData> list = Arrays.asList(new MonthlySalesData("January", 30000.00));
        when(salesDataService.getMonthlySalesTrends()).thenReturn(list);

        mockMvc.perform(get("/api/sales/trends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Month").value("January"))
                .andExpect(jsonPath("$[0].TotalSaleAmount").value(30000.00));
    }
}