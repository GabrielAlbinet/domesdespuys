package fr.simplon.domesdespuys.dto;

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
    private Long idAddress;

    @NotEmpty(message = "Il faut au moins un produit")
    @Valid
    private List<OrderProductDTO> orderProducts;
}