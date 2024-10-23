package com.acme.center.platform.learning.domain.model.commands;

public record CancelEnrollmentCommand(Long enrollmentId) {

    public CancelEnrollmentCommand {
        if(enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("The enrollmentId is required and must be greater than 0.");
        }
    }
}
