package com.sampleProject.evse.provision.service;

import com.sampleProject.evse.provision.exception.SiteNotFoundException;
import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.repository.EvseCustomRepo;
import com.sampleProject.evse.provision.repository.EvseRepo;
import com.sampleProject.evse.provision.repository.SiteCustomRepo;
import com.sampleProject.evse.provision.repository.SiteRepo;
import com.sampleProject.evse.provision.requestDTO.SiteInitialInfoDto;
import com.sampleProject.evse.provision.responseDTO.SiteCompleteInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public List<SiteCompleteInfoDto> getSites() {
        List<Site> sites = siteRepo.findAll();
        List<SiteCompleteInfoDto> sitesWithEvseDetails = new ArrayList<>();

        sites.forEach(site -> {
            List<Evse> evses = evseRepo.findBysiteId(site.getSiteId());
            SiteCompleteInfoDto siteWithEvseDetails = new SiteCompleteInfoDto(site,evses);
            sitesWithEvseDetails.add(siteWithEvseDetails);
        });

        return sitesWithEvseDetails;
    }

    public void addSite(SiteInitialInfoDto siteInitialInfoDto) {
        Site site = new Site(siteInitialInfoDto);
        site.setEvseCount(0);
        site.setSiteId(BigInteger.valueOf(sequenceGeneratorService.generateSequence(Site.SEQUENCE)));
        siteRepo.save(site);
    }

    public void removeSite(BigInteger siteId){

        siteRepo.deleteById(siteId);
        evseRepo.deleteBySiteId(siteId);
    }

    public void removeAll() {

        siteRepo.deleteAll();
        evseRepo.deleteAll();
    }

    public boolean isSiteExist(BigInteger siteId){
        return siteRepo.existsById(siteId);
    }




}
