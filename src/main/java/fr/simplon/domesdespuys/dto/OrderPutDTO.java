package fr.simplon.domesdespuys.dto;

import fr.simplon.domesdespuys.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPutDTO {

    @NotNull(message = "Il faut un statut")
    private OrderStatus status;
}