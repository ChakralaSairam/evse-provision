package com.sampleProject.evse.provision.responseDTO;


import com.sampleProject.evse.provision.model.Evse;
import com.sampleProject.evse.provision.model.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor

public class SiteCompleteInfoDto {
    private BigInteger siteId;
    private String siteName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private int evseCount;
    List<Evse> evses;


    public SiteCompleteInfoDto(Site site, List<Evse> evses) {
        this.siteId = site.getSiteId();
        this.siteName = site.getSiteName();
        this.city = site.getCity();
        this.address1 = site.getAddress1();
        this.address2 = site.getAddress2();
        this.state = site.getState();
        this.country = site.getCountry();
        this.evseCount = site.getEvseCount();
        this.evses = evses;
    }
}
