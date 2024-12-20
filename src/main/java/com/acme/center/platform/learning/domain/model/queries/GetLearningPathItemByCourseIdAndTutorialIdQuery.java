package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;

public record GetLearningPathItemByCourseIdAndTutorialIdQuery(Long courseId, TutorialId tutorialId) {
  public GetLearningPathItemByCourseIdAndTutorialIdQuery {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("courseId cannot be null");
    }
    if (tutorialId == null || tutorialId.tutorialId() == null || tutorialId.tutorialId() <= 0) {
      throw new IllegalArgumentException("tutorialId cannot be null");
    }
  }
}
