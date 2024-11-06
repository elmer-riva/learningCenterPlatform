package com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The user repository.
 * <p>
 * This interface is responsible for managing the user entities.
 * It provides methods to interact with the user entities.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
}
