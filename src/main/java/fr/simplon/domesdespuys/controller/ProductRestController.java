package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ProductRepository;
import fr.simplon.domesdespuys.service.interfaces.iProduct;
import fr.simplon.domesdespuys.view.brandView;
import fr.simplon.domesdespuys.view.productView;
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
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductRepository productRepository;
    private final iProduct productService;

    @GetMapping
    @JsonView(productView.ProductListView.class)
    public List<Product> getProducts() {
        return productService.getCollection();
    }

    @GetMapping("/{id}")
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @GetMapping("/slug/{slug}")
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}