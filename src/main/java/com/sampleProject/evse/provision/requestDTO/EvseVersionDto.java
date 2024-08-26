package com.sampleProject.evse.provision.requestDTO;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvseVersionDto {

    private String serialNumber;
    private String fmVersion;
}
