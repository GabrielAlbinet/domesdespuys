package fr.simplon.domesdespuys.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false, length = 10)
    private String extension;

    @Column(nullable = false)
    private Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}