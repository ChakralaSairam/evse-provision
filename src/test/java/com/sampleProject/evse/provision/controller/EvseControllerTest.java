package com.sampleProject.evse.provision.controller;


import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EvseControllerTest {

    @Mock
    private EvseService MockevseService;

    @Mock
    private SiteService MocksiteService;

    @InjectMocks
    private EvseController evseControllerUnderTest;


    private BigInteger siteId = BigInteger.valueOf(12000000);

    @Test
    void addEvseTest() {
//
//        EvseInitialInfoDto evseInitialInfoDto = new EvseInitialInfoDto("123456789012","displayName_1");
//
//        verify(MocksiteService, times(1)).isSiteExist(siteId);


    }
}
