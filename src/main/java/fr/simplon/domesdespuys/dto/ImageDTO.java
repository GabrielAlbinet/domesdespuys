package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ImageDTO {

    @NotBlank(message = "Il faut un chemin d'image")
    @Length(max = 180, message = "pas de chemin de plus de 180 caractères")
    private String path;

    @Length(max = 10, message = "pas d'extension de plus de 10 caractères")
    private String extension;

    private Boolean isDefault;
}