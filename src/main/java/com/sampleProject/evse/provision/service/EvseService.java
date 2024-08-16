package com.sampleProject.evse.provision.service;


import com.sampleProject.evse.provision.exception.DuplicateValueException;
import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.repository.EvseCustomRepo;
import com.sampleProject.evse.provision.repository.EvseRepo;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import com.sampleProject.evse.provision.responseDTO.EvseCompleteInfoDto;
import com.sampleProject.evse.provision.responseDTO.SiteEvseDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvseService {

    @Autowired
    EvseRepo  evseRepo;

    @Autowired
    EvseCustomRepo evseCustomRepo;

    @Autowired
    SiteRepo siteRepo;

    @Autowired
    SiteCustomRepo siteCustomRepo;





    public void retireEvse(String evseId) {
        Evse evse = evseRepo.findById(evseId).get();
        evseCustomRepo.retireEvse(evseId);
        BigInteger siteId = evse.getSiteId();
        siteCustomRepo.decreaseEvseCount(siteId);    //decrease evseCount
    }

    public void addEvse(BigInteger siteId, EvseInitialInfoDto evseInitialInfoDto) throws DuplicateValueException {
        String serialNum = evseInitialInfoDto.getSerialNumber();
        String displayName = evseInitialInfoDto.getDisplayName();

        if(!evseRepo.findAllByIsRetiredAndSerialNumber(false, serialNum).isEmpty())
            throw new DuplicateValueException("Evse with this serial number already exists");
        Evse evse = new Evse();
        evse.setRetired(false);
        evse.setSiteId(siteId);


        evse.setDisplayName(displayName);
        evse.setSerialNumber(serialNum);
        evse.setEvseId(serialNum + ":" + siteId);
        siteCustomRepo.increaseEvseCount(siteId); //increase evseCount
        evseRepo.insert(evse);
    }


    public SiteEvseDetailsDto getOneSiteEvseDetails(BigInteger siteId) {
        List<Evse> evses = evseRepo.findBysiteId(siteId);
        SiteEvseDetailsDto siteEvseDetailsDto = new SiteEvseDetailsDto();
        siteEvseDetailsDto.setEvses(evses);
        return siteEvseDetailsDto;
    }

    public void removeEvse(String evseId) {
        evseRepo.deleteById(evseId);
    }


    public boolean isEvseExist(String id) {
        return evseRepo.existsById(id);
    }

    public boolean isEvseRetired(String id) {
        return evseRepo.findById(id).get().isRetired();
    }

    public EvseCompleteInfoDto getEvseDetails(String id) {
        Evse evse = evseRepo.findById(id).get();
        EvseCompleteInfoDto evseDetails = new EvseCompleteInfoDto(evse);
        return evseDetails;
    }
}
