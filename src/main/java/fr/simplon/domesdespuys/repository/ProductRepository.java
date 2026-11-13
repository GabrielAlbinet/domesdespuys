package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);
    void deleteAllByBrand(Brand brand);
}