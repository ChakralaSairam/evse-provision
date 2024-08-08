package com.sampleProject.evse.provision.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "sites")
public class Site {
    @Id
    private String siteId; //8 digit
    private String siteName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private Integer evseCount;

    private List<Evse> evses;



}
