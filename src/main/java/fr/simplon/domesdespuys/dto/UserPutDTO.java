package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserPutDTO {

    @NotBlank(message = "Il faut un prénom")
    @Length(max = 100, message = "pas de prénom de plus de 100 caractères")
    private String firstname;

    @NotBlank(message = "Il faut un nom")
    @Length(max = 100, message = "pas de nom de plus de 100 caractères")
    private String lastname;

    private String phone;
}