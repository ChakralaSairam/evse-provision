package com.sampleProject.evse.provision.controller;


import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.service.EvseService;
import com.sampleProject.evse.provision.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvseController {

    @Autowired
    private EvseService evseService;

    @Autowired
    private SiteService siteService;


    @GetMapping("/sites/{id}")
    public ResponseEntity<Object> getOneSiteEvseDetails(@PathVariable String id){
        Site site = evseService.getOneSiteEvseDetails(id);
        return new ResponseEntity<>(site,HttpStatus.OK);
    }



    @PostMapping("/sites/{id}/evse")
    public ResponseEntity<Object> addEvse(@PathVariable String id, @RequestBody Evse evse){
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
