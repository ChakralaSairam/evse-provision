package com.sampleProject.evse.provision.controller;


import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import com.sampleProject.evse.provision.responseDTO.SiteEvseDetailsDto;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class EvseController {

    @Autowired
    private EvseService evseService;

    @Autowired
    private SiteService siteService;


    @GetMapping("/sites/{id}")
    public ResponseEntity<Object> getOneSiteEvseDetails(@PathVariable BigInteger id){
        return new ResponseEntity<>(evseService.getOneSiteEvseDetails(id) ,HttpStatus.OK);
    }


    @PostMapping("/sites/{id}/evse")
    public ResponseEntity<Object> addEvse(@PathVariable BigInteger id, @RequestBody EvseInitialInfoDto evse){
        evseService.addEvse(id,evse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/retire/evse/{id}")
    public ResponseEntity<Object> retireEvse(@PathVariable String id){
        evseService.retireEvse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/evse/{id}")
    public ResponseEntity<Object> removeEvse(@PathVariable String id){
        evseService.removeEvse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
