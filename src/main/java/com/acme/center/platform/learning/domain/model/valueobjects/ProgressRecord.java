package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgressRecord is an entity that is embedded in Enrollment aggregate.
 * It is a value object that is not persisted in the database.
 * It is used to track the progress of a student in a learning path.
 * It is initialized when an enrollment is created.
 * It is updated when a student starts or completes a tutorial.
 */
@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    /**
     * Initializes the progress record with the first tutorial in the learning path.
     * @param enrollment The enrollment to initialize the progress record
     * @param learningPath The learning path to initialize the progress record
     */
    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if (learningPath.isEmpty()) return;
        Long tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }

    /**
     * Starts the tutorial with the given id.
     * @param tutorialId The id of the tutorial to start
     */
    public void startTutorial(Long tutorialId) {

        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with given Id is already started or completed");
        }
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
    }

    /**
     * Completes the tutorial with the given id.
     * @param tutorialId The id of the tutorial to complete
     * @param learningPath The learning path to determine the next tutorial
     */

    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");

        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;

        Long nextTutorialId = learningPath.getNextTutorialInLearningPath(tutorialId);
        if (nextTutorialId != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }

    /**
     * Returns the progress record item with the given tutorial id.
     * @param tutorialId The id of the tutorial
     * @return The progress record item with the given tutorial id
     */
    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    /**
     * Returns a boolean indicating if there is a tutorial in progress.
     * @return true if there is a tutorial in progress
     */

    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    /**
     * Calculates the days elapsed for the given enrollment.
     * @param enrollment The enrollment to calculate the days elapsed
     * @return The days elapsed for the given enrollment
     */

    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }

}