package com.acme.center.platform.learning.domain.model.commands;

public record CreateCourseCommand(String title, String description) {

  public CreateCourseCommand {
    if(title==null || title.isBlank())
      throw new IllegalArgumentException("Course title cannot be blank");
    if(title==null || description.isBlank())
      throw new IllegalArgumentException("Course description cannot be blank");
  }
}
