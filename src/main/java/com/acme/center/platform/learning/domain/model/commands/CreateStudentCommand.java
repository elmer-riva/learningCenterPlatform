package com.acme.center.platform.learning.domain.model.commands;

public record CreateStudentCommand(
  String firstName,
  String lastName,
  String email,
  String street,
  String number,
  String city,
  String postalCode,
  String country) {

  public CreateStudentCommand {
    if(firstName==null || firstName.isBlank())
      throw new IllegalArgumentException("Student first name cannot be blank");
    if(lastName==null || lastName.isBlank())
      throw new IllegalArgumentException("Student last name cannot be blank");
    if(email==null || email.isBlank())
      throw new IllegalArgumentException("Student email cannot be blank");
    if(street==null || street.isBlank())
      throw new IllegalArgumentException("Student street cannot be blank");
    if(number==null || number.isBlank())
      throw new IllegalArgumentException("Student number cannot be blank");
    if(city==null || city.isBlank())
      throw new IllegalArgumentException("Student city cannot be blank");
    if(postalCode==null || postalCode.isBlank())
      throw new IllegalArgumentException("Student postal code cannot be blank");
    if(country==null || country.isBlank())
      throw new IllegalArgumentException("Student country cannot be blank");
  }

}
