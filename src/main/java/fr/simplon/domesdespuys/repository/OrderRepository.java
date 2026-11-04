package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}