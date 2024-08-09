package com.sampleProject.evse.provision.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "database_sequences")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class DatabaseSequence{

    @Id
    private String id;

    private long seq;


}
