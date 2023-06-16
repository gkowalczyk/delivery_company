package com.example.domains.controller;

import com.delivery_company.openapi.model.CustomerDto;
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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

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
    void getAllCustomers() throws Exception {

        //Given
        //When&Then
        mockMvc
                .perform(get("/v1/customers/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].name", Matchers.is("CUSTOMER3")));
    }

    @Test
    @Sql(scripts = "/data.sql")
    void getCustomerById() throws Exception {
        //Given
        //When&Then
        mockMvc
                .perform(get("/v1/customers/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void addCustomer() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Add Customer1");

        Gson gson = new Gson();
        String content = gson.toJson(customerDto);
        //when&then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/customers/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200));
    }

    @Test
    void updateCustomer() throws Exception {

        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("updated Customer1");

        Gson gson = new Gson();
        String content = gson.toJson(customerDto);
        //whe&then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/customers/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("updated Customer1")));
    }

    @Test
    @Sql(scripts = "/data.sql")
    void deleteCustomer() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/customers/api/customers/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}