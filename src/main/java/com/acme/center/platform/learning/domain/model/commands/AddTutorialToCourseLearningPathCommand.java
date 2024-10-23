package com.acme.center.platform.learning.domain.model.commands;

/*
    * Represents the command to add a tutorial to a course learning path.
 */
public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {

    /*
    * Constructor
    * @param tutorialId The tutorial identifier.
    * @param courseId The course identifier.
    * @throws IllegalArgumentException if the tutorialId is null or less than or equal to 0.
    * @throws IllegalArgumentException if the courseId is null or less than or equal to 0.
     */
    public AddTutorialToCourseLearningPathCommand {
        if( tutorialId== null || tutorialId <= 0) {
            throw new IllegalArgumentException("The tutorialId is required and must be greater than 0.");
        }
        if(courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("The courseId is required and must be greater than 0.");
        }
    }
}
