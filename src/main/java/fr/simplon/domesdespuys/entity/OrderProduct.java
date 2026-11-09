package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.orderProductView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(orderProductView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(orderProductView.Quantity.class)
    private Integer quantity;

    @Column(nullable = false, precision = 7, scale = 2)
    @JsonView(orderProductView.Price.class)
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonView(orderProductView.Product.class)
    private Product product;
}