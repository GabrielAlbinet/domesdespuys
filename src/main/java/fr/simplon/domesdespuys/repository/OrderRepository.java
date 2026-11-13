package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByAddress(Address address);
}