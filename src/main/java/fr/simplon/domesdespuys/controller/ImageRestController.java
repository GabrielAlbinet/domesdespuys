package fr.simplon.domesdespuys.controller;

import fr.simplon.domesdespuys.service.interfaces.iImage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private final iImage imageService;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable String id) {
        return imageService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
