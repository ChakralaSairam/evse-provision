package com.sampleProject.evse.provision.controller;


import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import com.sampleProject.evse.provision.service.EvseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    EvseService service;


    @GetMapping("/sites")
    public ResponseEntity<List<Site>> getSites(){
        List<Site> sites = service.getSites();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }

    @GetMapping("/sites/{id}")
    public ResponseEntity<Object> getOneSite(@PathVariable String id){
        Site site = service.getOneSite(id);
        return new ResponseEntity<>(site,HttpStatus.OK);
    }

    @PostMapping ("/sites")
    public ResponseEntity<Object> addSite(@RequestBody Site site){
    service.addSite(site);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/sites/{id}/evse")
    public ResponseEntity<Object> addEvse(@PathVariable String id, @RequestBody Evse evse){
    service.addEvse(id,evse);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/retire/evse/{id}")
    public ResponseEntity<Object> retireEvse(@PathVariable String id){
    service.retireEvse(id);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sites/{id}")
    public ResponseEntity<Object> removeSite(@PathVariable String id){
    service.removeSite(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/sites")
    public ResponseEntity<Object> removeAll(){
        service.removeAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/evse/{id}")
    public ResponseEntity<Object> removeEvse(@PathVariable String id){
    service.removeEvse(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
