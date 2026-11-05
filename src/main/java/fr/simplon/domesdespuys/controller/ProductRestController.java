package fr.simplon.domesdespuys.controller;

import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ProductRepository;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @GetMapping("/slug/{slug}")
    public Optional<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug);
    }
}