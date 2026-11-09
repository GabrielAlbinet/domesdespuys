package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.imageView;
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
    @JsonView(imageView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(imageView.Path.class)
    private String path;

    @Column(nullable = false, length = 10)
    @JsonView(imageView.Extension.class)
    private String extension;

    @Column(nullable = false)
    @JsonView(imageView.IsDefault.class)
    private Boolean isDefault = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}