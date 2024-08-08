package com.sampleProject.evse.provision.service;

import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SiteService {

    @Autowired
    SiteRepo siteRepo;

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



}
