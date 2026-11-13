package fr.simplon.domesdespuys.dto;

import fr.simplon.domesdespuys.customValidator.iExistingId;
import fr.simplon.domesdespuys.repository.AddressRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderPostDTO {

    @NotNull(message = "Il faut une adresse")
    @iExistingId(repository = AddressRepository.class, message = "Cette adresse n'existe pas")
    private Long idAddress;

    @NotEmpty(message = "Il faut au moins un produit")
    @Valid
    private List<OrderProductDTO> orderProducts;
}