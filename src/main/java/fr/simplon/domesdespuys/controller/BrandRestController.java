package fr.simplon.domesdespuys.controller;

import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private final BrandRepository brandRepository;

    @GetMapping
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Brand> getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id);
    }

    @GetMapping("/slug/{slug}")
    public Optional<Brand> getBrandBySlug(@PathVariable String slug) {
        return brandRepository.findBySlug(slug);
    }
}