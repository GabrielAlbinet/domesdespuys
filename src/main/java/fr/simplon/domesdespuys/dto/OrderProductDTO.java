package fr.simplon.domesdespuys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {
    private int qty;
    private int price;
    private Long idProduct;
}