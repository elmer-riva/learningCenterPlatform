package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to confirm an enrollment.
 */
public record ConfirmEnrollmentCommand(Long enrollmentId) {

    /**
     * Constructor
     * @param enrollmentId The enrollment identifier.
     *                     * @throws IllegalArgumentException if the enrollmentId is null or less than or equal to 0.
     */
    public ConfirmEnrollmentCommand {
        if(enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("The enrollmentId is required and must be greater than 0.");
        }
    }

}
