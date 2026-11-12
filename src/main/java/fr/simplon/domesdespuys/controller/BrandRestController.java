package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.BrandDTO;
import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.repository.BrandRepository;
import fr.simplon.domesdespuys.service.interfaces.iBrand;
import fr.simplon.domesdespuys.view.brandView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private final iBrand brandService;
    private final BrandRepository brandRepository;

    @GetMapping
    @JsonView(brandView.BrandListView.class)
    public List<Brand> getBrands() {
        return brandService.getCollection();
    }

    @GetMapping(path = "/{id}")
    @JsonView(brandView.BrandDetailView.class)
    public ResponseEntity<Brand> getBrandById(@PathVariable String id) {
        Brand brand = brandService.getById(id);
        return brand == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(brand);
    }

    @GetMapping(path = "/slug/{slug}")
    @JsonView(brandView.BrandDetailView.class)
    public ResponseEntity<Brand> getBrandBySlug(@PathVariable String slug) {
        return brandRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @JsonView(brandView.BrandListView.class)
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(dto));
    }

    @PutMapping(path = "/{id}")
    @JsonView(brandView.BrandListView.class)
    public ResponseEntity<Brand> updateBrand(@PathVariable String id, @RequestBody BrandDTO dto) {
        Brand brand = brandService.update(id, dto);
        return brand == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(brand);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String id) {
        return brandService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}