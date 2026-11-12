package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.OrderPostDTO;
import fr.simplon.domesdespuys.dto.OrderProductDTO;
import fr.simplon.domesdespuys.dto.OrderPutDTO;
import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.entity.OrderProduct;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.enums.OrderStatus;
import fr.simplon.domesdespuys.repository.AddressRepository;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iCreateOrderProduct;
import fr.simplon.domesdespuys.service.interfaces.iOrder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements iOrder {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private iCreateOrderProduct orderProductService;

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
    @Transactional
    public Order create(OrderPostDTO dto) {
        User user = getDefaultUser();
        Address address = findAddress(dto.getIdAddress());
        if (address == null || dto.getOrderProducts() == null) {
            return null;
        }
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setNumber(String.format("CMD-%d-0000%d", order.getCreatedAt().getYear(), user.getId()));
        for (OrderProductDTO line : dto.getOrderProducts()) {
            OrderProduct orderProduct = orderProductService.create(line, order);
            if (orderProduct == null) {
                return null;
            }
            order.getOrderProducts().add(orderProduct);
        }
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order update(String id, OrderPutDTO dto) {
        Order order = getById(id);
        if (order != null && dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
            orderRepository.saveAndFlush(order);
        }
        return order;
    }

    private User getDefaultUser() {
        return userRepository.findById(1L).orElseThrow();
    }

    private Address findAddress(Long idAddress) {
        if (idAddress == null) {
            return null;
        }
        return addressRepository.findById(idAddress).orElse(null);
    }
}
