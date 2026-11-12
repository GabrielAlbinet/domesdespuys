package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.service.interfaces.iOrder;
import fr.simplon.domesdespuys.view.orderView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final iOrder orderService;

    @GetMapping
    @JsonView(orderView.OrderListView.class)
    public List<Order> getOrders() {
        return orderService.getCollection();
    }

    @GetMapping(path = "/{id}")
    @JsonView(orderView.OrderDetailView.class)
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getById(id);
        return order == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(order);
    }
}