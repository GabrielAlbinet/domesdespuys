package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.enums.OrderStatus;
import fr.simplon.domesdespuys.view.orderView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(orderView.Id.class)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    @JsonView(orderView.Number.class)
    private String number;

    @Column(nullable = false, updatable = false)
    @JsonView(orderView.CreatedAt.class)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonView(orderView.Status.class)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(orderView.User.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @JsonView(orderView.Address.class)
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(orderView.OrderProducts.class)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}