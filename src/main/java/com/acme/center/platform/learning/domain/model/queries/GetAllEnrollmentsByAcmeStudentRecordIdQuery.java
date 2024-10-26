package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(AcmeStudentRecordId acmeStudentRecordId) {
    public GetAllEnrollmentsByAcmeStudentRecordIdQuery {
        if (acmeStudentRecordId == null || acmeStudentRecordId.studentRecordId() == null || acmeStudentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("AcmeStudentRecordId cannot be null");
        }
    }
}
