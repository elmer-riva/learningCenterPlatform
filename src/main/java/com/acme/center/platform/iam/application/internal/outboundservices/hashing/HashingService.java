package com.acme.center.platform.iam.application.internal.outboundservices.hashing;

/**
 * Hashing service.
 * <p>
 *   This service is responsible for encoding and matching passwords.
 * </p>
 */
public interface HashingService {
  String encode(CharSequence rawPassword);
  boolean matches(CharSequence rawPassword, String encodedPassword);
}
