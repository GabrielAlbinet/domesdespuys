package fr.simplon.domesdespuys.dto;

import fr.simplon.domesdespuys.customValidator.iExistingId;
import fr.simplon.domesdespuys.repository.ProductRepository;
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
    @iExistingId(repository = ProductRepository.class, message = "Ce produit n'existe pas")
    private Long idProduct;
}