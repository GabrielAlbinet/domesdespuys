package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.ImageDTO;
import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.entity.Image;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.service.interfaces.iImage;
import fr.simplon.domesdespuys.view.imageView;
import fr.simplon.domesdespuys.view.productView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private final iImage imageService;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable String id) {
        return imageService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping
    @JsonView(imageView.ImageListView.class)
    public ResponseEntity<Image> createImage(@Valid @RequestBody ImageDTO dto, Product product) {
        Image image = imageService.create(dto, product);
        return image == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(HttpStatus.CREATED).body(image);
    }

}
