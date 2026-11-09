package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.brandView;
import fr.simplon.domesdespuys.view.productView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(productView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(productView.Label.class)
    private String label;

    @Column(columnDefinition = "TEXT")
    @JsonView(productView.Description.class)
    private String description;

    @Column(nullable = false)
    @JsonView(productView.Price.class)
    private Integer price;

    @Column(nullable = false, unique = true)
    @JsonView(productView.Slug.class)
    private String slug;

    @Column(nullable = false, updatable = false)
    @JsonView(productView.CreatedAt.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonView(productView.Brand.class)
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(productView.Images.class)
    private List<Image> images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}