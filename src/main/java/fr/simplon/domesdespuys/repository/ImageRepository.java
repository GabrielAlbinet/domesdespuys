package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}