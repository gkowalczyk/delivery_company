package com.example.domains.controller;


import com.delivery_company.openapi.model.TrackingInfoDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrackingControllerTest {
/*    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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

    @Sql(scripts = "/data.sql")
    @Test
    void getTrackingInfo() throws Exception {

        //Given
        //When&Then
        mockMvc
                .perform(get("/v1/tracking/api/orders/1/tracking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", Matchers.is("LOCATION1")));
    }

    @Test
    @Sql(scripts = "/data.sql")
    void updateTrackingInfo() throws Exception {


        //given
        TrackingInfoDto trackingInfoDtoToUpdate = new TrackingInfoDto();
        trackingInfoDtoToUpdate.setStatus("Done");

        Gson gson = new Gson();
        String content = gson.toJson(trackingInfoDtoToUpdate);
        //whe&then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tracking/api/orders/1/tracking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("Done")));
    }*/
}