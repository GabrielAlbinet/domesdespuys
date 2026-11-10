package fr.simplon.domesdespuys.dto;

import fr.simplon.domesdespuys.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPutDTO {
    private OrderStatus status;
}