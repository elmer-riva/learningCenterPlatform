package com.acme.center.platform.iam.application.internal.commandservices;

import com.acme.center.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.center.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.commands.SingInCommand;
import com.acme.center.platform.iam.domain.model.commands.SingUpCommand;
import com.acme.center.platform.iam.domain.services.UserCommandService;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;

  public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
  }

  @Override
  public Optional<User> handle(SingUpCommand command) {
    if (userRepository.existsByUsername(command.username()))
      throw new IllegalArgumentException("Username already exists");
    var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
      .orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
    var user = new User(command.username(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByUsername(command.username());
  }

  @Override
  public Optional<InmutablePair<User, String>> handle(SingInCommand command) {
    var user = userRepository.findByUsername(command.username())
      .orElseThrow(() -> new IllegalArgumentException("Username not found"));
    if (!hashingService.matches(command.password(), user.getPassword()))
      throw new IllegalArgumentException("Invalid password");
    var token = tokenService.generateToken(user.getUsername());
    return Optional.of(new InmutablePair<>(user, token));
  }
}
