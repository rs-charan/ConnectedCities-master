package com.mastercard.connectedcities.controller;

import com.mastercard.connectedcities.service.RouteFinderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConnectedCitiesController.class)
public class TestConnectedCitiesController {

    @Autowired
    private MockMvc mockMvcObject;

    @MockBean
    private RouteFinderService routeFinderService;

    @Test
    public void test_checkCitiesAreConnected() throws Exception {

        Mockito.when(routeFinderService.findSourceAndDestinationAreConnected("Boston","Newark"))
                .thenReturn(true);

        mockMvcObject.perform(MockMvcRequestBuilders.get("/connected")
        .accept(MediaType.ALL)).andReturn();
    }
}
