package com.sampleProject.evse.provision.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "evse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Evse{

    @Id
    private String evseId; //= serial_num:site_id

    private String serialNumber; //= abc
    private String displayName;
    private boolean isRetired;
    private BigInteger siteId; //= 123

}
