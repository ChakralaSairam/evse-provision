package com.sampleProject.evse.provision.service;

import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.repository.EvseCustomRepo;
import com.sampleProject.evse.provision.repository.EvseRepo;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import com.sampleProject.evse.provision.requestDTO.SiteInitialInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
public class SiteService {

    @Autowired
    private EvseRepo evseRepo;

    @Autowired
    private EvseCustomRepo evseCustomRepo;

    @Autowired
    private SiteRepo siteRepo;

    @Autowired
    private SiteCustomRepo siteCustomRepo;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    public List<Site> getSites() {
        List<Site> sites = siteRepo.findAll();
        return sites;
    }

    public void addSite(SiteInitialInfoDto siteInitialInfoDto) {
        Site site = new Site(siteInitialInfoDto);
        site.setEvseCount(0);
        site.setSiteId(BigInteger.valueOf(sequenceGeneratorService.generateSequence(Site.SEQUENCE)));
        siteRepo.save(site);
    }

    public void removeSite(BigInteger siteId) {
        siteRepo.deleteById(siteId);
        evseRepo.deleteBySiteId(siteId);
    }

    public void removeAll() {
        siteRepo.deleteAll();
        evseRepo.deleteAll();
    }




}
