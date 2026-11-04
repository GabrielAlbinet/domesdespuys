package fr.simplon.domesdespuys.repository;

import fr.simplon.domesdespuys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}