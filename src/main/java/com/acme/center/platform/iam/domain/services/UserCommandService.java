package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.commands.SingInCommand;
import com.acme.center.platform.iam.domain.model.commands.SingUpCommand;

import java.util.Optional;

public interface UserCommandService {
  Optional<User> handle(SingUpCommand command);

  Optional<InmutablePair<User, String>> handle(SingInCommand command);
}
