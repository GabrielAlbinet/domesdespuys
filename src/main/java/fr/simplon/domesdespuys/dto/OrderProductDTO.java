package fr.simplon.domesdespuys.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {

    @Positive(message = "la quantité doit être supérieure à 0")
    private int qty;

    @Positive(message = "le prix doit être supérieur à 0 (en centimes)")
    private int price;

    @NotNull(message = "Il faut un produit")
    private Long idProduct;
}