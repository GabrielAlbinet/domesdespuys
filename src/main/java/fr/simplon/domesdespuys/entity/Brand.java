package fr.simplon.domesdespuys.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String label;

    private String logo;

    @Column(nullable = false, unique = true)
    private String slug;

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();
}