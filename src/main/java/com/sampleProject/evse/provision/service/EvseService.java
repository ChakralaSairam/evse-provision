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
    SiteRepo  siteRepo;

    @Autowired
    EvseCustomRepo evseCustomRepo;

    @Autowired
    SiteCustomRepo siteCustomRepo;



    public List<Site> getSites() {
        List<Site> sites = siteRepo.findAll();
        return sites;
    }

    public void addSite(Site site) {
        site.setEvseCount(0);
        site.setEvses(new ArrayList<>());
        siteRepo.save(site);
    }

    public void retireEvse(String evseId) {
        evseCustomRepo.retireEvse(evseId);
        String siteId = evseRepo.findById(evseId).get().getSiteId();
        //decrease evseCount
        siteCustomRepo.decreaseEvseCount(siteId);
    }

    public void addEvse(String siteId, Evse evse) {
        evse.setRetired(false);
        evse.setSiteId(siteId);
        //increase evseCount
        siteCustomRepo.increaseEvseCount(siteId);
        evseRepo.save(evse);
    }


    public Site getOneSite(String siteId) {
        List<Evse> evses = evseRepo.findBysiteId(siteId);
        Site site = siteRepo.findById(siteId).get();
        site.setEvses(evses);
        return site;
    }


    public void removeSite(String siteId) {
        siteRepo.deleteById(siteId);
        evseRepo.deleteBySiteId(siteId);
    }


    public void removeEvse(String evseId) {
        evseRepo.deleteById(evseId);
    }
}
