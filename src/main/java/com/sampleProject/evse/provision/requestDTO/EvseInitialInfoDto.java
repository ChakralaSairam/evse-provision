package com.sampleProject.evse.provision.requestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter@Setter
public class EvseInitialInfoDto {
    @NotBlank(message = " Serial number should not be blank")
    @Size(min = 12,max = 12)
    @Pattern(regexp = "^[0-9]*$")
    String serialNumber;
    @NotBlank(message = " Display name should not be blank")
    @Pattern(regexp = "^[-_ a-zA-Z0-9]+$")
    String displayName;
}
