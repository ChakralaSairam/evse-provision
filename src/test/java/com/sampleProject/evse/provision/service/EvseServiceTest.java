package com.sampleProject.evse.provision.service;

import com.sampleProject.evse.provision.exception.DuplicateValueException;
import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.repository.EvseCustomRepo;
import com.sampleProject.evse.provision.repository.EvseRepo;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.time.Instant;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvseServiceTest {

    @Mock
    private EvseRepo mockevseRepo;

    @Mock
    private EvseCustomRepo mockevseCustomRepo;

    @Mock
    private SiteRepo mocksiteRepo;

    @Mock
    private SiteCustomRepo mocksiteCustomRepo;

    @InjectMocks
    private EvseService evseServiceUnderTest;

    private BigInteger siteId = BigInteger.valueOf(12000000);

    @Captor
    ArgumentCaptor<Evse> evseArgumentCaptor;



    @Test
    void testAddEvse() throws DuplicateValueException {
        EvseInitialInfoDto evseInitialInfoDto = new EvseInitialInfoDto("123456789012","displayName_1");
        //Run the test
        evseServiceUnderTest.addEvse(siteId,evseInitialInfoDto);
//        Evse evse = new Evse();
        String serialNum = evseInitialInfoDto.getSerialNumber();
        String displayName = evseInitialInfoDto.getDisplayName();
//        evse.setRetired(false);
//        evse.setSiteId(siteId);
//
//
//        evse.setDisplayName(displayName);
//        evse.setSerialNumber(serialNum);
//        //mockito.anyString
//        evse.setEvseId();

        verify(mockevseRepo, times(1)).findAllByIsRetiredAndSerialNumber(false,serialNum);
        verify(mocksiteCustomRepo, times(1)).increaseEvseCount(siteId);

        verify(mockevseRepo).insert(evseArgumentCaptor.capture());
        Evse evseCaptorValue = evseArgumentCaptor.getValue();
        Assertions.assertEquals(evseCaptorValue.getSiteId(),siteId);

        Assertions.assertEquals(evseCaptorValue.getDisplayName(),displayName);
        Assertions.assertEquals(evseCaptorValue.getSerialNumber(),serialNum);
        Assertions.assertEquals(evseCaptorValue.isRetired(),false);
        Assertions.assertEquals(evseCaptorValue.getEvseId().startsWith(serialNum), true);
        Assertions.assertEquals(evseCaptorValue.getEvseId().endsWith(siteId.toString()),true);
    }

}
