package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserPostDTO {

    @NotBlank(message = "Il faut un email")
    @Email(message = "Email invalide")
    @Length(max = 180, message = "Email trop long (180 caractères)")
    private String email;

    @NotBlank(message = "Il faut un mot de passe")
    @Length(min = 8, max = 20, message = "le mot de passe doit faire entre 8 et 20 caractères")
    private String password;

    @NotBlank(message = "Il faut un prénom")
    @Length(max = 40, message = "pas de prénom de plus de 40 caractères")
    private String firstname;

    @NotBlank(message = "Il faut un nom")
    @Length(max = 40, message = "pas de nom de plus de 40 caractères")
    private String lastname;
}