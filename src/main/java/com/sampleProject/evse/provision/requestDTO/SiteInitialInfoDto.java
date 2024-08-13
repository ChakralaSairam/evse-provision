package com.sampleProject.evse.provision.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteInitialInfoDto {
    @NotBlank(message = "Site name should not be blank")
    @Pattern(regexp = "^[-_ a-zA-Z0-9]+$")
    @Size(min=2, max=20)
    private String siteName;


    @NotBlank(message = "Address 1 should not be blank")
    @Size(min=2, max=60)
    private String address1;


    @Size(max=60)
    private String address2;


    @NotBlank(message = " city field should not be blank")
    @Pattern(regexp = "^[ a-zA-Z]+$")
    @Size(min = 2,max = 20)
    private String city;


    @Pattern(regexp = "^[ a-zA-Z]+$")
    @Size(max = 20)
    private String state;

    @Size(min = 2,max=20)
    @NotBlank(message = " country field should not be blank")
    @Pattern(regexp = "^[ a-zA-Z]+$")
    private String country;
}
