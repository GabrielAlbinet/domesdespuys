package fr.simplon.domesdespuys.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderPostDTO {
    private Long idAddress;
    private List<OrderProductDTO> orderProducts;
}