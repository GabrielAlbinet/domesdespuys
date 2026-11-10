package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.OrderProductDTO;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.entity.OrderProduct;

public interface iOrderProductService {

    OrderProduct create(OrderProductDTO dto, Order order);

}