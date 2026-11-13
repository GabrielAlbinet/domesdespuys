package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.brandView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(brandView.Id.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(brandView.Label.class)
    private String label;

    @JsonView(brandView.Logo.class)
    private String logo;

    @Column(nullable = false, unique = true)
    @JsonView(brandView.Slug.class)
    private String slug;

    @Column(nullable = false)
    private boolean deleted = false;

    @JsonView(brandView.Products.class)
    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();
}