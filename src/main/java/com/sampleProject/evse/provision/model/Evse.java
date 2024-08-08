package com.sampleProject.evse.provision.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "evse")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Evse{

    @Id
    private String evseId; //= serial_num:site_id

    private Long serialNumber; //= abc
    private String displayName;
    private boolean isRetired;
    private String siteId; //= 123

}
