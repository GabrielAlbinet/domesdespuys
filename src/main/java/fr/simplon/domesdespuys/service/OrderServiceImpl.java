package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.OrderPostDTO;
import fr.simplon.domesdespuys.dto.OrderPutDTO;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.service.interfaces.iOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements iOrder {

    private OrderRepository orderRepository;

    @Override
    public List<Order> getCollection() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(String id) {
        try {
            return orderRepository
                    .findById(Long.parseLong(id))
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Order create(OrderPostDTO dto) {
        return null;
    }

    @Override
    public Order update(String id, OrderPutDTO dto) {
        return null;
    }
}
