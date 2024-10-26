package com.acme.center.platform.learning.domain.model.queries;

public record GetCourseByIdQuery(Long courseId) {
    public GetCourseByIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("CourseId cannot be null or less than or equal to zero");
        }
    }
}
