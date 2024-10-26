package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId, Long courseId) {
  public RequestEnrollmentCommand {
    if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("Student record id is required");
    }
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course id is required");
    }
  }
}
