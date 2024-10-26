package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(AcmeStudentRecordId acmeStudentRecordId, Long courseId) {
    public GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery {
        if (acmeStudentRecordId == null || acmeStudentRecordId.studentRecordId() == null || acmeStudentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("AcmeStudentRecordId cannot be null");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("CourseId cannot be null or less than or equal to zero");
        }
    }
}
