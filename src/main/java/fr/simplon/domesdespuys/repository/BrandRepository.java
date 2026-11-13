package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBySlugAndDeletedFalse(String slug);

    List<Brand> findAllByDeletedFalse();
}