package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.OrderPostDTO;
import fr.simplon.domesdespuys.dto.OrderPutDTO;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.service.interfaces.iOrder;

import java.util.List;

public class OrderServiceImpl implements iOrder {
    @Override
    public List<Order> getCollection() {
        return List.of();
    }

    @Override
    public Order create(OrderPostDTO dto) {
        return null;
    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public Order update(String id, OrderPutDTO dto) {
        return null;
    }
}
