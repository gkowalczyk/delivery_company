package com.example.domains.controller;


import com.delivery_company.openapi.model.OrderDto;
import com.delivery_company.openapi.model.TrackingInfoDto;
import com.example.domains.repository.OrderRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;

    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("This is the beginning of tests.");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are finished.");
    }

    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }

    @Test
    @Sql(scripts = "/data.sql")
    void getOrderById() throws Exception {
        //Given
        //When&Then
        mockMvc
                .perform(get("/v1/order/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void cancelOrderById() throws Exception {

        //Given
        //When&Then

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/order/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test

    @Sql(statements = "INSERT INTO TRACKING_INFO (location, status) VALUES( 'LOCATION1', 'IN PROGRESS')")
    void placeOrder() throws Exception {

        //given
        TrackingInfoDto trackingInfoDto = new TrackingInfoDto();
        trackingInfoDto.setId(1);
        trackingInfoDto.setLocation("location");
        trackingInfoDto.setLocation("status");
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);
        orderDto.setTrackinginfoDto(trackingInfoDto);
        orderDto.setProducts(List.of());
        orderDto.setCustomerId(1);

        Gson gson = new Gson();
        String content = gson.toJson(orderDto);
        //when&then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/order/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200));
    }

    @Test
    @Sql(scripts = "/data.sql")
    void getOrderHistoryByCustomerId() throws Exception {
        //Given
        //When&Then
        mockMvc
                .perform(get("/v1/order/api/customers/1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].trackinginfoDto.status", Matchers.is("IN PROGRESS")));
    }
}