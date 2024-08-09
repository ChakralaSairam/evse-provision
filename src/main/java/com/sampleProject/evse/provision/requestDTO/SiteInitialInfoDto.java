package com.sampleProject.evse.provision.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteInitialInfoDto {
    private String siteName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
}
