package com.acme.center.platform.iam.domain.model.entities;

import com.acme.center.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Roles name;

  /**
   * Constructor with role name.
   *
   * @param name the role name.
   */
  public Role(Roles name) {
    this.name = name;
  }

  /*
    * Get the role name.
   */
  public String getStringName() {
    return name.name();
  }

  /**
   * Get the role name.
   *
   */
  public static  Role toRoleFromName(String name) {
    return new Role(Roles.valueOf(name));
  }

  /**
   * Get the default role.
   */
  public static Role getDefaultRole() {
    return new Role(Roles.ROLE_USER);
  }

  /**
   * Validate the roles set.
   */
  public static List<Role> validateRolesSet(List<Role> roles) {
    if (Objects.requireNonNull(roles).isEmpty()) {
      return List.of(getDefaultRole());
    }
    return roles;
  }
}
