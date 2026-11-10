package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.OrderPostDTO;
import fr.simplon.domesdespuys.dto.OrderPutDTO;
import fr.simplon.domesdespuys.entity.Order;

public interface iOrder extends
        iCreate<Order, OrderPostDTO>,
        iUpdate<Order, OrderPutDTO>,
        iGetItem<Order>,
        iCollection<Order>
{
}