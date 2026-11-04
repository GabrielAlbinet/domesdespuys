package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}