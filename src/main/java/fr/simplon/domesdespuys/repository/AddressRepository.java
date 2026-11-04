package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}