package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

  /**
   * Handle the seed roles command.
   *
   * <p>
   *   This method is responsible for handling the seed roles command.
   * </p>
   *
   * @param command the seed roles command.
   */

  void handle(SeedRolesCommand command);
}
