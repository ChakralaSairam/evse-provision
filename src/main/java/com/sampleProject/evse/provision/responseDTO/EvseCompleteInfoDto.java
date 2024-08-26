package com.sampleProject.evse.provision.responseDTO;

import com.sampleProject.evse.provision.model.Evse;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvseCompleteInfoDto {
    private String evseId; //= serial_num:site_id

    private String serialNumber; //= abc
    private String displayName;
    private boolean isRetired;
    private BigInteger siteId;
    private String fmVersion;

    public EvseCompleteInfoDto(Evse evse) {
        this.evseId = evse.getEvseId();
        this.serialNumber = evse.getSerialNumber();
        this.displayName = evse.getDisplayName();
        this.isRetired = evse.isRetired();
        this.siteId = evse.getSiteId();
        this.fmVersion = evse.getFmVersion();
    }
}
