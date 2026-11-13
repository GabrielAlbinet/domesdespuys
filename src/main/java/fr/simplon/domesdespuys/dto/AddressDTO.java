package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AddressDTO {

    @NotBlank(message = "Il faut une adresse")
    @Length(max = 180, message = "pas d'adresse de plus de 180 caractères")
    private String line;

    @Length(max = 180, message = "pas de complément d'adresse de plus de 180 caractères")
    private String extraLine;

    @NotBlank(message = "Il faut un code postal")
    @Length(max = 40, message = "pas de code postal de plus de 20 caractères")
    private String zipCode;

    @NotBlank(message = "Il faut une ville")
    @Length(max = 40, message = "pas de ville de plus de 40 caractères")
    private String city;

    @NotBlank(message = "Il faut un pays")
    @Length(max = 40, message = "pas de pays de plus de 40 caractères")
    private String country;

    private String phone;

    @NotBlank(message = "Il faut un prénom")
    @Length(max = 40, message = "pas de prénom de plus de 40 caractères")
    private String firstname;

    @NotBlank(message = "Il faut un nom")
    @Length(max = 40, message = "pas de nom de plus de 40 caractères")
    private String lastname;
}