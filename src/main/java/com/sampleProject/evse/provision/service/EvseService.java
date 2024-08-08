package com.sampleProject.evse.provision.service;


import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.repository.EvseCustomRepo;
import com.sampleProject.evse.provision.repository.EvseRepo;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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
        evseCustomRepo.retireEvse(evseId);
        String siteId = evseRepo.findById(evseId).get().getSiteId();
        siteCustomRepo.decreaseEvseCount(siteId); //decrease evseCount
    }

    public void addEvse(String siteId, Evse evse) {
        evse.setRetired(false);
        evse.setSiteId(siteId);

        siteCustomRepo.increaseEvseCount(siteId); //increase evseCount
        evseRepo.save(evse);
    }


    public Site getOneSiteEvseDetails(String siteId) {
        List<Evse> evses = evseRepo.findBysiteId(siteId);
        Site site = siteRepo.findById(siteId).get();
        site.setEvses(evses);
        return site;
    }

    public void removeEvse(String evseId) {
        evseRepo.deleteById(evseId);
    }


}
