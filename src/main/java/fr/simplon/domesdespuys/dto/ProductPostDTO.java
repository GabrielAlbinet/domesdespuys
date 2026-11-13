package fr.simplon.domesdespuys.dto;

import fr.simplon.domesdespuys.customValidator.iExistingId;
import fr.simplon.domesdespuys.repository.BrandRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class ProductPostDTO {

    @NotBlank(message = "Il faut un label")
    @Length(max = 180, message = "pas de label de plus de 180 caractères")
    private String label;

    @NotBlank(message = "Il faut une description")
    private String description;

    @Positive(message = "le prix doit être supérieur à 0 (en centimes)")
    private int price;

    @NotNull(message = "Il faut une marque")
    @iExistingId(repository = BrandRepository.class, message = "Cette marque n'existe pas")
    private Long idBrand;

    @Valid
    private List<ImageDTO> images;
}