package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.dto.ProductPutDTO;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ProductRepository;
import fr.simplon.domesdespuys.service.interfaces.iProduct;
import fr.simplon.domesdespuys.view.brandView;
import fr.simplon.domesdespuys.view.productView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private final iProduct productService;
    private final ProductRepository productRepository;

    @GetMapping
    @JsonView(productView.ProductListView.class)
    public List<Product> getProducts() {
        return productService.getCollection();
    }

    @GetMapping(path = "/{id}")
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @GetMapping(path = "/slug/{slug}")
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductPostDTO dto) {
        Product product = productService.create(dto);
        return product == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(path = "/{id}")
    @JsonView(productView.ProductDetailView.class)
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody ProductPutDTO dto) {
        Product product = productService.update(id, dto);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }
}