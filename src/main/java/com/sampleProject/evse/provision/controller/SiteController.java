package com.sampleProject.evse.provision.controller;

import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.requestDTO.SiteInitialInfoDto;
import com.sampleProject.evse.provision.responseDTO.SiteCompleteInfoDto;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;


@RestController
public class SiteController {

    @Autowired
    private EvseService evseService;

    @Autowired
    private SiteService siteService;


    @GetMapping("/sites")
    @ResponseStatus(value = HttpStatus.OK)
    public List<SiteCompleteInfoDto> getSites(){
        List<SiteCompleteInfoDto> sites = siteService.getSites();
        return sites;
    }

    @PostMapping("/sites")
    public ResponseEntity<Object> addSite(@RequestBody SiteInitialInfoDto siteInitialInfoDto){
        siteService.addSite(siteInitialInfoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/sites/{id}")
    public ResponseEntity<Object> removeSite(@PathVariable BigInteger id){
        siteService.removeSite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/sites")
    public ResponseEntity<Object> removeAll(){
        siteService.removeAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
