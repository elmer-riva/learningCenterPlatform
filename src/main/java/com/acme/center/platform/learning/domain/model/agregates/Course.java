package com.acme.center.platform.learning.domain.model.agregates;

import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Course aggregate root
 * @summary This class represents a course in the learning platform
 * A course is a collection of tutorials that a student can take to learn a specific topic
 * @since 1.0.0
 */
@Getter
@Entity
public class Course extends AuditableAbstractAggregateRoot<Course> {
  private String title;
  private String description;

  @Embedded
  private LearningPath learningPath;

  public Course(String title, String description) {
    this();
    this.title = title;
    this.description = description;
  }

  /**
   * Default constructor
   */
  public Course() {
    this.title = Strings.EMPTY;
    this.description = Strings.EMPTY;
    this.learningPath = new LearningPath();
  }

  /**
   * Update the course information
   * @param title The course title
   * @param description The course description
   * @return The updated course
   */

  public Course updateInformation(String title, String description) {
    this.title = title;
    this.description = description;
    return this;
  }

  /**
   * Add a tutorial to the learning path
   * @param tutorialId The tutorial id
   */
  public void addTutorialToLearningPath(Long tutorialId) {
    this.learningPath.addItem(this, tutorialId);
  }

  /**
   * Add a tutorial to the learning path. The tutorial is added after the next tutorial
   * @param tutorialId The tutorial id
   * @param nextTutorialId The next tutorial id
   */

  public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId) {
    this.learningPath.addItem(this, tutorialId, nextTutorialId);
  }

}
