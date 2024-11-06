package com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.iam.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The role repository.
 * <p>
 * This interface is responsible for managing the role entities.
 * It provides methods to interact with the role entities.
 * </p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);

  boolean existsByName(String name);
}
