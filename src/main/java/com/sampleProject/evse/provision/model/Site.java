package com.sampleProject.evse.provision.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "sites")
public class Site {

    @Transient
    public static final String SEQUENCE = "sites_sequence";


    @Id
    private BigInteger siteId;
    private String siteName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private Integer evseCount;



}
