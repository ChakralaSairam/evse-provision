package com.sampleProject.evse.provision.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sampleProject.evse.provision.exception.DuplicateValueException;
import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigInteger;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;




@AutoConfigureMockMvc
@WebMvcTest
public class EvseControllerTest {

    @MockBean
    EvseService mockEvseService;

    @MockBean
    SiteService mockSiteService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateMockmvc(){
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void addEvseTest() throws Exception {
        EvseInitialInfoDto evseInitialInfoDto = new EvseInitialInfoDto("947323874832","DisplayName_1");
        BigInteger siteId = BigInteger.valueOf(12345678);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(evseInitialInfoDto );

        RequestBuilder request = MockMvcRequestBuilders.post("/sites/{id}/evse", siteId)
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson);
//
//
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

}
