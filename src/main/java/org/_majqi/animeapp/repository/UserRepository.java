package org._majqi.animeapp.repository;

import org._majqi.animeapp.model.User; // Poprawny import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
