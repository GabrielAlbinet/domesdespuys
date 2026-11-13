package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.OrderPostDTO;
import fr.simplon.domesdespuys.dto.OrderPutDTO;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.service.interfaces.iOrder;
import fr.simplon.domesdespuys.view.orderView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @JsonView(orderView.OrderDetailView.class)
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderPostDTO dto) {
        Order order = orderService.create(dto);
        return order == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping(path = "/{id}")
    @JsonView(orderView.OrderDetailView.class)
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @Valid @RequestBody OrderPutDTO dto) {
        Order order = orderService.update(id, dto);
        return order == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(order);
    }
}