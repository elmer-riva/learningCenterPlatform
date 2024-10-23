package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/*
Represents a learning path item, which is a course or a tutorial that is part of a learning path.
@since 1.0.0
 */

@Getter
@Entity
public class LearningPathItem extends AuditableModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //Identity field patron

  @ManyToOne
  @JoinColumn(name = "course_id")
  @NotNull
  private Course course;

  @NotNull
  private Long tutorialId;

  @ManyToOne
  @JoinColumn(name = "next_item_id")
  private LearningPathItem nextItem;

  /**
   * Constructor for a LearningPathItem.
   * @param course The course that is part of the learning path.
   * @param tutorialId The tutorial that is part of the learning path.
   * @param nextItem The next item in the learning path.
   *                 If this is the last item, this should be null.
   * @see Course
   */

  public LearningPathItem(Course course, Long tutorialId, LearningPathItem nextItem) {
    this.course = course;
    this.tutorialId = tutorialId;
    this.nextItem = nextItem;
  }

  /**
   * Default constructor for a LearningPathItem.
   */
  public LearningPathItem() {
    this.tutorialId = 0L;
    this.nextItem = null;
  }

  /**
   * Updates the next item in the learning path.
   * @param nextItem The next item in the learning path.
   */

  public void updateNextItem(LearningPathItem nextItem) {
    this.nextItem = nextItem;
  }

}
