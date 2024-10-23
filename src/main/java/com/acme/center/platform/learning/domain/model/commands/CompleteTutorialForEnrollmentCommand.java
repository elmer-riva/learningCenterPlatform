package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to complete a tutorial for an enrollment.
 */
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {

    /*
        * Constructor
        * @param enrollmentId The enrollment identifier.
        * @param tutorialId The tutorial identifier.
     */
    public CompleteTutorialForEnrollmentCommand {
        if(enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("The enrollmentId is required and must be greater than 0.");
        }
        if(tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("The tutorialId is required and must be greater than 0.");
        }
    }
}
