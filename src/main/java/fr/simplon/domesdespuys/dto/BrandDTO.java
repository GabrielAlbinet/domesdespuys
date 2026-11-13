package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BrandDTO {

    @NotBlank(message = "Il faut un label")
    @Length(max = 180, message = "pas de label de plus de 180 caractères")
    private String label;

    private String logo;
}