package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.repository.BrandRepository;
import fr.simplon.domesdespuys.view.brandView;
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
    @JsonView(brandView.BrandListView.class)
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(brandView.BrandDetailView.class)
    public Optional<Brand> getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id);
    }

    @GetMapping("/slug/{slug}")
    @JsonView(brandView.BrandDetailView.class)
    public Optional<Brand> getBrandBySlug(@PathVariable String slug) {
        return brandRepository.findBySlug(slug);
    }
}