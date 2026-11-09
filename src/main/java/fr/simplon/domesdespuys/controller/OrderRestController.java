package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.view.orderView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderRepository orderRepository;

    @GetMapping
    @JsonView(orderView.OrderListView.class)
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(orderView.OrderDetailView.class)
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }
}