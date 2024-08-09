package com.sampleProject.evse.provision.responseDTO;

import com.sampleProject.evse.provision.model.Evse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteEvseDetailsDto {
    List<Evse> evses;
}
