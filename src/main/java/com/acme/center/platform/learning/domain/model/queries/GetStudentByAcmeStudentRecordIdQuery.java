package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetStudentByAcmeStudentRecordIdQuery(AcmeStudentRecordId acmeStudentRecordId) {
  public GetStudentByAcmeStudentRecordIdQuery {
    if (acmeStudentRecordId == null || acmeStudentRecordId.studentRecordId() == null || acmeStudentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("acmeStudentRecordId cannot be null");
    }
  }
}
