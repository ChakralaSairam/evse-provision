package com.sampleProject.evse.provision.controller;


import com.sampleProject.evse.provision.exception.DuplicateValueException;
import com.sampleProject.evse.provision.exception.EvseNotFoundException;
import com.sampleProject.evse.provision.exception.SiteNotFoundException;
import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.requestDTO.EvseInitialInfoDto;
import com.sampleProject.evse.provision.responseDTO.SiteEvseDetailsDto;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;

@RestController
public class EvseController {

    @Autowired
    private EvseService evseService;

    @Autowired
    private SiteService siteService;


    @GetMapping("/sites/{id}")
    public ResponseEntity<Object> getOneSiteEvseDetails(@PathVariable BigInteger id) throws SiteNotFoundException {
        if(!siteService.isSiteExist(id))
            throw new SiteNotFoundException("Site does not exist");
        return new ResponseEntity<>(evseService.getOneSiteEvseDetails(id) ,HttpStatus.OK);
    }

    @GetMapping("/evse/{id}")
    public ResponseEntity<Object> getOneEVseDetails(@PathVariable String id) throws EvseNotFoundException {
        if(!evseService.isEvseExist(id))
            throw new EvseNotFoundException("Evse does not exist");
        return new ResponseEntity<>(evseService.getEvseDetails(id),HttpStatus.OK);
    }


    @PostMapping("/sites/{id}/evse")
    public ResponseEntity<Object> addEvse(@PathVariable BigInteger id, @RequestBody @Valid  EvseInitialInfoDto evse) throws SiteNotFoundException, DuplicateValueException {
        if(!siteService.isSiteExist(id)) {
            throw new SiteNotFoundException("Site does not exist");
        }
        evseService.addEvse(id,evse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/retire/evse/{id}")
    public ResponseEntity<Object> retireEvse(@PathVariable String id) throws EvseNotFoundException {
        if(!evseService.isEvseExist(id))
            throw new EvseNotFoundException("Evse does not exist");

        if(evseService.isEvseRetired(id)){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Evse Already Retired");
        }
        evseService.retireEvse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/evse/{id}")
    public ResponseEntity<Object> removeEvse(@PathVariable String id){
        if(!evseService.isEvseExist(id))
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Evse does not exist for deleting");
        evseService.removeEvse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
