package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}